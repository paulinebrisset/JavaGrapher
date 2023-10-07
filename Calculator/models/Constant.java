package Calculator.models;

public class Constant extends Node {

	float value;

	// Constructor
	public Constant(float v) {
		this.value = v;
	}

	@Override
	public float eval(float x) {
		return this.value;
	}

	@Override
	public void buidTree() {
		System.out.println("\n"+this.value);
	}
}