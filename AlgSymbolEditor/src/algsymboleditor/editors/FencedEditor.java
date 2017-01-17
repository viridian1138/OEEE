






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


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JApplet;
import javax.swing.JScrollPane;

import java.io.File;
import java.net.URI;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.URIUtil;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;


/**
 * Editor for MathML Fenceds.
 * @author tgreen
 *
 */
public class FencedEditor extends EditorPart implements IInitiateMarkDirty {
	
	
	/**
	 * Canvas for rendering the Fenced.
	 */
	protected FencedEditorCanvas canvas = null;

	 
	@Override
	public void doSaveAs() {
		SaveAsDialog saveAsDialog = new SaveAsDialog( getSite().getShell() );
		saveAsDialog.setOriginalFile( getFileInput() );
		saveAsDialog.open();
		
		IPath path = saveAsDialog.getResult();
		if( path != null )
		{
			IProgressMonitor pm = this.getEditorSite().getActionBars().getStatusLineManager().getProgressMonitor();
			performSaveAs( path , pm );
		}
	}
	
	
	/**
	 * Gets the file from the current IEditorInput, or null if no such file exists.
	 * @return The file from the current IEditorInput, or null if no such file exists.
	 */
	protected IFile getFileInput()
	{
		final IEditorInput input = getEditorInput();
		
		if( input instanceof IFileEditorInput )
		{
			return( ( (IFileEditorInput) input ).getFile() );
		}
		
		if( input instanceof IURIEditorInput )
		{
			URI uri = ((IURIEditorInput)input).getURI();
			IPath path = uri == null ? null : new Path( uri.toString() );
			IFile file = path == null ? null : ResourcesPlugin.getWorkspace().getRoot().getFileForLocation( path );
			return( file );
		}
		
		return( null );
	}

	
	/**
	 * Performs a save as and reports the result state back to the
	 * given progress monitor.
	 *
	 * @param path The default path at which to perform the save.
	 * @param progressMonitor the progress monitor for communicating result state or <code>null</code>
	 */
	protected void performSaveAs(IPath path, IProgressMonitor progressMonitor) {
		
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile( path );
		IEditorInput newInput = new FileEditorInput( file );
		
		boolean success= false;
		try {
			performSave(newInput, true, progressMonitor);
			success= true;

		} catch (CoreException x) {
			final IStatus status= x.getStatus();
			if (status == null || status.getSeverity() != IStatus.CANCEL) {
				Shell shell= PlatformUI.getWorkbench().getModalDialogShellProvider().getShell();
				String title= "Save As Error";
				String msg= "Error Saving " + ( x.getMessage() );
				MessageDialog.openError(shell, title, msg);
			}
		} finally {
			if (success)
				setInput(newInput);
		}

		if (progressMonitor != null)
			progressMonitor.setCanceled(!success);
	}

	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		try
		{
			performSave(getEditorInput(), false, progressMonitor);
		}
		catch( CoreException x )
		{
			Shell shell= PlatformUI.getWorkbench().getModalDialogShellProvider().getShell();
			String title= "Save As Error";
			String msg= "Error Saving " + ( x.getMessage() );
			MessageDialog.openError(shell, title, msg);
		}
	}
	
	/**
	 * Performs the save and handles errors appropriately.
	 *
	 * @param input The data to save.
	 * @param overwrite Indicates whether or not overwriting is allowed
	 * @param progressMonitor The monitor in which to run the operation
	 */
	protected void performSave(IEditorInput input, boolean overwrite, IProgressMonitor progressMonitor) throws CoreException {
		
			if( input instanceof IFileEditorInput )
			{
				IFileEditorInput fileInput = (IFileEditorInput) input;
				IFile file = fileInput.getFile();
				canvas.writeContent(file, overwrite, progressMonitor);
			}
			else
			{
				throw( new RuntimeException( "Failed.  No FileEditorInput." ) );
			}
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		
		canvas = new FencedEditorCanvas( this );
		
		
		try
		{
			if( input != null )
			{
				boolean located = false;
			
				if( input instanceof IStorageEditorInput )
				{
			   
					// IProgressMonitor pm = null; // this.getEditorSite().getActionBars().getStatusLineManager().getProgressMonitor();
					canvas.readContent( ( (IStorageEditorInput) input ).getStorage().getContents() /* , pm */ );
					located = true;
				}
			
			
				if( !located )
				{
					throw( new RuntimeException( "Wrong Input" ) );
				}
			
			}
		}
		catch( CoreException ex )
		{
			ex.printStackTrace( System.out );
			throw( new RuntimeException( "Failed." ) );
		}
		
		setSite( site );
		
		setInput( input );
		
		String pname = "Algebra Fenced Editor";
		
		if( input instanceof IPathEditorInput )
		{
			final String nname = ( (IPathEditorInput) input ).getPath().lastSegment();
			if( nname != null )
			{
				pname = nname;
			}
		}
		else
		{
			if( input instanceof IURIEditorInput )
			{
				final String nname = ( (IURIEditorInput) input ).getName();
				if( nname != null )
				{
					pname = nname;
				}
			}
		}
		
		setPartName( pname );
		
	}

	@Override
	public boolean isDirty() {
		if( canvas != null )
		{
			return( canvas.swtIsDirty() );
		}
		return( false );
	}
	
	@Override
	public void initiateMarkDirty()
	{
		firePropertyChange( PROP_DIRTY );
	}

	@Override
	public boolean isSaveAsAllowed() {
		return( true );
	}
	
	/**
	 * The composite control for the user interface.
	 */
	protected Composite composite = null;

	
	@Override
	public void createPartControl(Composite parent) {
		
		composite = new Composite( parent , SWT.EMBEDDED | SWT.NO_BACKGROUND );
		final Frame frame = SWT_AWT.new_Frame( composite );
		final JApplet applet = new JApplet();
		frame.add( applet );
		
		final JScrollPane jsp = new JScrollPane( canvas , 
				JScrollPane.VERTICAL_SCROLLBAR_NEVER , JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
		
		canvas.setPreferredSize( new Dimension( 5000 , 1000 ) );
		
		applet.setLayout( new BorderLayout( 0 , 0 ) );
		applet.add( jsp , BorderLayout.CENTER );
		
		composite.addKeyListener( new KeyListener()
		{

			@Override
			public void keyPressed(KeyEvent e) {
				handleKeyPressed( e );
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// Ignored				
			}
			
		} );
		
		
		
		IHandlerService serv = (IHandlerService)( getSite().getService( IHandlerService.class ) );
		
		
		serv.activateHandler( IWorkbenchCommandConstants.EDIT_COPY ,
				new AbstractHandler()
		{

			@Override
			public Object execute(ExecutionEvent event)
					throws ExecutionException {
				handleCopy();
				return( null );
			}
			
		} );
		
		
		
		serv.activateHandler( IWorkbenchCommandConstants.EDIT_PASTE ,
				new AbstractHandler()
		{

			@Override
			public Object execute(ExecutionEvent event)
					throws ExecutionException {
				handlePaste();
				return( null );
			}
			
		} );
		
		
		
		serv.activateHandler( IWorkbenchCommandConstants.EDIT_DELETE ,
				new AbstractHandler()
		{

			@Override
			public Object execute(ExecutionEvent event)
					throws ExecutionException {
				handleDelete();
				return( null );
			}
			
		} );
		
		
		
		serv.activateHandler( IWorkbenchCommandConstants.EDIT_CUT ,
				new AbstractHandler()
		{

			@Override
			public Object execute(ExecutionEvent event)
					throws ExecutionException {
				handleCut();
				return( null );
			}
			
		} );
		
		
		
		Display d = parent.getDisplay();
		if( AlgCommon.clipboard == null ) AlgCommon.clipboard = new Clipboard( d );
		
		
		canvas.setDisplay( d );
		
		
	}
	
	
	
	/**
	 * Handles a request to copy to the clipboard.
	 */
	protected void handleCopy()
	{
		canvas.handleCopySwt( AlgCommon.clipboard );
	}
	
	
	/**
	 * Handles a request to paste from the clipboard.
	 */
	protected void handlePaste()
	{
		canvas.handlePasteSwt( AlgCommon.clipboard );
	}
	
	
	/**
	 * Handles a request to delete the current content.
	 */
	protected void handleDelete()
	{
		canvas.handleDeleteSwt( );
	}
	
	
	/**
	 * Handles a request to cut to the clipboard.
	 */
	protected void handleCut()
	{
		canvas.handleCopySwt( AlgCommon.clipboard );
		canvas.handleDeleteSwt( );
	}
	
	
	/**
	 * Handles a key-press enent.
	 * @param e The key-press event.
	 */
	protected void handleKeyPressed( final KeyEvent e )
	{
		
		final char ch = e.character;
		
		if( ch >= 'a' && ch <= 'z' )
		{
			handleInsert( "" + ch , FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			return;
		}
		
		if( ch >= 'A' && ch <= 'Z' )
		{
			handleInsert( "" + ch , FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			return;
		}
		
		if( ch >= '0' && ch <= '9' )
		{
			handleInsert( "" + ch , FencedEditorCanvas.DefaultInsert.NUMBER_INSERT_MODE  );
			return;
		}
		
		System.out.println( e.keyCode );
		
		if( ( e.keyCode == SWT.BS ) || ( e.keyCode == SWT.DEL ) )
		{
			handleSymDelete();
		}
		
		switch( ch )
		{
			case '+':
				handleInsert( "+" , FencedEditorCanvas.DefaultInsert.OPERATOR_INSERT_MODE );
				return;
				
			case '-':	
				handleInsert( "-" , FencedEditorCanvas.DefaultInsert.OPERATOR_INSERT_MODE );
				return;
				
			case '<':
				handleInsert( "&lt;" , FencedEditorCanvas.DefaultInsert.OPERATOR_INSERT_MODE );
				return;
				
			case '>':
				handleInsert( "&gt;" , FencedEditorCanvas.DefaultInsert.OPERATOR_INSERT_MODE );
				return;
		}
		
		
	}
	

	
	@Override
	public void setFocus() {
		final boolean focus = composite.setFocus();
		if( !focus )
		{
			System.out.println( "Unable To Gain Focus" );
		}
	}
	
	
	/**
	 * Handles a request to delete the current symbol.
	 */
	protected void handleSymDelete()
	{
		canvas.handleSymDeleteSwt();
	}
	
	
	/**
	 * Handles a request to insert a symbol.
	 * @param str The string for the symbol to insert.
	 * @param insMode The default insertion mode for the symbol.
	 */
	public void handleInsert( String str , FencedEditorCanvas.DefaultInsert insMode )
	{
		canvas.handleInsertSwt( str , insMode );
	}
	
	
	/**
	 * Handles a request to set the insertion mode.
	 * @param in The desired insertion mode.
	 */
	public void handleInsertMode( FencedEditorCanvas.InsertModes in )
	{
		canvas.handleInsertModeSwt( in );
	}
	
	
	/**
	 * Handles a request to set the Overarch Insert.
	 * @param in The desired Overarch Insert to set.
	 */
	public void handleOverarchInsert( FencedEditorCanvas.OverarchInsert in )
	{
		canvas.handleOverarchInsertSwt( in );
	}

	
	
}



