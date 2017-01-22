






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

import java.awt.*;
import java.awt.font.FontRenderContext;
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

import algsymboleditor.editors.SymbolEditorCanvas.InsertModes;
import simplealgebra.DoubleElem;
import simplealgebra.symbolic.DroolsSession;
import meta.*;


/**
 * Canvas for rendering edits of mfenced entities.
 * 
 * @author tgreen
 *
 */
public class FencedEditorCanvas extends JPanel implements Scrollable {
	
	
	/**
	 * Enumerated insertion modes.
	 * @author tgreen
	 *
	 */
	public static enum InsertModes
	{
		
		/**
		 * Circular paren insert.
		 */
		CIRC_MODE
		{
			@Override
			void insertStringSwing( String str , FencedEditorCanvas canvas )
			{
				AlgCommon.performLstInsert( str, canvas.swingFencescriptLst );
			}
			
			@Override
			void deleteSymSwing( FencedEditorCanvas canvas )
			{
				AlgCommon.performDelete( canvas.swingFencescriptLst );
			}
			
			@Override
			void insertStringSwt( String str , FencedEditorCanvas canvas )
			{
				AlgCommon.performLstInsert( str, canvas.swtFencescriptLst );
			}
			
			@Override
			void deleteSymSwt( FencedEditorCanvas canvas )
			{
				AlgCommon.performDelete( canvas.swtFencescriptLst );
			}

			@Override
			String getTypeString() {
				return( "<mfenced open=\"(\" close=\")\">" );
			}
			
		},
		
		/**
		 * Square paren insert.
		 */
		SQUARE_MODE
		{
			@Override
			void insertStringSwing( String str , FencedEditorCanvas canvas )
			{
				AlgCommon.performLstInsert( str, canvas.swingFencescriptLst );
			}
			
			@Override
			void deleteSymSwing( FencedEditorCanvas canvas )
			{
				AlgCommon.performDelete( canvas.swingFencescriptLst );
			}
			
			@Override
			void insertStringSwt( String str , FencedEditorCanvas canvas )
			{
				AlgCommon.performLstInsert( str, canvas.swtFencescriptLst );
			}
			
			@Override
			void deleteSymSwt( FencedEditorCanvas canvas )
			{
				AlgCommon.performDelete( canvas.swtFencescriptLst );
			}
			
			@Override
			String getTypeString() {
				return( "<mfenced open=\"[\" close=\"]\">" );
			}
			
		},
		
		/**
		 * Curly brace insert.
		 */
		CURLY_MODE
		{
			@Override
			void insertStringSwing( String str , FencedEditorCanvas canvas )
			{
				AlgCommon.performLstInsert( str, canvas.swingFencescriptLst );
			}
			
			@Override
			void deleteSymSwing( FencedEditorCanvas canvas )
			{
				AlgCommon.performDelete( canvas.swingFencescriptLst );
			}
			
			@Override
			void insertStringSwt( String str , FencedEditorCanvas canvas )
			{
				AlgCommon.performLstInsert( str, canvas.swtFencescriptLst );
			}
			
			@Override
			void deleteSymSwt( FencedEditorCanvas canvas )
			{
				AlgCommon.performDelete( canvas.swtFencescriptLst );
			}
			
			@Override
			String getTypeString() {
				return( "<mfenced open=\"{\" close=\"}\">" );
			}
			
		},
		
		/**
		 * Vertical brace insert.
		 */
		VERT_MODE
		{
			@Override
			void insertStringSwing( String str , FencedEditorCanvas canvas )
			{
				AlgCommon.performLstInsert( str, canvas.swingFencescriptLst );
			}
			
			@Override
			void deleteSymSwing( FencedEditorCanvas canvas )
			{
				AlgCommon.performDelete( canvas.swingFencescriptLst );
			}
			
			@Override
			void insertStringSwt( String str , FencedEditorCanvas canvas )
			{
				AlgCommon.performLstInsert( str, canvas.swtFencescriptLst );
			}
			
			@Override
			void deleteSymSwt( FencedEditorCanvas canvas )
			{
				AlgCommon.performDelete( canvas.swtFencescriptLst );
			}
			
			@Override
			String getTypeString() {
				return( "<mfenced open=\"|\" close=\"|\">" );
			}
			
		};
		
		
		/**
		 * Inserts a symbol on the Swing thread.
		 * @param str The symbol to insert.
		 * @param canvas The canvas on which to insert the symbol.
		 */
		abstract void insertStringSwing( String str , FencedEditorCanvas canvas );
		
