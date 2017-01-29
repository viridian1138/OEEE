






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

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;
 

/**
 * Manages the installation/deinstallation of global actions for Alg Fenced editors.
 * Responsible for the redirection of global actions to the active editor.
 */
public class FencedEditorContributor extends MultiPageEditorActionBarContributor {
	private IEditorPart activeEditorPart;
	
	private Action circAction;
	private Action squareAction;
	private Action curlyAction;
	private Action vertAction;
	
	/**
	 * Action to dump the current expression to the console.
	 */
	private Action dumpTextToConsoleAction;
	
	private Action overarchInsertDefaultAction;
	private Action overarchInsertNoneAction;
	private Action overarchInsertIdentifierAction;
	private Action overarchInsertOperatorAction;
	private Action overarchInsertNumberAction;
	
	/**
	 * Action for inserting a plus "+" symbol.
	 */
	private Action plusAction;
	
	/**
	 * Action for inserting a minus "-" symbol.
	 */
	private Action minusAction;
	
	/**
	 * Action for inserting a cross-product or multiplication symbol.
	 */
	private Action crossProductOrTimesAction;
	
	/**
	 * Action for inserting a dot-product symbol.
	 */
	private Action dotProductAction;
	
	/**
	 * Action for inserting a wedge-product symbol.
	 */
	private Action wedgeProductAction;
	
	/**
	 * Action for inserting a partial derivative symbol.
	 */
	private Action partialDerivativeAction;
	
	/**
	 * Action for inserting a <math display="inline">
     * <mo>&nabla;</mo>
     * </math> operator symbol for directional derivatives.
	 */
	private Action delOperatorAction;
	
	/**
	 * Action for inserting a greater-than symbol.
	 */
	private Action greaterThanAction;
	
	/**
	 * Action for inserting a less-than symbol.
	 */
	private Action lessThanAction;
	
	/**
	 * Action for inserting a Greek uppercase Alpha.
	 */
	private Action uAlphaAction;
	
	/**
	 * Action for inserting a Greek uppercase Beta.
	 */
	private Action uBetaAction;
	
	/**
	 * Action for inserting a Greek uppercase Gamma.
	 */
	private Action uGammaAction;
	
	/**
	 * Action for inserting a Greek uppercase Delta.
	 */
	private Action  uDeltaAction;
	
	/**
	 * Action for inserting a Greek uppercase Epsilon.
	 */
	private Action  uEpsilonAction;
	
	/**
	 * Action for inserting a Greek uppercase Zeta.
	 */
	private Action  uZetaAction;
	
	/**
	 * Action for inserting a Greek uppercase Eta.
	 */
	private Action  uEtaAction;
	
	/**
	 * Action for inserting a Greek uppercase Theta.
	 */
	private Action  uThetaAction;
	
	/**
	 * Action for inserting a Greek uppercase Iota.
	 */
	private Action  uIotaAction;
	
	/**
	 * Action for inserting a Greek uppercase Kappa.
	 */
	private Action  uKappaAction;
	
	/**
	 * Action for inserting a Greek uppercase Lambda.
	 */
	private Action  uLambdaAction;
	
	/**
	 * Action for inserting a Greek uppercase Mu.
	 */
	private Action  uMuAction;
	
	/**
	 * Action for inserting a Greek uppercase Nu.
	 */
	private Action  uNuAction;
	
	/**
	 * Action for inserting a Greek uppercase Xi.
	 */
	private Action  uXiAction;
	
	/**
	 * Action for inserting a Greek uppercase Omicron.
	 */
	private Action  uOmicronAction;
	
	/**
	 * Action for unserting an uppercase Greek Pi.
	 */
	private Action  uPiAction;
	
	/**
	 * Action for inserting an uppercase Greek Rho.
	 */
	private Action  uRhoAction;
	
	/**
	 * Action for inserting an uppercase Greek Sigma.
	 */
	private Action  uSigmaAction;
	
	/**
	 * Action for inserting an uppercase Greek Tau.
	 */
	private Action  uTauAction;
	
	/**
	 * Action for inserting an uppercase Greek Upsilon.
	 */
	private Action  uUpsilonAction;
	
