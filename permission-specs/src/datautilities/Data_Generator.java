package datautilities;

import datastructure.E_Package;

public class Data_Generator {

	private static E_Package packg;
	
	Data_Generator(){	
		
	}
	public static void createNewPackage(){	
		
		packg =  new E_Package();
		
	}
	public static void addPackage(E_Package _pkg){

	}
	public static E_Package getPackage(){
		return packg;
	}
	
}
