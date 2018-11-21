package uma.SMC;

import java.util.ArrayList;
import java.util.LinkedList;

public class E_JmlSpecification {

	static String ensures;
	static String perm;
	static String TS="TS";
	static LinkedList<String> requires=new  LinkedList<String>();
	static LinkedList<E_Ghost> ghostList=new  LinkedList<E_Ghost>();
	
	
	public static void setDimensionName(String str){
		E_Ghost ghost=new E_Ghost();
		 ghost.setDimensionName(str);
		ghostList.add(ghost);
	}
	
	public static void setDimensionValues(int low, int high){
		ghostList.getFirst().setDimensionValues(low, high);
	}
	
	public static void setEnsures(String str){
		ensures=str;
	}
	
	
	public static void setPerm(String str){
		perm=str;
	}
	public static void addRequires(String str){
		requires.add(str);	
	}
	
	public static void reset(){
		ensures=null;
		perm=null;
		requires.clear();
	}
	
	public static String JmlClassSpec2PluralClassSpec(){
		String Plural;
		
		E_Ghost dimesnion=ghostList.getFirst();
		Plural="@Refine({\n"+"\t@States(dim=\""+dimesnion.getDimensionName()+"\", value={";
		for (int i=dimesnion.getLowValueofInv();i<=dimesnion.getHighValueofInv();  i++){
			if (i<dimesnion.getHighValueofInv())
			Plural+="\""+"TS"+i+"\", ";
			else
				Plural+="\""+"TS"+i+"\"}";
		}
		Plural+="\n})\n";
		
		Plural+="@ClassStates({\n";
		for (int i=dimesnion.getLowValueofInv();i<=dimesnion.getHighValueofInv();  i++){
			if (i<dimesnion.getHighValueofInv())
			Plural+="\t@State(name=\""+"TS"+i+"\"),\n";
			else
				Plural+="\t@State(name=\""+"TS"+i+"\")\n";
		}
		Plural+="\n})\n";
		return Plural;
	}
	
	public static String JmlMethodSpec2PluralMethodSpec(){
	
		if (requires.size()>1)
			return moreRequires();
		
		else if (requires.size()==1)
			return oneRequires();
		
		else if (requires.size()==0)
			return noRequires();
		return "";
		
	}
	private static String noRequires() {
		String plural="@Perm(requires="+"\""+getPerm()+"Alive\""+ ", ensures=\""+getPerm()+determineEnsures("")+"\")";
		return plural;
	}

	private static String oneRequires() {
		String plural;
		plural="@Perm(requires=\""+getPerm()+TS+requires.getFirst()+"\"";
		if (ensures!=null)
			plural+=", ensures=\""+getPerm()+determineEnsures(requires.getFirst())+"\"";
		plural+=")";
		return plural;
	}

	private static String moreRequires() {
		String plural;
		plural="@Cases({\n";
		for (String req:requires){
			plural+="@Perm(requires=\""+getPerm()+TS+req+"\"";
			if (ensures!=null){
				plural+=", ensures=\""+getPerm()+determineEnsures(req)+"\"";
			}
			plural+="),\n";
		}
		plural+="})";
		
		return plural;
	}

	private static String determineEnsures(String req) {
		
		int ts;
		if (ensures==null){
			return ensures="Alive";
		}
		else if (ensures.contains("old+")==true){
				
				ts=Integer.parseInt(req)+Integer.parseInt(ensures.substring(4,ensures.length()));
				return TS+ts;
			}
		else if (ensures.contains("old-")==true){
				ts=Integer.parseInt(req)-Integer.parseInt(ensures.substring(4,ensures.length()));
				return TS+ts;
			}
		else if (ensures.contains("old")==true)
				return TS+req;
		else
				return TS+ensures;		
	}


	private static String getPerm() {
		
		if (perm!=null)
			return perm+" (this) in ";
		else
			return "Pure (this) in ";
		
	}
}