	/**
	 * Action for inserting an uppercase Greek Phi.
	 */
	private Action  uPhiAction;
	
	/**
	 * Action for inserting an uppercase Greek Chi.
	 */
	private Action  uChiAction;
	
	/**
	 * Action for inserting an uppercase Greek Psi.
	 */
	private Action  uPsiAction;
	
	/**
	 * Action for inserting an uppercase Greek Omega.
	 */
	private Action  uOmegaAction;
	
	
	
	
	/**
	 * Action for inserting a Greek lowercase Alpha.
	 */
	private Action lAlphaAction;
	
	/**
	 * Action for inserting a Greek lowercase Beta.
	 */
	private Action lBetaAction;
	
	/**
	 * Action for inserting a Greek lowercase Gamma.
	 */
	private Action lGammaAction;
	
	/**
	 * Action for inserting a Greek lowercase Delta.
	 */
	private Action  lDeltaAction;
	
	/**
	 * Action for inserting a Greek lowercase Epsilon.
	 */
	private Action  lEpsilonAction;
	
	/**
	 * Action for inserting a Greek lowercase Zeta.
	 */
	private Action  lZetaAction;
	
	/**
	 * Action for inserting a Greek lowercase Eta.
	 */
	private Action  lEtaAction;
	
	/**
	 * Action for inserting a Greek lowercase Theta.
	 */
	private Action  lThetaAction;
	
	/**
	 * Action for inserting a Greek lowercase Iota.
	 */
	private Action  lIotaAction;
	
	/**
	 * Action for inserting a Greek lowercase Kappa.
	 */
	private Action  lKappaAction;
	
	/**
	 * Action for inserting a Greek lowercase Lambda.
	 */
	private Action  lLambdaAction;
	
	/**
	 * Action for inserting a Greek lowercase Mu.
	 */
	private Action  lMuAction;
	
	/**
	 * Action for inserting a Greek lowercase Nu.
	 */
	private Action  lNuAction;
	
	/**
	 * Action for inserting a Greek lowercase Xi.
	 */
	private Action  lXiAction;
	
	/**
	 * Action for inserting a Greek lowercase Omicron.
	 */
	private Action  lOmicronAction;
	
	/**
	 * Action for inserting a Greek lowercase Pi.
	 */
	private Action  lPiAction;
	
	/**
	 * Action for inserting a Greek lowercase Rho.
	 */
	private Action  lRhoAction;
	
	/**
	 * Action for inserting a Greek lowercase Sigma.
	 */
	private Action  lSigmaAction;
	
	/**
	 * Action for inserting a Greek lowercase Tau.
	 */
	private Action  lTauAction;
	
	/**
	 * Action for inserting a Greek lowercase Upsilon.
	 */
	private Action  lUpsilonAction;
	
	/**
	 * Action for inserting a Greek lowercase Phi.
	 */
	private Action  lPhiAction;
	
	/**
	 * Action for inserting a Greek lowercase Chi.
	 */
	private Action  lChiAction;
	
	/**
	 * Action for inserting a Greek lowercase Psi.
	 */
	private Action  lPsiAction;
	
	/**
	 * Action for inserting a Greek lowercase Omega.
	 */
	private Action  lOmegaAction;
	
	
	
	/**
	 * Handles a request to insert a string into the expression.
	 * @param str The desired string to insert.
	 * @param insMode The default mode for the insertion.
	 */
	protected void insertString( final String str , FencedEditorCanvas.DefaultInsert insMode )
	{
		final IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if( part instanceof FencedEditor )
		{
			final FencedEditor apart = (FencedEditor) part;
			apart.handleInsert( str , insMode );
		}
		else
		{
			System.out.println( "Symbol Editor Not Found." );
		}
	}
	
	
	/**
	 * Handles a request to set an Insert Mode.
	 * @param in The desired Insert Mode to set.
	 */
	protected void handleInsertMode( FencedEditorCanvas.InsertModes in )
	{
		final IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if( part instanceof FencedEditor )
		{
			final FencedEditor apart = (FencedEditor) part;
			apart.handleInsertMode( in );
		}
		else
		{
			System.out.println( "Symbol Editor Not Found." );
		}
	}
	
	
	/**
	 * Handles a request to set an Overarch Insert.
	 * @param in The desired Overarch Insert to set.
	 */
	protected void handleOverarchInsert( FencedEditorCanvas.OverarchInsert in )
	{
		final IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if( part instanceof FencedEditor )
		{
			final FencedEditor apart = (FencedEditor) part;
			apart.handleOverarchInsert( in );
		}
		else
		{
			System.out.println( "Symbol Editor Not Found." );
		}
	}
	
	
	/**
	 * Handles exporting the MathML expression to the console.
	 */
	protected void handleExportToConsole()
	{
		final IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if( part instanceof FencedEditor )
		{
			final FencedEditor apart = (FencedEditor) part;
			apart.handleExportToConsole();
		}
		else
		{
			System.out.println( "Symbol Editor Not Found." );
		}
	}
	
	
	
