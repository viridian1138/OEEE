






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
 * Manages the installation/deinstallation of global actions for Alg Quotient editors.
 * Responsible for the redirection of global actions to the active editor.
 */
public class QuotientEditorContributor extends MultiPageEditorActionBarContributor {
	private IEditorPart activeEditorPart;
	
	private Action overscriptAction;
	private Action underscriptAction;
	private Action dumpTextToConsoleAction;
	
	private Action overarchInsertDefaultAction;
	private Action overarchInsertNoneAction;
	private Action overarchInsertIdentifierAction;
	private Action overarchInsertOperatorAction;
	private Action overarchInsertNumberAction;
	
	private Action plusAction;
	private Action minusAction;
	private Action crossProductOrTimesAction;
	private Action dotProductAction;
	private Action wedgeProductAction;
	private Action partialDerivativeAction;
	private Action delOperatorAction;
	private Action greaterThanAction;
	private Action lessThanAction;
	
	private Action uAlphaAction;
	private Action uBetaAction;
	private Action uGammaAction;
	private Action  uDeltaAction;
	private Action  uEpsilonAction;
	private Action  uZetaAction;
	private Action  uEtaAction;
	private Action  uThetaAction;
	private Action  uIotaAction;
	private Action  uKappaAction;
	private Action  uLambdaAction;
	private Action  uMuAction;
	private Action  uNuAction;
	private Action  uXiAction;
	private Action  uOmicronAction;
	private Action  uPiAction;
	private Action  uRhoAction;
	private Action  uSigmaAction;
	private Action  uTauAction;
	private Action  uUpsilonAction;
	private Action  uPhiAction;
	private Action  uChiAction;
	private Action  uPsiAction;
	private Action  uOmegaAction;
	
	
	
	
	
	private Action lAlphaAction;
	private Action lBetaAction;
	private Action lGammaAction;
	private Action  lDeltaAction;
	private Action  lEpsilonAction;
	private Action  lZetaAction;
	private Action  lEtaAction;
	private Action  lThetaAction;
	private Action  lIotaAction;
	private Action  lKappaAction;
	private Action  lLambdaAction;
	private Action  lMuAction;
	private Action  lNuAction;
	private Action  lXiAction;
	private Action  lOmicronAction;
	private Action  lPiAction;
	private Action  lRhoAction;
	private Action  lSigmaAction;
	private Action  lTauAction;
	private Action  lUpsilonAction;
	private Action  lPhiAction;
	private Action  lChiAction;
	private Action  lPsiAction;
	private Action  lOmegaAction;
	
	
	
