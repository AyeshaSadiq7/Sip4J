package aeminium.gaknapsack;

public class Indiv implements Comparable<Indiv> {
	
	public boolean[] has;
	public double fitness = 0;
	public int size;
	boolean flag;

	//none(size) * none(has) -> unique(has) * unique(size)
	public Indiv(int size) {
		this.size = size;
		this.has = new boolean[size];
	}

	/*@Perm(requires="full(this) in alive",
			ensures="full(this) in alive")*/
	public void set(int w, boolean h) {
		has[w] = h;
	}
	// pure(fitness) -> pure(fitness)
	public int compareTo(Indiv other) {
		if (this.fitness == other.fitness) {
			return 0;
		} else if (this.fitness > other.fitness) {
			return 1;
		} else {
			return -1;
		}
	}
}