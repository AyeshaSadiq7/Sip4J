package uma.gap.plugin;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import uma.SMC.UserSelectedClassesAnalysis;
import uma.SMC.WorkspaceUtilities;


public class GAPIFileAction implements IObjectActionDelegate{

	private ISelection selection;

	@SuppressWarnings("unused")
	
	private static final Logger log = Logger.getLogger(GAPIFileAction.class.getName());

	public GAPIFileAction() {
		super();
	}

	@Override
public void run(IAction action) {
		
	System.out.println("Run Action: " + action.getId());
		
	   int testType = -1;
	   
		if(action.getId().endsWith(".alltest")==true)
			testType = 0;
		else if(action.getId().endsWith("Sink.States"))
			testType = 1;
		else if(action.getId().endsWith("Methods.Execution"))
			testType = 2;
		else if(action.getId().endsWith("Adjaceny.Matrix"))
			testType = 3;
		else if(action.getId().endsWith("Concurrency.Test"))
			testType = 4;
		
				
		final int ftestType = testType;
		
		List<ICompilationUnit> reanalyzeList = null;
		
		if (!selection.isEmpty()) {
			if (selection instanceof IStructuredSelection) {
				for (Object element : ((IStructuredSelection)selection).toList()) {
					List<ICompilationUnit> temp =WorkspaceUtilities.collectCompilationUnits((IJavaElement) element);
					if(temp == null)
						continue;
					if(reanalyzeList == null)
						reanalyzeList = temp;
					else
						reanalyzeList.addAll(temp);
				}
			}
		}
		if(reanalyzeList != null) {
			
			final List<ICompilationUnit> compUnits = reanalyzeList;
		
			Job j = new Job("EVMDD_SMC") {

		@Override
		protected IStatus run(IProgressMonitor monitor) {
					
			final List<ICompilationUnit> cus = compUnits;

			UserSelectedClassesAnalysis UAnalysis = new UserSelectedClassesAnalysis();
					
			UAnalysis.analyzeFromPlugin(cus,ftestType);

			if(monitor.isCanceled())
					return Status.CANCEL_STATUS;
			return Status.OK_STATUS;
		}
				
	};
		j.setUser(true);
		j.schedule();
	}

}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;

	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {


	}

	
}
