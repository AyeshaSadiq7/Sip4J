package aeminium.gaknapsack;

public class Item {


//none(name) * none(weight) * none(value) -> unique(name) * unique(weight) * unique(value)
	public Item(String n, int w, int v) {
		this.name = n;
		this.weight = w;
		this.value = v;
	}

	public String name;
	public int weight;
	public int value;
}