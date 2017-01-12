






//$$strtCprt
/**
* OEEE (Oversimplified [MathML] Expression Editor for Eclipse)
* 
* Copyright (C) 2016 Thornton Green
* 
* This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
* published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty 
* of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with this program; if not, 
* see <http://www.gnu.org/licenses>.
* Additional permission under GNU GPL version 3 section 7
*
*/
//$$endCprt






package algsymboleditor.editors;


import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.*;
import java.awt.geom.Path2D;
import java.beans.XMLDecoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.swing.*;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.mvel2.optimizers.OptimizerFactory;

import algsymboleditor.editors.QuotientEditorCanvas.DefaultInsert;
import simplealgebra.DoubleElem;
import simplealgebra.symbolic.DroolsSession;
import meta.*;


/**
 * Canvas for rendering symbol edits.
 * 
 * @author tgreen
 *
 */
public class SymbolEditorCanvas extends JPanel implements Scrollable {
	
	
	/**
	 * Enumerated insertion modes.
	 * @author tgreen
	 *
	 */
	public static enum InsertModes
	{
		/**
		 * Script insert.
		 */
		SCRIPT_MODE
		{
			@Override
			void insertStringSwing( String str , SymbolEditorCanvas canvas )
			{
				AlgCommon.performLstInsert( str, canvas.swingScriptLst );
			}
			
			@Override
			void deleteSymSwing( SymbolEditorCanvas canvas )
			{
				AlgCommon.performDelete( canvas.swingScriptLst );
			}
			
			@Override
			void insertStringSwt( String str , SymbolEditorCanvas canvas )
			{
				AlgCommon.performLstInsert( str, canvas.swtScriptLst );
			}
			
			@Override
			void deleteSymSwt( SymbolEditorCanvas canvas )
			{
				AlgCommon.performDelete( canvas.swtScriptLst );
			}
		},
		
		/**
		 * Subscript insert.
		 */
		SUBSCRIPT_MODE
		{
			@Override
			void insertStringSwing( String str , SymbolEditorCanvas canvas )
			{
				AlgCommon.performLstInsert( str, canvas.swingSubscriptLst );
			}
			
			@Override
			void deleteSymSwing( SymbolEditorCanvas canvas )
			{
				AlgCommon.performDelete( canvas.swingSubscriptLst );
			}
			
			@Override
			void insertStringSwt( String str , SymbolEditorCanvas canvas )
			{
				AlgCommon.performLstInsert( str, canvas.swtSubscriptLst );
			}
			
			@Override
			void deleteSymSwt( SymbolEditorCanvas canvas )
			{
				AlgCommon.performDelete( canvas.swtSubscriptLst );
			}
		},
		
		/**
		 * Superscript insert.
		 */
		SUPERSCRIPT_MODE
		{
			@Override
			void insertStringSwing( String str , SymbolEditorCanvas canvas )
			{
				AlgCommon.performLstInsert( str, canvas.swingSuperscriptLst );
			}
			
			@Override
			void deleteSymSwing( SymbolEditorCanvas canvas )
			{
				AlgCommon.performDelete( canvas.swingSuperscriptLst );
			}
			
			@Override
			void insertStringSwt( String str , SymbolEditorCanvas canvas )
			{
				AlgCommon.performLstInsert( str, canvas.swtSuperscriptLst );
			}
			
			@Override
			void deleteSymSwt( SymbolEditorCanvas canvas )
			{
				AlgCommon.performDelete( canvas.swtSuperscriptLst );
			}
		},
		
		/**
		 * Overscript insert.
		 */
		OVERSCRIPT_MODE
		{
			@Override
			void insertStringSwing( String str , SymbolEditorCanvas canvas )
			{
				AlgCommon.performLstInsert( str, canvas.swingOverscriptLst );
			}
			
			@Override
			void deleteSymSwing( SymbolEditorCanvas canvas )
			{
				AlgCommon.performDelete( canvas.swingOverscriptLst );
			}
			
			@Override
			void insertStringSwt( String str , SymbolEditorCanvas canvas )
			{
				AlgCommon.performLstInsert( str, canvas.swtOverscriptLst );
			}
			
			@Override
			void deleteSymSwt( SymbolEditorCanvas canvas )
			{
				AlgCommon.performDelete( canvas.swtOverscriptLst );
			}
		},
		
