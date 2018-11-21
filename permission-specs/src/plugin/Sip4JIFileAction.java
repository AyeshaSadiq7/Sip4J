package plugin;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import parser.UserSelectedClasses_Analysis;
import parser.Workspace_Utilities;

public class Sip4JIFileAction implements IObjectActionDelegate {

	private ISelection selection;

	private static final Logger log = Logger.getLogger(Sip4JIFileAction.class
			.getName());

	public Sip4JIFileAction() {
		super();
	}

	// Step 1 get compilation units
	public void run(IAction action) {
		System.out.println(action.toString());
		long start = System.nanoTime();// do -nothin
		//System.out.println("Run Action: " + action.getId());
		/*
		 * int testType = -1;
		 * 
		 * if(action.getId().endsWith(".alltest")==true) testType = 0; else
		 * if(action.getId().endsWith("Context-Null.Test")==true) testType = 1;
		 * else if(action.getId().endsWith("Context-RW.Test")==true) testType =
		 * 2; else if(action.getId().endsWith("Context-R.Test")) testType = 3;
		 * else if(action.getId().endsWith("Context-N.Test")) testType = 4;
		 * 
		 * final int ftestType = testType;
		 * System.out.println("action id assigned "+ftestType);
		 */
		List<ICompilationUnit> reanalyzeList = null;

		if (!selection.isEmpty()) {
			if (selection instanceof IStructuredSelection) {
				for (Object element : ((IStructuredSelection) selection)
						.toList()) {
					List<ICompilationUnit> temp = Workspace_Utilities
							.collectCompilationUnits((IJavaElement) element);
					if (temp == null)
						continue;
					if (reanalyzeList == null)
						reanalyzeList = temp;
					else
						reanalyzeList.addAll(temp);
				}
			}
		}

		if (reanalyzeList != null) {

			final List<ICompilationUnit> compUnits = reanalyzeList;

			int count = 0;

			for (ICompilationUnit cunit : compUnits) {
				System.out.println("Compilation Unit Names = "+ cunit.getElementName().toString());
				count++;
			}
			// Step 2 : analyzeCompUnits (compUnits, ftestType);
			try {
				analyzeCompUnits(compUnits);
			} catch (IOException | JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long end = System.nanoTime();
			long elapsedTime = end - start;
			double seconds = (double)elapsedTime / 1000000000.0;
			System.out.println("Seconds Time = "+seconds);
			
		
		}

	}
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;

	}
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {

	}
	public void analyzeCompUnits(List<ICompilationUnit> compUnits) throws IOException, JavaModelException {

		final List<ICompilationUnit> cus = compUnits;

		UserSelectedClasses_Analysis UAnalysis = new UserSelectedClasses_Analysis();

		UAnalysis.visitCompilationUnits(cus);
		
				System.out.println("//////////////////////////////////////////////////////");
		


		
	
	}

}