		/**
		 * Deletes a symbol on the Swing thread.
		 * @param canvas The canvas on which to delete the symbol.
		 */
		abstract void deleteSymSwing( FencedEditorCanvas canvas );
		
		/**
		 * Inserts a symbol on the SWT thread.
		 * @param str The symbol to insert.
		 * @param canvas The canvas on which to insert the symbol.
		 */
		abstract void insertStringSwt( String str , FencedEditorCanvas canvas );
		
		/**
		 * Deletes a symbol on the SWT thread.
		 * @param canvas The canvas on which to delete the symbol.
		 */
		abstract void deleteSymSwt( FencedEditorCanvas canvas );
		
		/**
		 * Returns the markup string for the insertion type.
		 * @return The markup string.
		 */
		abstract String getTypeString();
		
		
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
	protected InsertModes swingInsertMode = InsertModes.CIRC_MODE;
	
	/**
	 * The current insertion mode on the SWT thread.
	 */
	protected InsertModes swtInsertMode = InsertModes.CIRC_MODE;
	
	/**
	 * The current Overarch Insert mode on the Swing thread.
	 */
	protected OverarchInsert swingOverarchInsert = OverarchInsert.DEFAULT_INSERT_MODE;
	
	
	
	/**
	 * The fencescript list on the SWT thread.
	 */
	HighLevelList<StdLowLevelList<FlexString>,FlexString> swtFencescriptLst = new HighLevelList<StdLowLevelList<FlexString>,FlexString>();
	
	
	
	/**
	 * The fencescript list on the Swing thread.
	 */
	HighLevelList<StdLowLevelList<FlexString>,FlexString> swingFencescriptLst = new HighLevelList<StdLowLevelList<FlexString>,FlexString>();
	
	
	/**
	 * The display string on the Swing thread.
	 */
	FlexString swingDisplayString = new FlexString();
	