		/**
		 * Underscript insert.
		 */
		UNDERSCRIPT_MODE
		{
			@Override
			void insertStringSwing( String str , SymbolEditorCanvas canvas )
			{
				AlgCommon.performLstInsert( str, canvas.swingUnderscriptLst );
			}
			
			@Override
			void deleteSymSwing( SymbolEditorCanvas canvas )
			{
				AlgCommon.performDelete( canvas.swingUnderscriptLst );
			}
			
			@Override
			void insertStringSwt( String str , SymbolEditorCanvas canvas )
			{
				AlgCommon.performLstInsert( str, canvas.swtUnderscriptLst );
			}
			
			@Override
			void deleteSymSwt( SymbolEditorCanvas canvas )
			{
				AlgCommon.performDelete( canvas.swtUnderscriptLst );
			}
			
		};
		
		
		/**
		 * Inserts a symbol on the Swing thread.
		 * @param str The symbol to insert.
		 * @param canvas The canvas on which to insert the symbol.
		 */
		abstract void insertStringSwing( String str , SymbolEditorCanvas canvas );
		
		/**
		 * Deletes a symbol on the Swing thread.
		 * @param canvas The canvas on which to delete the symbol.
		 */
		abstract void deleteSymSwing( SymbolEditorCanvas canvas );
		
		/**
		 * Inserts a symbol on the SWT thread.
		 * @param str The symbol to insert.
		 * @param canvas The canvas on which to insert the symbol.
		 */
		abstract void insertStringSwt( String str , SymbolEditorCanvas canvas );
		
		/**
		 * Deletes a symbol on the SWT thread.
		 * @param canvas The canvas on which to delete the symbol.
		 */
		abstract void deleteSymSwt( SymbolEditorCanvas canvas );
		
		
	};
	
	
	/**
	 * The default insertion mode.
	 * @author tgreen
	 *
	 */
	public static enum DefaultInsert
	{
		
		/**
		 * Inserts with no wrapping.
		 */
		NO_INSERT_MODE
		{
			@Override
			String handleInsert( String in )
			{
				return( in );
			}
		},
		
		/**
		 * Inserts as an identifier.
		 */
		IDENT_INSERT_MODE
		{
			@Override
			String handleInsert( String in )
			{
				return( "<mi>" + in + "</mi>" );
			}
		},
		
		/**
		 * Inserts as an operator.
		 */
		OPERATOR_INSERT_MODE
		{
			@Override
			String handleInsert( String in )
			{
				return( "<mo>" + in + "</mo>" );
			}
		},
		
		/**
		 * Inserts as a number.
		 */
		NUMBER_INSERT_MODE
		{
			@Override
			String handleInsert( String in )
			{
				return( "<mn>" + in + "</mn>" );
			}
		};
		
		/**
		 * Handles the insertion of a symbol.
		 * @param in The symbol to insert.
		 * @return The wrapped version of the symbol.
		 */
		abstract String handleInsert( String in );
		
		
	};
	
	
	
	/**
	 * The Overarch Insertion mode.
	 * @author tgreen
	 *
	 */
	public static enum OverarchInsert
	{
		
		/**
		 * Inserts using the default mode.
		 */
		DEFAULT_INSERT_MODE
		{
			@Override
			String handleInsert( String in , DefaultInsert def )
			{
				return( def.handleInsert( in ) );
			}
		},
		
		/**
		 * Inserts with no wrapping.
		 */
		NO_INSERT_MODE
		{
			@Override
			String handleInsert( String in , DefaultInsert def )
			{
				return( in );
			}
		},
		
		/**
		 * Inserts as an identifier.
		 */
		IDENT_INSERT_MODE
		{
			@Override
			String handleInsert( String in , DefaultInsert def )
			{
				return( DefaultInsert.IDENT_INSERT_MODE.handleInsert(in) );
			}
		},
		
		/**
		 * Inserts as an operator.
		 */
		OPERATOR_INSERT_MODE
		{
			@Override
			String handleInsert( String in , DefaultInsert def )
			{
				return( DefaultInsert.OPERATOR_INSERT_MODE.handleInsert(in) );
			}
		},
		
		/**
		 * Inserts as a number.
		 */
		NUMBER_INSERT_MODE
		{
			@Override
			String handleInsert( String in , DefaultInsert def )
			{
				return( DefaultInsert.NUMBER_INSERT_MODE.handleInsert(in) );
			}
		};
		
