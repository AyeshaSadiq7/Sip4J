package uma.SMC;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {
	
	public FileReader(String string) {
		// TODO Auto-generated constructor stub
	}

	public static String readFile(String pathname) throws IOException {

	    File file = new File(pathname);
	    StringBuilder fileContents = new StringBuilder((int)file.length());
	    Scanner scanner = new Scanner(file);
	    String lineSeparator = System.getProperty("line.separator");

	    try {
	        while(scanner.hasNextLine()) {        
	            fileContents.append(scanner.nextLine() + lineSeparator);
	        }
	        return fileContents.toString();
	    } finally {
	        scanner.close();
	    }
	}

}