	/**
	 * Temporary test rendering string.
	 */
	ParseNode swingRendNode = null;
	
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
	public FencedEditorCanvas( IInitiateMarkDirty _imd ) {
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
		updateSwtInit( swingFencescriptLst );
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
		
		
		Font inFont = null;
		double fontSz = 32;
		FontRenderContext tempFrc = null;
		
		
		int yy = 50;
		ParseNode nxt = swingRendNode;
		while( nxt != null )
		{
			if( nxt instanceof ParseRendNode )
			{
				ParseRendNode pr = (ParseRendNode) nxt;
				if( pr.getConnRect() == null )
				{
					if( inFont == null )
					{
						Font f = gg.getFont();
						inFont = new Font( f.getName() , f.getStyle() , (int) fontSz );
						g.setFont( inFont );
						tempFrc = g.getFontRenderContext();
					}
					pr.calcRects(inFont, fontSz, tempFrc);
				}
				pr.draw(g, 10, yy - pr.getImgRect().y );
				yy += 10 + pr.getImgRect().height - pr.getImgRect().height ;
			}
			nxt = nxt.next;
		}
		
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
		swingFencescriptLst.eraseAllInfo();
		
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
		
		updateSwingDisplayString();
		
		repaint();
		updateSwtInsertMode( swingInsertMode );
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
		swingDisplayString = genDisplayString( swingFencescriptLst , swingInsertMode );
		ParseNode pnode = genRegex.parse( swingDisplayString );
		
		try
		{
			KnowledgeBase knowledgeBase = getSwingKnowledgeBase();
		
			StatefulKnowledgeSession session = knowledgeBase.newStatefulKnowledgeSession();
		
			session.insert( new DroolsSession( session ) );
		
			ParsePlaceholder placeholder = new ParsePlaceholder( pnode );
		
			session.insert( placeholder );
			
			{
				ParseNode rnode = pnode;
				while( rnode != null )
				{
					session.insert( rnode );
					rnode = rnode.next;
				}
			}
		
			System.out.println( "AA: " + placeholder.getElem() );
				
			session.fireAllRules();

			session.dispose();
		
			ParseNode prev = null;
			ParseNode nxt = placeholder.getElem();
		
			System.out.println( "BB: " + placeholder.getElem() );
		
			swingRendNode = nxt;
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
					in.insertStringSwt( str , FencedEditorCanvas.this );
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
					in.deleteSymSwt( FencedEditorCanvas.this );
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
					swtFencescriptLst.eraseAllInfo();
					
					swtSetDirty( true );
				}
			} );
		}
	}
	
	
	/**
	 * Handles a request to update the insert mode on the SWT thread.
	 * @param insertMode The input insert mode.
	 */
	protected void updateSwtInsertMode( final InsertModes insertMode  )
	{
		if( display != null )
		{
			display.asyncExec( new Runnable()
			{
				@Override
				public void run()
				{
					swtInsertMode = insertMode;
				}
			} );
		}
	}
	
	
	
	/**
	 * Handles a request to initialize on the SWT thread.
	 * @param overscriptLst The overscript list.
	 */
	protected void updateSwtInit( final HighLevelList<StdLowLevelList<FlexString>,FlexString> fencedscriptLst )
	{
		if( display != null )
		{
			display.asyncExec( new Runnable()
			{
				@Override
				public void run()
				{
					swtFencescriptLst.eraseAllInfo();
					fencedscriptLst.copyAllInfo( swtFencescriptLst );
					
					swtSetDirty( false );
				}
			} );
		}
	}
	
	
	/**
	 * Generates the combined display string.
	 * @param overscriptLst The overscript list.
	 * @return The output string in which to insert.
	 */
	protected static FlexString genDisplayString( final HighLevelList<StdLowLevelList<FlexString>,FlexString> fencedscriptLst , final InsertModes typeMode )
		{
		
			final boolean fenceds = fencedscriptLst.empty();
			
			
			FlexString ret = new FlexString();
			ret.setInsertPoint( 0 );
			
			
			ret.insertJavaString( typeMode.getTypeString() );
			
			
			if( fenceds )
			{
				ret.insertJavaString( "&#9633;" );
			}
			else
			{
				AlgCommon.condenseText( fencedscriptLst , ret );
			}
			
			
			ret.insertJavaString( "</mfenced>" );
			
			
			return( ret );
			
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
		final FlexString swtStr = genDisplayString( swtFencescriptLst , swtInsertMode );
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
			writeAlgFencedEditor( swtFencescriptLst, baos);
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
	public void readContent( InputStream is /* , IProgressMonitor monitor */ )
	{	
		try {
			
		readAlgFencedEditor(is, swingFencescriptLst);
		
		updateSwingDisplayString();
		
		updateSwtInit( swingFencescriptLst );
		
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
	 * @param overscriptLst Overscript list.
	 * @param os Output stream to persistent store.
	 * @throws IOException
	 */
	public static void writeAlgFencedEditor( 
			final HighLevelList<StdLowLevelList<FlexString>,FlexString> fencedscriptLst,
			final OutputStream os ) throws IOException
	{
		ExXMLEncoder enc = new ExXMLEncoder( os );
		
		VersionBuffer vb = new VersionBuffer( true );
		
		
		vb.setProperty( "fencedscriptLst" , fencedscriptLst );
		
		
		enc.writeObject( vb );
		
		enc.close();
	}
	
	
	/**
	 * Reads content from persistence.
	 * @param is Input stream from persistent store.
	 * @param overscriptLst Overscript list.
	 * @param underscriptLst Underscript list.
	 * @throws IOException
	 */
	public static void readAlgFencedEditor( 
			final InputStream is,
			final HighLevelList<StdLowLevelList<FlexString>,FlexString> fencedscriptLst ) throws IOException
	{
		XMLDecoder enc = new XMLDecoder( is );
		
		VersionBuffer vb = (VersionBuffer)( enc.readObject() );
		
		
		final HighLevelList<StdLowLevelList<FlexString>,FlexString> fencedscriptLs = 
				(HighLevelList<StdLowLevelList<FlexString>,FlexString>)( vb.getPropertyEx( "fencedscriptLst" ) );
		fencedscriptLs.copyDataPlusPtrInfo( fencedscriptLst );
		
		
		enc.close();
	}
	


	
	
}