		/**
		 * Handles the insertion of a symbol.
		 * @param in The symbol to insert.
		 * @param def The default insertion mode.
		 * @return The wrapped version of the symbol.
		 */
		abstract String handleInsert( String in , DefaultInsert def );
		
		
	};
	
	
	/**
	 * The current SWT display.
	 */
	protected volatile Display display = null;
	
	/**
	 * The current insertion mode on the Swing thread.
	 */
	protected InsertModes swingInsertMode = InsertModes.SCRIPT_MODE;
	
	/**
	 * The current Overarch Insert mode on the Swing thread.
	 */
	protected OverarchInsert swingOverarchInsert = OverarchInsert.DEFAULT_INSERT_MODE;
	
	
	
	/**
	 * The script list on the SWT thread.
	 */
	HighLevelList<StdLowLevelList<FlexString>,FlexString> swtScriptLst = new HighLevelList<StdLowLevelList<FlexString>,FlexString>();
	
	/**
	 * The superscript list on the SWT thread.
	 */
	HighLevelList<StdLowLevelList<FlexString>,FlexString> swtSuperscriptLst = new HighLevelList<StdLowLevelList<FlexString>,FlexString>();
	
	/**
	 * The subscript list on the SWT thread.
	 */
	HighLevelList<StdLowLevelList<FlexString>,FlexString> swtSubscriptLst = new HighLevelList<StdLowLevelList<FlexString>,FlexString>();
	
	/**
	 * The overscript list on the SWT thread.
	 */
	HighLevelList<StdLowLevelList<FlexString>,FlexString> swtOverscriptLst = new HighLevelList<StdLowLevelList<FlexString>,FlexString>();
	
	/**
	 * The underscript list on the SWT thread.
	 */
	HighLevelList<StdLowLevelList<FlexString>,FlexString> swtUnderscriptLst = new HighLevelList<StdLowLevelList<FlexString>,FlexString>();
	
	
	
	/**
	 * The script list on the Swing thread.
	 */
	HighLevelList<StdLowLevelList<FlexString>,FlexString> swingScriptLst = new HighLevelList<StdLowLevelList<FlexString>,FlexString>();
	
	/**
	 * The superscript list on the Swing thread.
	 */
	HighLevelList<StdLowLevelList<FlexString>,FlexString> swingSuperscriptLst = new HighLevelList<StdLowLevelList<FlexString>,FlexString>();
	
	/**
	 * The subscript list on the Swing thread.
	 */
	HighLevelList<StdLowLevelList<FlexString>,FlexString> swingSubscriptLst = new HighLevelList<StdLowLevelList<FlexString>,FlexString>();
	
	/**
	 * The overscript list on the Swing thread.
	 */
	HighLevelList<StdLowLevelList<FlexString>,FlexString> swingOverscriptLst = new HighLevelList<StdLowLevelList<FlexString>,FlexString>();
	
	/**
	 * The underscript list on the Swing thread.
	 */
	HighLevelList<StdLowLevelList<FlexString>,FlexString> swingUnderscriptLst = new HighLevelList<StdLowLevelList<FlexString>,FlexString>();
	
	
	/**
	 * The display string on the Swing thread.
	 */
	FlexString swingDisplayString = new FlexString();
	
	/**
	 * Temporary test rendering string.
	 */
	FlexString swingRendString = new FlexString();
	
	/**
	 * Temporary use of regex.
	 */
	GenRegex genRegex = new GenRegex();
	
	/**
	 * The dirty flag on the SWT thread.
	 */
	boolean swtDirty = false;
	
	/**
	 * The dirty-mark callback for the SWT thread.
	 */
	IInitiateMarkDirty swtimd;
	
	