	/**
	 * Creates a Alg Fenced contributor.
	 */
	public FencedEditorContributor() {
		super();
		createActions();
	}
	
	

	@Override
	public void setActivePage(IEditorPart part) {
		if (activeEditorPart == part)
			return;

		activeEditorPart = part;

		IActionBars actionBars = getActionBars();
		if (actionBars != null) {

			
			actionBars.updateActionBars();
		}
	}
	
	/**
	 * Creates actions for responding to user events.
	 */
	private void createActions() {
		
		circAction = new Action() {
			@Override
			public void run() {
				handleInsertMode( FencedEditorCanvas.InsertModes.CIRC_MODE );
			}
		};
		circAction.setText("Circular Paren");
		circAction.setToolTipText("Circular Paren");
		circAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		squareAction = new Action() {
			@Override
			public void run() {
				handleInsertMode( FencedEditorCanvas.InsertModes.SQUARE_MODE );
			}
		};
		squareAction.setText("Square Paren");
		squareAction.setToolTipText("Square Paren");
		squareAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		curlyAction = new Action() {
			@Override
			public void run() {
				handleInsertMode( FencedEditorCanvas.InsertModes.CURLY_MODE );
			}
		};
		curlyAction.setText("Curly Brace");
		curlyAction.setToolTipText("Curly Brace");
		curlyAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		vertAction = new Action() {
			@Override
			public void run() {
				handleInsertMode( FencedEditorCanvas.InsertModes.VERT_MODE );
			}
		};
		vertAction.setText("Vertical Brace");
		vertAction.setToolTipText("Vertical Brace");
		vertAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		dumpTextToConsoleAction = new Action() {
			@Override
			public void run() {
				handleExportToConsole();
			}
		};
		dumpTextToConsoleAction.setText("Dump Text To Console");
		dumpTextToConsoleAction.setToolTipText("Dump Text To Console");
		dumpTextToConsoleAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		
		overarchInsertDefaultAction = new Action() {
			@Override
			public void run() {
				handleOverarchInsert( FencedEditorCanvas.OverarchInsert.DEFAULT_INSERT_MODE );
			}
		};
		overarchInsertDefaultAction.setText("Default");
		overarchInsertDefaultAction.setToolTipText("Default Mode");
		overarchInsertDefaultAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		overarchInsertNoneAction = new Action() {
			@Override
			public void run() {
				handleOverarchInsert( FencedEditorCanvas.OverarchInsert.NO_INSERT_MODE );
			}
		};
		overarchInsertNoneAction.setText("None");
		overarchInsertNoneAction.setToolTipText("None Mode");
		overarchInsertNoneAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		overarchInsertIdentifierAction = new Action() {
			@Override
			public void run() {
				handleOverarchInsert( FencedEditorCanvas.OverarchInsert.IDENT_INSERT_MODE );
			}
		};
		overarchInsertIdentifierAction.setText("Identifier");
		overarchInsertIdentifierAction.setToolTipText("Identifier Mode");
		overarchInsertIdentifierAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		overarchInsertOperatorAction = new Action() {
			@Override
			public void run() {
				handleOverarchInsert( FencedEditorCanvas.OverarchInsert.OPERATOR_INSERT_MODE );
			}
		};
		overarchInsertOperatorAction.setText("Operator");
		overarchInsertOperatorAction.setToolTipText("Operator Mode");
		overarchInsertOperatorAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		overarchInsertNumberAction = new Action() {
			@Override
			public void run() {
				handleOverarchInsert( FencedEditorCanvas.OverarchInsert.NUMBER_INSERT_MODE );
			}
		};
		overarchInsertNumberAction.setText("Number");
		overarchInsertNumberAction.setToolTipText("Number Mode");
		overarchInsertNumberAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		
		
		
		plusAction = new Action() {
			@Override
			public void run() {
				insertString( "+" , FencedEditorCanvas.DefaultInsert.OPERATOR_INSERT_MODE );
			}
		};
		plusAction.setText("Plus");
		plusAction.setToolTipText("Plus");
		plusAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		minusAction = new Action() {
			@Override
			public void run() {
				insertString( "-", FencedEditorCanvas.DefaultInsert.OPERATOR_INSERT_MODE );
			}
		};
		minusAction.setText("Minus");
		minusAction.setToolTipText("Minus");
		minusAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		
		crossProductOrTimesAction = new Action() {
			@Override
			public void run() {
				insertString( "&times;", FencedEditorCanvas.DefaultInsert.OPERATOR_INSERT_MODE );
			}
		};
		crossProductOrTimesAction.setText("Cross Product Or Times");
		crossProductOrTimesAction.setToolTipText("Cross Product Or Times");
		crossProductOrTimesAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		
		dotProductAction = new Action() {
			@Override
			public void run() {
				insertString( "&CenterDot;", FencedEditorCanvas.DefaultInsert.OPERATOR_INSERT_MODE );
			}
		};
		dotProductAction.setText("Dot Product");
		dotProductAction.setToolTipText("Dot Product");
		dotProductAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		
		wedgeProductAction = new Action() {
			@Override
			public void run() {
				insertString( "&and;", FencedEditorCanvas.DefaultInsert.OPERATOR_INSERT_MODE );
			}
		};
		wedgeProductAction.setText("Wedge Product");
		wedgeProductAction.setToolTipText("Wedge Product");
		wedgeProductAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		
		partialDerivativeAction = new Action() {
			@Override
			public void run() {
				insertString( "&PartialD;", FencedEditorCanvas.DefaultInsert.OPERATOR_INSERT_MODE );
			}
		};
		partialDerivativeAction.setText("Partial Derivative");
		partialDerivativeAction.setToolTipText("Partial Derivative");
		partialDerivativeAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		
		delOperatorAction = new Action() {
			@Override
			public void run() {
				insertString( "&nabla;", FencedEditorCanvas.DefaultInsert.OPERATOR_INSERT_MODE );
			}
		};
		delOperatorAction.setText("Del Operator");
		delOperatorAction.setToolTipText("Del Operator");
		delOperatorAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		lessThanAction = new Action() {
			@Override
			public void run() {
				insertString( "&lt;", FencedEditorCanvas.DefaultInsert.OPERATOR_INSERT_MODE );
			}
		};
		lessThanAction.setText("Less Than");
		lessThanAction.setToolTipText("Less Than");
		lessThanAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		greaterThanAction = new Action() {
			@Override
			public void run() {
				insertString( "&gt;", FencedEditorCanvas.DefaultInsert.OPERATOR_INSERT_MODE );
			}
		};
		greaterThanAction.setText("Greater Than");
		greaterThanAction.setToolTipText("Greater Than");
		greaterThanAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		
		
		
		uAlphaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Alpha;" , FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uAlphaAction.setText("Alpha");
		uAlphaAction.setToolTipText("Alpha");
		uAlphaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uBetaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Beta;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uBetaAction.setText("Beta");
		uBetaAction.setToolTipText("Beta");
		uBetaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uGammaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Gamma;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uGammaAction.setText("Gamma");
		uGammaAction.setToolTipText("Gamma");
		uGammaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uDeltaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Delta;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uDeltaAction.setText("Delta");
		uDeltaAction.setToolTipText("Delta");
		uDeltaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uEpsilonAction = new Action() {
			@Override
			public void run() {
				insertString( "&Epsilon;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uEpsilonAction.setText("Epsilon");
		uEpsilonAction.setToolTipText("Epsilon");
		uEpsilonAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uZetaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Zeta;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uZetaAction.setText("Zeta");
		uZetaAction.setToolTipText("Zeta");
		uZetaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uEtaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Eta;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uEtaAction.setText("Eta");
		uEtaAction.setToolTipText("Eta");
		uEtaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uThetaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Theta;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uThetaAction.setText("Theta");
		uThetaAction.setToolTipText("Theta");
		uThetaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uIotaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Iota;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uIotaAction.setText("Iota");
		uIotaAction.setToolTipText("Iota");
		uIotaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uKappaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Kappa;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uKappaAction.setText("Kappa");
		uKappaAction.setToolTipText("Kappa");
		uKappaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uLambdaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Lambda;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uLambdaAction.setText("Lambda");
		uLambdaAction.setToolTipText("Lambda");
		uLambdaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uMuAction = new Action() {
			@Override
			public void run() {
				insertString( "&Mu;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uMuAction.setText("Mu");
		uMuAction.setToolTipText("Mu");
		uMuAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uNuAction = new Action() {
			@Override
			public void run() {
				insertString( "&Nu;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uNuAction.setText("Nu");
		uNuAction.setToolTipText("Nu");
		uNuAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uXiAction = new Action() {
			@Override
			public void run() {
				insertString( "&Xi;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uXiAction.setText("Xi");
		uXiAction.setToolTipText("Xi");
		uXiAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uOmicronAction = new Action() {
			@Override
			public void run() {
				insertString( "&Omicron;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uOmicronAction.setText("Omicron");
		uOmicronAction.setToolTipText("Omicron");
		uOmicronAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uPiAction = new Action() {
			@Override
			public void run() {
				insertString( "&Pi;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uPiAction.setText("Pi");
		uPiAction.setToolTipText("Pi");
		uPiAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uRhoAction = new Action() {
			@Override
			public void run() {
				insertString( "&Rho;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uRhoAction.setText("Rho");
		uRhoAction.setToolTipText("Rho");
		uRhoAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uSigmaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Sigma;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uSigmaAction.setText("Sigma");
		uSigmaAction.setToolTipText("Sigma");
		uSigmaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uTauAction = new Action() {
			@Override
			public void run() {
				insertString( "&Tau;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uTauAction.setText("Tau");
		uTauAction.setToolTipText("Tau");
		uTauAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uUpsilonAction = new Action() {
			@Override
			public void run() {
				insertString( "&Upsilon;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uUpsilonAction.setText("Upsilon");
		uUpsilonAction.setToolTipText("Upsilon");
		uUpsilonAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uPhiAction = new Action() {
			@Override
			public void run() {
				insertString( "&Phi;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uPhiAction.setText("Phi");
		uPhiAction.setToolTipText("Phi");
		uPhiAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uChiAction = new Action() {
			@Override
			public void run() {
				insertString( "&Chi;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uChiAction.setText("Chi");
		uChiAction.setToolTipText("Chi");
		uChiAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uPsiAction = new Action() {
			@Override
			public void run() {
				insertString( "&Psi;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uPsiAction.setText("Psi");
		uPsiAction.setToolTipText("Psi");
		uPsiAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uOmegaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Omega;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uOmegaAction.setText("Omega");
		uOmegaAction.setToolTipText("Omega");
		uOmegaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		
		
		
		lAlphaAction = new Action() {
			@Override
			public void run() {
				insertString( "&alpha;" , FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lAlphaAction.setText("alpha");
		lAlphaAction.setToolTipText("alpha");
		lAlphaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lBetaAction = new Action() {
			@Override
			public void run() {
				insertString( "&beta;" , FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lBetaAction.setText("beta");
		lBetaAction.setToolTipText("beta");
		lBetaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lGammaAction = new Action() {
			@Override
			public void run() {
				insertString( "&gamma;" , FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lGammaAction.setText("gamma");
		lGammaAction.setToolTipText("gamma");
		lGammaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		lDeltaAction = new Action() {
			@Override
			public void run() {
				insertString( "&delta;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lDeltaAction.setText("delta");
		lDeltaAction.setToolTipText("delta");
		lDeltaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lEpsilonAction = new Action() {
			@Override
			public void run() {
				insertString( "&epsilon;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lEpsilonAction.setText("epsilon");
		lEpsilonAction.setToolTipText("epsilon");
		lEpsilonAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lZetaAction = new Action() {
			@Override
			public void run() {
				insertString( "&zeta;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lZetaAction.setText("zeta");
		lZetaAction.setToolTipText("zeta");
		lZetaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lEtaAction = new Action() {
			@Override
			public void run() {
				insertString( "&eta;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lEtaAction.setText("eta");
		lEtaAction.setToolTipText("eta");
		lEtaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lThetaAction = new Action() {
			@Override
			public void run() {
				insertString( "&theta;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lThetaAction.setText("theta");
		lThetaAction.setToolTipText("theta");
		lThetaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lIotaAction = new Action() {
			@Override
			public void run() {
				insertString( "&iota;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lIotaAction.setText("iota");
		lIotaAction.setToolTipText("iota");
		lIotaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lKappaAction = new Action() {
			@Override
			public void run() {
				insertString( "&kappa;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lKappaAction.setText("kappa");
		lKappaAction.setToolTipText("kappa");
		lKappaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lLambdaAction = new Action() {
			@Override
			public void run() {
				insertString( "&lambda;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lLambdaAction.setText("lambda");
		lLambdaAction.setToolTipText("lambda");
		lLambdaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lMuAction = new Action() {
			@Override
			public void run() {
				insertString( "&mu;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lMuAction.setText("mu");
		lMuAction.setToolTipText("mu");
		lMuAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lNuAction = new Action() {
			@Override
			public void run() {
				insertString( "&nu;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lNuAction.setText("nu");
		lNuAction.setToolTipText("nu");
		lNuAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lXiAction = new Action() {
			@Override
			public void run() {
				insertString( "&xi;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lXiAction.setText("xi");
		lXiAction.setToolTipText("xi");
		lXiAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lOmicronAction = new Action() {
			@Override
			public void run() {
				insertString( "&omicron;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lOmicronAction.setText("omicron");
		lOmicronAction.setToolTipText("omicron");
		lOmicronAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lPiAction = new Action() {
			@Override
			public void run() {
				insertString( "&pi;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lPiAction.setText("pi");
		lPiAction.setToolTipText("pi");
		lPiAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lRhoAction = new Action() {
			@Override
			public void run() {
				insertString( "&rho;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lRhoAction.setText("rho");
		lRhoAction.setToolTipText("rho");
		lRhoAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lSigmaAction = new Action() {
			@Override
			public void run() {
				insertString( "&sigma;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lSigmaAction.setText("sigma");
		lSigmaAction.setToolTipText("sigma");
		lSigmaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lTauAction = new Action() {
			@Override
			public void run() {
				insertString( "&tau;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lTauAction.setText("tau");
		lTauAction.setToolTipText("tau");
		lTauAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lUpsilonAction = new Action() {
			@Override
			public void run() {
				insertString( "&upsilon;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lUpsilonAction.setText("upsilon");
		lUpsilonAction.setToolTipText("upsilon");
		lUpsilonAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lPhiAction = new Action() {
			@Override
			public void run() {
				insertString( "&phi;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lPhiAction.setText("phi");
		lPhiAction.setToolTipText("phi");
		lPhiAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lChiAction = new Action() {
			@Override
			public void run() {
				insertString( "&chi;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lChiAction.setText("chi");
		lChiAction.setToolTipText("chi");
		lChiAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lPsiAction = new Action() {
			@Override
			public void run() {
				insertString( "&psi;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lPsiAction.setText("psi");
		lPsiAction.setToolTipText("psi");
		lPsiAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lOmegaAction = new Action() {
			@Override
			public void run() {
				insertString( "&omega;", FencedEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lOmegaAction.setText("omega");
		lOmegaAction.setToolTipText("omega");
		lOmegaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		

		
		
		
		
		
		
	}
	
	@Override
	public void contributeToMenu(IMenuManager manager) {
		IMenuManager uGreekMenu = new MenuManager("U_Greek");
		manager.prependToGroup(IWorkbenchActionConstants.MB_ADDITIONS, uGreekMenu);
		uGreekMenu.add( uAlphaAction );
		uGreekMenu.add( uBetaAction );
		uGreekMenu.add( uGammaAction );
		uGreekMenu.add(  uDeltaAction );
		uGreekMenu.add(  uEpsilonAction );
		uGreekMenu.add(  uZetaAction );
		uGreekMenu.add(  uEtaAction );
		uGreekMenu.add(  uThetaAction );
		uGreekMenu.add(  uIotaAction );
		uGreekMenu.add(  uKappaAction );
		uGreekMenu.add(  uLambdaAction );
		uGreekMenu.add(  uMuAction );
		uGreekMenu.add(  uNuAction );
		uGreekMenu.add(  uXiAction );
		uGreekMenu.add(  uOmicronAction );
		uGreekMenu.add(  uPiAction );
		uGreekMenu.add(  uRhoAction );
		uGreekMenu.add(  uSigmaAction );
		uGreekMenu.add(  uTauAction );
		uGreekMenu.add(  uUpsilonAction );
		uGreekMenu.add(  uPhiAction );
		uGreekMenu.add(  uChiAction );
		uGreekMenu.add(  uPsiAction );
		uGreekMenu.add(  uOmegaAction );
		
		IMenuManager lGreekMenu = new MenuManager("L_Greek");
		manager.prependToGroup(IWorkbenchActionConstants.MB_ADDITIONS, lGreekMenu);
		lGreekMenu.add( lAlphaAction );
		lGreekMenu.add( lBetaAction );
		lGreekMenu.add( lGammaAction );
		lGreekMenu.add(  lDeltaAction );
		lGreekMenu.add(  lEpsilonAction );
		lGreekMenu.add(  lZetaAction );
		lGreekMenu.add(  lEtaAction );
		lGreekMenu.add(  lThetaAction );
		lGreekMenu.add(  lIotaAction );
		lGreekMenu.add(  lKappaAction );
		lGreekMenu.add(  lLambdaAction );
		lGreekMenu.add(  lMuAction );
		lGreekMenu.add(  lNuAction );
		lGreekMenu.add(  lXiAction );
		lGreekMenu.add(  lOmicronAction );
		lGreekMenu.add(  lPiAction );
		lGreekMenu.add(  lRhoAction );
		lGreekMenu.add(  lSigmaAction );
		lGreekMenu.add(  lTauAction );
		lGreekMenu.add(  lUpsilonAction );
		lGreekMenu.add(  lPhiAction );
		lGreekMenu.add(  lChiAction );
		lGreekMenu.add(  lPsiAction );
		lGreekMenu.add(  lOmegaAction );
		
		IMenuManager operatorMenu = new MenuManager("Operator");
		manager.prependToGroup(IWorkbenchActionConstants.MB_ADDITIONS, operatorMenu);
		operatorMenu.add( plusAction );
		operatorMenu.add( minusAction );
		operatorMenu.add( crossProductOrTimesAction );
		operatorMenu.add( dotProductAction );
		operatorMenu.add( wedgeProductAction );
		operatorMenu.add( partialDerivativeAction );
		operatorMenu.add( delOperatorAction );
		operatorMenu.add( greaterThanAction );
		operatorMenu.add( lessThanAction );
		
		IMenuManager overarchInsertMenu = new MenuManager("Overarch_Insert");
		manager.prependToGroup(IWorkbenchActionConstants.MB_ADDITIONS, overarchInsertMenu);
		overarchInsertMenu.add( overarchInsertDefaultAction );
		overarchInsertMenu.add( overarchInsertNoneAction );
		overarchInsertMenu.add( overarchInsertIdentifierAction );
		overarchInsertMenu.add( overarchInsertOperatorAction );
		overarchInsertMenu.add( overarchInsertNumberAction );
		
		IMenuManager symbolMenu = new MenuManager("Paren");
		manager.prependToGroup(IWorkbenchActionConstants.MB_ADDITIONS, symbolMenu);
		symbolMenu.add(circAction);
		symbolMenu.add(squareAction);
		symbolMenu.add(curlyAction);
		symbolMenu.add(vertAction);
		symbolMenu.add( new Separator() );
		symbolMenu.add(dumpTextToConsoleAction);
		
	}
	
	@Override
	public void contributeToToolBar(IToolBarManager manager) {
		// manager.add(new Separator());
		// manager.add(scriptAction);
	}
	
}

