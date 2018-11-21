package uma.gap.plugin;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import uma.SMC.E_SMC_Model;

public class GAPHandler implements IHandler{

//	@SuppressWarnings("unused")

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) { }

	@Override
	public void dispose() { }

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		System.out.println("Run event: " + event.getCommand().getId());
	//	String str=event.getCommand().getId();
	//	str=str.substring(str.length()-1, str.length());
	//	E_FileWriter.setK(Integer.parseInt(str));
		extractSettings(event);
		return null;	
	}

	private void extractSettings(ExecutionEvent event) {
		String str = event.getCommand().getId();
		
		if (str.contains("pulse-svn.command.Invariants.0")==true){
			PulseSettings.setInvariants(0);
			//PulseSettings.setFullModel(0);
		}
		else if (str.contains("pulse-svn.command.Invariants.1")==true){
			PulseSettings.setInvariants(1);
			//PulseSettings.setFullModel(0);
		}
		else if (str.contains("pulse-svn.command.Invariants.2")==true){
			PulseSettings.setInvariants(2);
			//PulseSettings.setFullModel(0);
		}
		
		if (str.contains("EVMDD_SMC_ModelGenerator.action.K.0")==true){
			PulseSettings.setAliasPerObject(0);
			//PulseSettings.setFullModel(0);
		}
		else if (str.contains("EVMDD_SMC_ModelGenerator.action.K.1")==true){
			PulseSettings.setAliasPerObject(1);
			//PulseSettings.setFullModel(0);
		}
		else if (str.contains("EVMDD_SMC_ModelGenerator.action.K.2")==true){
			PulseSettings.setAliasPerObject(2);
			//PulseSettings.setFullModel(0);
		}
		else if (str.contains("EVMDD_SMC_ModelGenerator.action.K.3")==true){
			PulseSettings.setAliasPerObject(3);
			PulseSettings.setFullModel(0);
		}
		else if (str.contains("EVMDD_SMC_ModelGenerator.action.K.4")==true){
			PulseSettings.setAliasPerObject(4);
			//PulseSettings.setFullModel(0);
		}
			
		
		
		if (str.contains("pulse-svn.command.dimensions.0")==true){
			
			PulseSettings.setDimensions(0);
			//PulseSettings.setFullModel(0);
		}
		else if (str.contains("pulse-svn.command.dimensions.0")==true){
			PulseSettings.setDimensions(1);
			//PulseSettings.setFullModel(0);
		}
	
		
		if (str.contains("pulse-svn.command.inheritance.0")==true){
			PulseSettings.setInheritance(0);
			//PulseSettings.setFullModel(0);
		}
		else if (str.contains("pulse-svn.command.inheritance.1")==true){
			PulseSettings.setInheritance(1);
			//PulseSettings.setFullModel(0);
		}
		
		if (str.contains("pulse-svn.command.basic.model")==true){
			PulseSettings.setFullModel(0);
			PulseSettings.setAliasPerObject(0);
		}
		else if (str.contains("pulse-svn.command.full.model")==true){
				
				PulseSettings.setFullModel(1);
				PulseSettings.setAliasPerObject(1);
		}
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {

		
	}
	


}