	/**
	 * Constructs the canvas.
	 * @param _imd The dirty-mark callback.
	 */
	public SymbolEditorCanvas( IInitiateMarkDirty _imd ) {
		super( true );
		swtimd = _imd;
	}
	
	
	/**
	 * Sets the SWT display.
	 * @param _display The display.
	 */
	public void setDisplay( Display _display )
	{
		display = _display;
		updateSwtInit( swingScriptLst,
				swingSuperscriptLst,
				swingSubscriptLst,
				swingOverscriptLst,
				swingUnderscriptLst );
	}
	
	
	/**
	 * Gets the dirty flag on the SWT thread.
	 * @return The dirty flag on the SWT thread.
	 */
	public boolean swtIsDirty()
	{
		return( swtDirty );
	}
	
	
	/**
	 * Sets the dirty flag on the SWT thread.
	 * @param in The dirty flag on the SWT thread.
	 */
	protected void swtSetDirty( boolean in )
	{
		boolean d = swtDirty;
		swtDirty = in;
		if( in != d )
		{
			swtimd.initiateMarkDirty();
		}
	}
	
	
	@Override
	public void paintComponent( Graphics gg )
	{
		super.paintComponent( gg );
		
		Graphics2D g = (Graphics2D) gg;
		
		g.setRenderingHint( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );
		// g.setRenderingHint( RenderingHints.KEY_RENDERING , RenderingHints.VALUE_COLOR_RENDER_QUALITY );
		
		
		swingDisplayString.drawString(g, 10, 20);
		
		swingRendString.drawString(g, 10, 50);
		
		
		Path2D.Double p = new Path2D.Double();
		
		p.moveTo( 30.0 , 30.0 );
		p.curveTo( 50.0 , 70.0 , 50.0 , 70.0 , 70.0 , 70.0 );
		
		g.draw( p );
		
	}
	
	
	/**
	 * The Drools knowledge base on the Swing thread.
	 */
	KnowledgeBase swingKnowledgeBase = null;
	