	/**
	 * Handles a request to insert a string into the expression.
	 * @param str The desired string to insert.
	 * @param insMode The default mode for the insertion.
	 */
	protected void insertString( final String str , QuotientEditorCanvas.DefaultInsert insMode )
	{
		final IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if( part instanceof QuotientEditor )
		{
			final QuotientEditor apart = (QuotientEditor) part;
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
	protected void handleInsertMode( QuotientEditorCanvas.InsertModes in )
	{
		final IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if( part instanceof QuotientEditor )
		{
			final QuotientEditor apart = (QuotientEditor) part;
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
	protected void handleOverarchInsert( QuotientEditorCanvas.OverarchInsert in )
	{
		final IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if( part instanceof QuotientEditor )
		{
			final QuotientEditor apart = (QuotientEditor) part;
			apart.handleOverarchInsert( in );
		}
		else
		{
			System.out.println( "Symbol Editor Not Found." );
		}
	}
	
	
	
	/**
	 * Creates a Alg Quotient contributor.
	 */
	public QuotientEditorContributor() {
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
		
		overscriptAction = new Action() {
			@Override
			public void run() {
				handleInsertMode( QuotientEditorCanvas.InsertModes.OVERSCRIPT_MODE );
			}
		};
		overscriptAction.setText("Overscript");
		overscriptAction.setToolTipText("Overscript Mode");
		overscriptAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		underscriptAction = new Action() {
			@Override
			public void run() {
				handleInsertMode( QuotientEditorCanvas.InsertModes.UNDERSCRIPT_MODE );
			}
		};
		underscriptAction.setText("Underscript");
		underscriptAction.setToolTipText("Underscript Mode");
		underscriptAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		dumpTextToConsoleAction = new Action() {
			@Override
			public void run() {
				MessageDialog.openInformation(null, "QuotientEditor", "Dump Text To Console Action Executed");
			}
		};
		dumpTextToConsoleAction.setText("Dump Text To Console");
		dumpTextToConsoleAction.setToolTipText("Dump Text To Console");
		dumpTextToConsoleAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		
		overarchInsertDefaultAction = new Action() {
			@Override
			public void run() {
				handleOverarchInsert( QuotientEditorCanvas.OverarchInsert.DEFAULT_INSERT_MODE );
			}
		};
		overarchInsertDefaultAction.setText("Default");
		overarchInsertDefaultAction.setToolTipText("Default Mode");
		overarchInsertDefaultAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		overarchInsertNoneAction = new Action() {
			@Override
			public void run() {
				handleOverarchInsert( QuotientEditorCanvas.OverarchInsert.NO_INSERT_MODE );
			}
		};
		overarchInsertNoneAction.setText("None");
		overarchInsertNoneAction.setToolTipText("None Mode");
		overarchInsertNoneAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		overarchInsertIdentifierAction = new Action() {
			@Override
			public void run() {
				handleOverarchInsert( QuotientEditorCanvas.OverarchInsert.IDENT_INSERT_MODE );
			}
		};
		overarchInsertIdentifierAction.setText("Identifier");
		overarchInsertIdentifierAction.setToolTipText("Identifier Mode");
		overarchInsertIdentifierAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		overarchInsertOperatorAction = new Action() {
			@Override
			public void run() {
				handleOverarchInsert( QuotientEditorCanvas.OverarchInsert.OPERATOR_INSERT_MODE );
			}
		};
		overarchInsertOperatorAction.setText("Operator");
		overarchInsertOperatorAction.setToolTipText("Operator Mode");
		overarchInsertOperatorAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		overarchInsertNumberAction = new Action() {
			@Override
			public void run() {
				handleOverarchInsert( QuotientEditorCanvas.OverarchInsert.NUMBER_INSERT_MODE );
			}
		};
		overarchInsertNumberAction.setText("Number");
		overarchInsertNumberAction.setToolTipText("Number Mode");
		overarchInsertNumberAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		
		
		
		plusAction = new Action() {
			@Override
			public void run() {
				insertString( "+" , QuotientEditorCanvas.DefaultInsert.OPERATOR_INSERT_MODE );
			}
		};
		plusAction.setText("Plus");
		plusAction.setToolTipText("Plus");
		plusAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		minusAction = new Action() {
			@Override
			public void run() {
				insertString( "-", QuotientEditorCanvas.DefaultInsert.OPERATOR_INSERT_MODE );
			}
		};
		minusAction.setText("Minus");
		minusAction.setToolTipText("Minus");
		minusAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		
		crossProductOrTimesAction = new Action() {
			@Override
			public void run() {
				insertString( "&times;", QuotientEditorCanvas.DefaultInsert.OPERATOR_INSERT_MODE );
			}
		};
		crossProductOrTimesAction.setText("Cross Product Or Times");
		crossProductOrTimesAction.setToolTipText("Cross Product Or Times");
		crossProductOrTimesAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		
		dotProductAction = new Action() {
			@Override
			public void run() {
				insertString( "&CenterDot;", QuotientEditorCanvas.DefaultInsert.OPERATOR_INSERT_MODE );
			}
		};
		dotProductAction.setText("Dot Product");
		dotProductAction.setToolTipText("Dot Product");
		dotProductAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		
		wedgeProductAction = new Action() {
			@Override
			public void run() {
				insertString( "&and;", QuotientEditorCanvas.DefaultInsert.OPERATOR_INSERT_MODE );
			}
		};
		wedgeProductAction.setText("Wedge Product");
		wedgeProductAction.setToolTipText("Wedge Product");
		wedgeProductAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		
		partialDerivativeAction = new Action() {
			@Override
			public void run() {
				insertString( "&PartialD;", QuotientEditorCanvas.DefaultInsert.OPERATOR_INSERT_MODE );
			}
		};
		partialDerivativeAction.setText("Partial Derivative");
		partialDerivativeAction.setToolTipText("Partial Derivative");
		partialDerivativeAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		
		delOperatorAction = new Action() {
			@Override
			public void run() {
				insertString( "&nabla;", QuotientEditorCanvas.DefaultInsert.OPERATOR_INSERT_MODE );
			}
		};
		delOperatorAction.setText("Del Operator");
		delOperatorAction.setToolTipText("Del Operator");
		delOperatorAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		lessThanAction = new Action() {
			@Override
			public void run() {
				insertString( "&lt;", QuotientEditorCanvas.DefaultInsert.OPERATOR_INSERT_MODE );
			}
		};
		lessThanAction.setText("Less Than");
		lessThanAction.setToolTipText("Less Than");
		lessThanAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		greaterThanAction = new Action() {
			@Override
			public void run() {
				insertString( "&gt;", QuotientEditorCanvas.DefaultInsert.OPERATOR_INSERT_MODE );
			}
		};
		greaterThanAction.setText("Greater Than");
		greaterThanAction.setToolTipText("Greater Than");
		greaterThanAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		
		
		
		uAlphaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Alpha;" , QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uAlphaAction.setText("Alpha");
		uAlphaAction.setToolTipText("Alpha");
		uAlphaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uBetaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Beta;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uBetaAction.setText("Beta");
		uBetaAction.setToolTipText("Beta");
		uBetaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uGammaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Gamma;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uGammaAction.setText("Gamma");
		uGammaAction.setToolTipText("Gamma");
		uGammaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uDeltaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Delta;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uDeltaAction.setText("Delta");
		uDeltaAction.setToolTipText("Delta");
		uDeltaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uEpsilonAction = new Action() {
			@Override
			public void run() {
				insertString( "&Epsilon;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uEpsilonAction.setText("Epsilon");
		uEpsilonAction.setToolTipText("Epsilon");
		uEpsilonAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uZetaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Zeta;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uZetaAction.setText("Zeta");
		uZetaAction.setToolTipText("Zeta");
		uZetaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uEtaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Eta;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uEtaAction.setText("Eta");
		uEtaAction.setToolTipText("Eta");
		uEtaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uThetaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Theta;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uThetaAction.setText("Theta");
		uThetaAction.setToolTipText("Theta");
		uThetaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uIotaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Iota;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uIotaAction.setText("Iota");
		uIotaAction.setToolTipText("Iota");
		uIotaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uKappaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Kappa;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uKappaAction.setText("Kappa");
		uKappaAction.setToolTipText("Kappa");
		uKappaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uLambdaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Lambda;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uLambdaAction.setText("Lambda");
		uLambdaAction.setToolTipText("Lambda");
		uLambdaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uMuAction = new Action() {
			@Override
			public void run() {
				insertString( "&Mu;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uMuAction.setText("Mu");
		uMuAction.setToolTipText("Mu");
		uMuAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uNuAction = new Action() {
			@Override
			public void run() {
				insertString( "&Nu;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uNuAction.setText("Nu");
		uNuAction.setToolTipText("Nu");
		uNuAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uXiAction = new Action() {
			@Override
			public void run() {
				insertString( "&Xi;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uXiAction.setText("Xi");
		uXiAction.setToolTipText("Xi");
		uXiAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uOmicronAction = new Action() {
			@Override
			public void run() {
				insertString( "&Omicron;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uOmicronAction.setText("Omicron");
		uOmicronAction.setToolTipText("Omicron");
		uOmicronAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uPiAction = new Action() {
			@Override
			public void run() {
				insertString( "&Pi;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uPiAction.setText("Pi");
		uPiAction.setToolTipText("Pi");
		uPiAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uRhoAction = new Action() {
			@Override
			public void run() {
				insertString( "&Rho;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uRhoAction.setText("Rho");
		uRhoAction.setToolTipText("Rho");
		uRhoAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uSigmaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Sigma;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uSigmaAction.setText("Sigma");
		uSigmaAction.setToolTipText("Sigma");
		uSigmaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uTauAction = new Action() {
			@Override
			public void run() {
				insertString( "&Tau;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uTauAction.setText("Tau");
		uTauAction.setToolTipText("Tau");
		uTauAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uUpsilonAction = new Action() {
			@Override
			public void run() {
				insertString( "&Upsilon;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uUpsilonAction.setText("Upsilon");
		uUpsilonAction.setToolTipText("Upsilon");
		uUpsilonAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uPhiAction = new Action() {
			@Override
			public void run() {
				insertString( "&Phi;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uPhiAction.setText("Phi");
		uPhiAction.setToolTipText("Phi");
		uPhiAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uChiAction = new Action() {
			@Override
			public void run() {
				insertString( "&Chi;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uChiAction.setText("Chi");
		uChiAction.setToolTipText("Chi");
		uChiAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uPsiAction = new Action() {
			@Override
			public void run() {
				insertString( "&Psi;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uPsiAction.setText("Psi");
		uPsiAction.setToolTipText("Psi");
		uPsiAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		uOmegaAction = new Action() {
			@Override
			public void run() {
				insertString( "&Omega;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		uOmegaAction.setText("Omega");
		uOmegaAction.setToolTipText("Omega");
		uOmegaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		
		
		
		lAlphaAction = new Action() {
			@Override
			public void run() {
				insertString( "&alpha;" , QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lAlphaAction.setText("alpha");
		lAlphaAction.setToolTipText("alpha");
		lAlphaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lBetaAction = new Action() {
			@Override
			public void run() {
				insertString( "&beta;" , QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lBetaAction.setText("beta");
		lBetaAction.setToolTipText("beta");
		lBetaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lGammaAction = new Action() {
			@Override
			public void run() {
				insertString( "&gamma;" , QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lGammaAction.setText("gamma");
		lGammaAction.setToolTipText("gamma");
		lGammaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		
		lDeltaAction = new Action() {
			@Override
			public void run() {
				insertString( "&delta;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lDeltaAction.setText("delta");
		lDeltaAction.setToolTipText("delta");
		lDeltaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lEpsilonAction = new Action() {
			@Override
			public void run() {
				insertString( "&epsilon;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lEpsilonAction.setText("epsilon");
		lEpsilonAction.setToolTipText("epsilon");
		lEpsilonAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lZetaAction = new Action() {
			@Override
			public void run() {
				insertString( "&zeta;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lZetaAction.setText("zeta");
		lZetaAction.setToolTipText("zeta");
		lZetaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lEtaAction = new Action() {
			@Override
			public void run() {
				insertString( "&eta;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lEtaAction.setText("eta");
		lEtaAction.setToolTipText("eta");
		lEtaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lThetaAction = new Action() {
			@Override
			public void run() {
				insertString( "&theta;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lThetaAction.setText("theta");
		lThetaAction.setToolTipText("theta");
		lThetaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lIotaAction = new Action() {
			@Override
			public void run() {
				insertString( "&iota;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lIotaAction.setText("iota");
		lIotaAction.setToolTipText("iota");
		lIotaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lKappaAction = new Action() {
			@Override
			public void run() {
				insertString( "&kappa;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lKappaAction.setText("kappa");
		lKappaAction.setToolTipText("kappa");
		lKappaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lLambdaAction = new Action() {
			@Override
			public void run() {
				insertString( "&lambda;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lLambdaAction.setText("lambda");
		lLambdaAction.setToolTipText("lambda");
		lLambdaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lMuAction = new Action() {
			@Override
			public void run() {
				insertString( "&mu;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lMuAction.setText("mu");
		lMuAction.setToolTipText("mu");
		lMuAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lNuAction = new Action() {
			@Override
			public void run() {
				insertString( "&nu;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lNuAction.setText("nu");
		lNuAction.setToolTipText("nu");
		lNuAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lXiAction = new Action() {
			@Override
			public void run() {
				insertString( "&xi;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lXiAction.setText("xi");
		lXiAction.setToolTipText("xi");
		lXiAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lOmicronAction = new Action() {
			@Override
			public void run() {
				insertString( "&omicron;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lOmicronAction.setText("omicron");
		lOmicronAction.setToolTipText("omicron");
		lOmicronAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lPiAction = new Action() {
			@Override
			public void run() {
				insertString( "&pi;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lPiAction.setText("pi");
		lPiAction.setToolTipText("pi");
		lPiAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lRhoAction = new Action() {
			@Override
			public void run() {
				insertString( "&rho;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lRhoAction.setText("rho");
		lRhoAction.setToolTipText("rho");
		lRhoAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lSigmaAction = new Action() {
			@Override
			public void run() {
				insertString( "&sigma;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lSigmaAction.setText("sigma");
		lSigmaAction.setToolTipText("sigma");
		lSigmaAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lTauAction = new Action() {
			@Override
			public void run() {
				insertString( "&tau;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lTauAction.setText("tau");
		lTauAction.setToolTipText("tau");
		lTauAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lUpsilonAction = new Action() {
			@Override
			public void run() {
				insertString( "&upsilon;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lUpsilonAction.setText("upsilon");
		lUpsilonAction.setToolTipText("upsilon");
		lUpsilonAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lPhiAction = new Action() {
			@Override
			public void run() {
				insertString( "&phi;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lPhiAction.setText("phi");
		lPhiAction.setToolTipText("phi");
		lPhiAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lChiAction = new Action() {
			@Override
			public void run() {
				insertString( "&chi;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lChiAction.setText("chi");
		lChiAction.setToolTipText("chi");
		lChiAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lPsiAction = new Action() {
			@Override
			public void run() {
				insertString( "&psi;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
			}
		};
		lPsiAction.setText("psi");
		lPsiAction.setToolTipText("psi");
		lPsiAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
		
		lOmegaAction = new Action() {
			@Override
			public void run() {
				insertString( "&omega;", QuotientEditorCanvas.DefaultInsert.IDENT_INSERT_MODE );
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
		
		IMenuManager symbolMenu = new MenuManager("Symbol");
		manager.prependToGroup(IWorkbenchActionConstants.MB_ADDITIONS, symbolMenu);
		symbolMenu.add(overscriptAction);
		symbolMenu.add(underscriptAction);
		symbolMenu.add( new Separator() );
		symbolMenu.add(dumpTextToConsoleAction);
		
	}
	
	@Override
	public void contributeToToolBar(IToolBarManager manager) {
		// manager.add(new Separator());
		// manager.add(scriptAction);
	}
	
}

