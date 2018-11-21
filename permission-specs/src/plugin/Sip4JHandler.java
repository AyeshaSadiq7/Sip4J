package plugin;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;

public class Sip4JHandler implements IHandler {

	public void addHandlerListener(IHandlerListener handlerListener) {
	}

	public void dispose() {

	}

	public Object execute(ExecutionEvent event) throws ExecutionException {

		System.out.println("Run event: " + event.getCommand().getId());
		// String str=event.getCommand().getId();
		// str=str.substring(str.length()-1, str.length());
		// E_FileWriter.setK(Integer.parseInt(str));

		return null;
	}

	// PulseSettings.setFullModel(0);

	public boolean isEnabled() {
		return true;
	}

	public boolean isHandled() {
		return true;
	}

	public void removeHandlerListener(IHandlerListener handlerListener) {

	}

}