	/**
	 * Gets the Drools knowledge base on the Swing thread.
	 * @return The Drools knowledge base on the Swing thread.
	 */
	protected KnowledgeBase getSwingKnowledgeBase()
	{
		if( swingKnowledgeBase == null )
		{
			OptimizerFactory.setDefaultOptimizer( OptimizerFactory.SAFE_REFLECTIVE );
			
			KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
			
			builder.add( ResourceFactory.newClassPathResource( "algsymboleditor/editors/parse.drl" )  , 
					ResourceType.DRL );
			
			swingKnowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
			swingKnowledgeBase.addKnowledgePackages( builder.getKnowledgePackages() );
		}
		
		return( swingKnowledgeBase );
	}
	
	
	/**
	 * Handles the insertion of a symbol on the Swing thread.
	 * @param istr The symbol to insert.
	 * @param insMode The default insertion mode.
	 */
	public void handleInsertSwing( String istr , DefaultInsert insMode )
	{	
		final String dstr = insMode.handleInsert( istr );
		
		swingInsertMode.insertStringSwing( dstr , this );
		
		updateSwingDisplayString();
		
		repaint();
		updateSwtInsert( swingInsertMode , dstr );
	}
	
	
	/**
	 * Handles the deletion of the current symbol on the Swing thread.
	 */
	public void handleSymDeleteSwing( )
	{	
		swingInsertMode.deleteSymSwing( this );
		
		updateSwingDisplayString();
		
		repaint();
		updateSwtDeleteSym( swingInsertMode );
	}
	
	
	/**
	 * Handles a delete request on the Swing thread.
	 */
	public void handleDeleteSwing( )
	{	
		swingScriptLst.eraseAllInfo();
		
		swingSuperscriptLst.eraseAllInfo();
		
		swingSubscriptLst.eraseAllInfo();
		
		swingOverscriptLst.eraseAllInfo();
		
		swingUnderscriptLst.eraseAllInfo();
		
		updateSwingDisplayString();
		
		repaint();
		updateSwtDelete();
	}
	
	
	/**
	 * Handles a request to set the insertion mode on the Swing thread.
	 * @param in The desired insertion mode.
	 */
	public void handleInsertModeSwing( InsertModes in )
	{
		swingInsertMode = in;
	}
	
	
	/**
	 * Handles a request to set the Overarch Insert mode on the Swing thread.
	 * @param in The desired Overarch Insert mode.
	 */
	public void handleOverarchInsertSwing( OverarchInsert in )
	{
		swingOverarchInsert = in;
	}
	
	
	/**
	 * Updates the display string on the Swing thread.
	 */
	protected void updateSwingDisplayString()
	{
		swingDisplayString = genDisplayString( swingScriptLst,
				swingSuperscriptLst,
				swingSubscriptLst,
				swingOverscriptLst,
				swingUnderscriptLst );
		ParseNode<Integer> pnode = genRegex.parse( swingDisplayString );
		
		try
		{
			KnowledgeBase knowledgeBase = getSwingKnowledgeBase();
		
			StatefulKnowledgeSession session = knowledgeBase.newStatefulKnowledgeSession();
		
			session.insert( new DroolsSession( session ) );
		
			ParsePlaceholder<Integer> placeholder = new ParsePlaceholder<Integer>( pnode );
		
			session.insert( placeholder );
		
			System.out.println( "AA: " + placeholder.getElem() );
				
			session.fireAllRules();

			session.dispose();
		
			ParseNode<Integer> prev = null;
			ParseNode<Integer> nxt = placeholder.getElem();
		
			System.out.println( "BB: " + placeholder.getElem() );
		
			FlexString str = new FlexString();
			while( nxt != null )
			{
				if( nxt instanceof LiteralRendNode )
				{
					( (LiteralRendNode) nxt ).getStr().insertString( str );
				}
				nxt = nxt.next;
			}
			swingRendString = str;
		}
		catch( Throwable ex )
		{
			ex.printStackTrace( System.out );
		}
	}
	
	
	/**
	 * Handles a request to insert a symbol on the SWT thread.
	 * @param in The symbol to be inserted.
	 * @param str The default insert mode.
	 */
	protected void updateSwtInsert( final InsertModes in , final String str )
	{
		if( display != null )
		{
			display.asyncExec( new Runnable()
			{
				@Override
				public void run()
				{
					in.insertStringSwt( str , SymbolEditorCanvas.this );
					swtSetDirty( true );
				}
			} );
		}
	}
	
	
	/**
	 * Handles a request to delete the current symbol on the SWT thread.
	 * @param in The current insertion mode.
	 */
	protected void updateSwtDeleteSym( final InsertModes in )
	{
		if( display != null )
		{
			display.asyncExec( new Runnable()
			{
				@Override
				public void run()
				{
					in.deleteSymSwt( SymbolEditorCanvas.this );
					swtSetDirty( true );
				}
			} );
		}
	}
	
	
	/**
	 * Handles a request to delete on the SWT thread.
	 */
	protected void updateSwtDelete( )
	{
		if( display != null )
		{
			display.asyncExec( new Runnable()
			{
				@Override
				public void run()
				{
					swtScriptLst.eraseAllInfo();
					
					swtSuperscriptLst.eraseAllInfo();
					
					swtSubscriptLst.eraseAllInfo();
					
					swtOverscriptLst.eraseAllInfo();
					
					swtUnderscriptLst.eraseAllInfo();
					
					swtSetDirty( true );
				}
			} );
		}
	}
	
	
	/**
	 * Handles a request to initialize on the SWT thread.
	 * @param scriptLst The script list.
	 * @param superscriptLst The superscript list.
	 * @param subscriptLst The subscript list.
	 * @param overscriptLst The overscript list.
	 * @param underscriptLst The underscript list.
	 */
	protected void updateSwtInit( final HighLevelList<StdLowLevelList<FlexString>,FlexString> scriptLst,
		final HighLevelList<StdLowLevelList<FlexString>,FlexString> superscriptLst,
		final HighLevelList<StdLowLevelList<FlexString>,FlexString> subscriptLst,
		final HighLevelList<StdLowLevelList<FlexString>,FlexString> overscriptLst,
		final HighLevelList<StdLowLevelList<FlexString>,FlexString> underscriptLst )
	{
		if( display != null )
		{
			display.asyncExec( new Runnable()
			{
				@Override
				public void run()
				{
					swtScriptLst.eraseAllInfo();
					scriptLst.copyAllInfo( swtScriptLst );
					
					swtSuperscriptLst.eraseAllInfo();
					superscriptLst.copyAllInfo( swtSuperscriptLst );
					
					swtSubscriptLst.eraseAllInfo();
					subscriptLst.copyAllInfo( swtSubscriptLst );
					
					swtOverscriptLst.eraseAllInfo();
					overscriptLst.copyAllInfo( swtOverscriptLst );
					
					swtUnderscriptLst.eraseAllInfo();
					underscriptLst.copyAllInfo( swtUnderscriptLst );
					
					swtSetDirty( false );
				}
			} );
		}
	}
	
	
	/**
	 * Generates the combined string for the script list.
	 * @param scriptLst The input script list.
	 * @param retA The output string in which to insert.
	 */
	protected static void genScript( final HighLevelList<StdLowLevelList<FlexString>,FlexString> scriptLst,
			 final FlexString retA )
	{
			
		final boolean scripts = scriptLst.empty();
		
		if( scripts )
		{
			retA.insertJavaString( "&#9633;" );
		}
		else
		{
			AlgCommon.condenseText( scriptLst , retA );
		}
		
	}
	
	
	/**
	 * Generates the combined string for overscript, underscript, and script.
	 * @param scriptLst The script list.
	 * @param overscriptLst The overscript list.
	 * @param underscriptLst The underscript list.
	 * @param retA The output string in which to insert.
	 */
	protected static void genOverUnder( final HighLevelList<StdLowLevelList<FlexString>,FlexString> scriptLst,
			final HighLevelList<StdLowLevelList<FlexString>,FlexString> overscriptLst,
			final HighLevelList<StdLowLevelList<FlexString>,FlexString> underscriptLst , final FlexString retA )
	{
	
		final boolean overs = overscriptLst.empty();
	
		final boolean unders = underscriptLst.empty();
		
			
		if( overs )
		{
			
			if( unders )
			{
				genScript( scriptLst , retA );
			}
			else
			{
				retA.insertJavaString( "<munder>" );
				genScript( scriptLst , retA );
				AlgCommon.condenseText( underscriptLst , retA );
				retA.insertJavaString( "</munder>" );
			}
			
		}
		else
		{
			
			if( unders )
			{
				retA.insertJavaString( "<mover>" );
				genScript( scriptLst , retA );
				AlgCommon.condenseText( overscriptLst , retA );
				retA.insertJavaString( "</mover>" );
			}
			else
			{
				retA.insertJavaString( "<munderover>" );
				genScript( scriptLst , retA );
				AlgCommon.condenseText( underscriptLst , retA );
				AlgCommon.condenseText( overscriptLst , retA );
				retA.insertJavaString( "</munderover>" );
			}
			
		}
		
	}
	
	
	/**
	 * Generates the combined display string.
	 * @param scriptLst The script list.
	 * @param superscriptLst The superscript list.
	 * @param subscriptLst The subscript list.
	 * @param overscriptLst The overscript list.
	 * @param underscriptLst The underscript list.
	 * @return The output string in which to insert.
	 */
	protected static FlexString genDisplayString( final HighLevelList<StdLowLevelList<FlexString>,FlexString> scriptLst,
			final HighLevelList<StdLowLevelList<FlexString>,FlexString> superscriptLst,
			final HighLevelList<StdLowLevelList<FlexString>,FlexString> subscriptLst,
			final HighLevelList<StdLowLevelList<FlexString>,FlexString> overscriptLst,
			final HighLevelList<StdLowLevelList<FlexString>,FlexString> underscriptLst )
		{
		
			final boolean subs = subscriptLst.empty();
		
			final boolean supers = superscriptLst.empty();
			
			
			
			FlexString retB = new FlexString();
			retB.setInsertPoint( 0 );
			
			
			
			if( subs )
			{
				
				if( supers )
				{
					genOverUnder( scriptLst, overscriptLst, underscriptLst , retB );
				}
				else
				{
					retB.insertJavaString( "<msup>" );
					genOverUnder( scriptLst, overscriptLst, underscriptLst , retB );
					AlgCommon.condenseText( superscriptLst , retB );
					retB.insertJavaString( "</msup>" );
				}
				
			}
			else
			{
				
				if( supers )
				{
					retB.insertJavaString( "<msub>" );
					genOverUnder( scriptLst, overscriptLst, underscriptLst , retB );
					AlgCommon.condenseText( subscriptLst , retB );
					retB.insertJavaString( "</msub>" );
				}
				else
				{
					retB.insertJavaString( "<msubsup>" );
					genOverUnder( scriptLst, overscriptLst, underscriptLst , retB );
					AlgCommon.condenseText( subscriptLst , retB );
					AlgCommon.condenseText( superscriptLst , retB );
					retB.insertJavaString( "</msubsup>" );
				}
				
			}
			
			
			return( retB );
			
		}
	
	
	
	
	/**
	 * Handles an insertion from the SWT thread.
	 * @param istr The symbol to be inserted.
	 * @param insMode The default insertion mode.
	 */
	public void handleInsertSwt( final String istr , final DefaultInsert insMode )
	{
		SwingUtilities.invokeLater( new Runnable()
		{
			@Override
			public void run()
			{
				handleInsertSwing( istr , insMode );
			}
		});
	}
	
	
	
