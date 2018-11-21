package datastructure;

import java.util.LinkedList;

public class E_Field {

	private String name;
	private String type;
	private int modifier;
	
	private int objectIndex;
	private int classIndex;
	
	public E_Field() {
		classIndex = -1;
	}
	
	public void setName(String str) {
		name = str;
	}

	public void setType(String str) {
		type = str;
	}

	public void setModifier(int mod) {
		modifier = mod;
	}

	public String getName() {
		return name.toString();
	}

	public String getType() {
		return type.toString();
	}

	public int getModifier() {
		return modifier;
	}

	public void setObjectIndex(int objectIndex) {
		this.objectIndex = objectIndex;
	}

	public int getObjectIndex() {
		return objectIndex;
	}

	public void setClassIndex(int classIndex) {
		this.classIndex = classIndex;
	}

	public int getClassIndex() {
		return classIndex;
	}
}
