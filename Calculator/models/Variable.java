package Calculator.models;

public class Variable extends Node {

	public Variable() {
	}

	@Override
	public float eval(float x) {
		return x;
	}

	@Override
	public void buidTree() {
		System.out.println("x");
	}
}