	/**
	 * Handles a deletion on the SWT thread.
	 */
	public void handleSymDeleteSwt( )
	{
		SwingUtilities.invokeLater( new Runnable()
		{
			@Override
			public void run()
			{
				handleSymDeleteSwing( );
			}
		});
	}
	
	
	
	/**
	 * Handles the setting of the insertion mode on the SWT thread.
	 * @param in The desired insertion mode.
	 */
	public void handleInsertModeSwt( final InsertModes in )
	{
		SwingUtilities.invokeLater( new Runnable()
		{
			@Override
			public void run()
			{
				handleInsertModeSwing( in );
			}
		});
	}
	
	
	/**
	 * Handles the setting of the Overarch Insert mode on the SWT thread.
	 * @param in The desired insertion mode.
	 */
	public void handleOverarchInsertSwt( final OverarchInsert in )
	{
		SwingUtilities.invokeLater( new Runnable()
		{
			@Override
			public void run()
			{
				handleOverarchInsertSwing( in );
			}
		});
	}
	
	
	/**
	 * Handles a deletion request on the SWT thread.
	 */
	public void handleDeleteSwt()
	{
		SwingUtilities.invokeLater( new Runnable()
		{
			@Override
			public void run()
			{
				handleDeleteSwing( );
			}
		});
	}
	
	
	/**
	 * Handles a request to copy to the clipboard on the SWT thread.
	 * @param clipboard The clipboard.
	 */
	public void handleCopySwt( Clipboard clipboard )
	{
		TextTransfer transfer;
		final FlexString swtStr = genDisplayString( swtScriptLst,
				swtSuperscriptLst,
				swtSubscriptLst,
				swtOverscriptLst,
				swtUnderscriptLst );
		final String[] data = { AlgCommon.MATCH_STR + swtStr.exportString() };
		System.out.println( data );
		clipboard.setContents( data /* new Object[] { data } */ , 
				new Transfer[] { TextTransfer.getInstance() } );
	}
	
	
	/**
	 * Handles a request to paste from the clipboard on the SWT thread.
	 * @param clipboard The clipboard.
	 */
	public void handlePasteSwt( Clipboard clipboard )
	{
		System.out.println( "Entered Paste" );
		String str = (String)( clipboard.getContents( TextTransfer.getInstance() ) );
		if( str.indexOf( AlgCommon.MATCH_STR )  == 0 )
		{
			str = str.substring( ( AlgCommon.MATCH_STR ).length() );
			handleInsertSwt( str , DefaultInsert.NO_INSERT_MODE );
		}
		else
		{
			System.out.println( "Mismatch" );
		}
	}
	
	
	/**
	 * Writes content to persistence on the SWT thread.
	 * @param file The file to which to save.
	 * @param overwrite Indicates whether overwrites are permitted.
	 * @param monitor The progress monitor for monitoring the save.
	 * @throws CoreException
	 */
	public void writeContent( IFile file , boolean overwrite, IProgressMonitor monitor ) throws CoreException
	{
		// Overwrite !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			writeAlgSymbolEditor( swtScriptLst,
					swtSuperscriptLst, swtSubscriptLst, swtOverscriptLst, swtUnderscriptLst, baos);
			ByteArrayInputStream stream = new ByteArrayInputStream( baos.toByteArray() );
			if( !( file.exists() ) )
			{
				file.create(stream, IFile.NONE, monitor);
			}
			else
			{
				file.setContents(stream, IFile.NONE, monitor);
			}
			swtSetDirty( false );
		}
		catch( IOException ex )
		{
			ex.printStackTrace( System.out );
			throw( new RuntimeException( ex.toString() ) );
		}
	}
	
	
	/**
	 * Reads persistent content at initialization.  Because it's at initialization, thread considerations do not matter.
	 * @param is Input stream from persistence.
	 */
	public void readContent( InputStream is /*, IProgressMonitor monitor */ )
	{	
		try {
			
		readAlgSymbolEditor(is, swingScriptLst,
				swingSuperscriptLst,
				swingSubscriptLst,
				swingOverscriptLst,
				swingUnderscriptLst );
		
		updateSwingDisplayString();
		
		updateSwtInit( swingScriptLst,
				swingSuperscriptLst,
				swingSubscriptLst,
				swingOverscriptLst,
				swingUnderscriptLst );
		
		}
		catch( Throwable ex )
		{
			ex.printStackTrace( System.out );
		}
		
	}


	@Override
	public Dimension getPreferredScrollableViewportSize() {
		return( new Dimension( 5000 , 1000 ) );
	}


	@Override
	public int getScrollableBlockIncrement(Rectangle arg0, int arg1, int arg2) {
		return( 100 );
	}


	@Override
	public boolean getScrollableTracksViewportHeight() {
		return( true );
	}


	@Override
	public boolean getScrollableTracksViewportWidth() {
		return( false );
	}


	@Override
	public int getScrollableUnitIncrement(Rectangle arg0, int arg1, int arg2) {
		return( 1 );
	}
	
	
	
	/**
	 * Writes content to persistence.
	 * @param scriptLst Script list.
	 * @param superscriptLst Superscript list.
	 * @param subscriptLst Subscript list.
	 * @param overscriptLst Overscript list.
	 * @param underscriptLst Underscript list.
	 * @param os Output stream to persistent store.
	 * @throws IOException
	 */
	public static void writeAlgSymbolEditor( 
			final HighLevelList<StdLowLevelList<FlexString>,FlexString> scriptLst,
			final HighLevelList<StdLowLevelList<FlexString>,FlexString> superscriptLst,
			final HighLevelList<StdLowLevelList<FlexString>,FlexString> subscriptLst,
			final HighLevelList<StdLowLevelList<FlexString>,FlexString> overscriptLst,
			final HighLevelList<StdLowLevelList<FlexString>,FlexString> underscriptLst,
			final OutputStream os ) throws IOException
	{
		ExXMLEncoder enc = new ExXMLEncoder( os );
		
		VersionBuffer vb = new VersionBuffer( true );
		
		
		vb.setProperty( "scriptLst" , scriptLst );
		vb.setProperty( "superscriptLst" , superscriptLst  );
		vb.setProperty( "subscriptLst" , subscriptLst );
		vb.setProperty( "overscriptLst" , overscriptLst );
		vb.setProperty( "underscriptLst" , underscriptLst );
		
		
		enc.writeObject( vb );
		
		enc.close();
	}
	
	
	/**
	 * Reads content from persistence.
	 * @param is Input stream from persistent store.
	 * @param scriptLst Script list.
	 * @param superscriptLst Superscript list.
	 * @param subscriptLst Subscript list.
	 * @param overscriptLst Overscript list.
	 * @param underscriptLst Underscript list.
	 * @throws IOException
	 */
	public static void readAlgSymbolEditor( 
			final InputStream is,
			final HighLevelList<StdLowLevelList<FlexString>,FlexString> scriptLst,
			final HighLevelList<StdLowLevelList<FlexString>,FlexString> superscriptLst,
			final HighLevelList<StdLowLevelList<FlexString>,FlexString> subscriptLst,
			final HighLevelList<StdLowLevelList<FlexString>,FlexString> overscriptLst,
			final HighLevelList<StdLowLevelList<FlexString>,FlexString> underscriptLst ) throws IOException
	{
		XMLDecoder enc = new XMLDecoder( is );
		
		VersionBuffer vb = (VersionBuffer)( enc.readObject() );
		
		
		final HighLevelList<StdLowLevelList<FlexString>,FlexString> scriptLs = 
				(HighLevelList<StdLowLevelList<FlexString>,FlexString>)( vb.getPropertyEx( "scriptLst" ) );
		scriptLs.copyDataPlusPtrInfo( scriptLst );
		
		
		final HighLevelList<StdLowLevelList<FlexString>,FlexString> superscriptLs = 
				(HighLevelList<StdLowLevelList<FlexString>,FlexString>)( vb.getPropertyEx( "superscriptLst" ) );
		superscriptLs.copyDataPlusPtrInfo( superscriptLst );
		
		
		final HighLevelList<StdLowLevelList<FlexString>,FlexString> subscriptLs = 
				(HighLevelList<StdLowLevelList<FlexString>,FlexString>)( vb.getPropertyEx( "subscriptLst" ) );
		subscriptLs.copyDataPlusPtrInfo( subscriptLst );
		
		
		final HighLevelList<StdLowLevelList<FlexString>,FlexString> overscriptLs = 
				(HighLevelList<StdLowLevelList<FlexString>,FlexString>)( vb.getPropertyEx( "overscriptLst" ) );
		overscriptLs.copyDataPlusPtrInfo( overscriptLst );
		
		
		final HighLevelList<StdLowLevelList<FlexString>,FlexString> underscriptLs = 
				(HighLevelList<StdLowLevelList<FlexString>,FlexString>)( vb.getPropertyEx( "underscriptLst" ) );
		underscriptLs.copyDataPlusPtrInfo( underscriptLst );
		
		
		enc.close();
	}
	

	
	
}


