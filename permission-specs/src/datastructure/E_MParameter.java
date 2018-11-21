package datastructure;

public class E_MParameter {

	private String name;
	private String type;
	private int position;

	// LinkedList<E_Specification> requires;
	// LinkedList<E_Specification> ensures;

	public E_MParameter(String name, String type, int position) {

		this.name = name;
		this.type = type;
		this.position = position;

	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setNumber(int n) {
		position = n;
	}

	public int getNumber() {
		return position;
	}

	public void setName(String str) {
		name = str;
	}

	public void setType(String str) {
		type = str;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name.toString();
	}

}
