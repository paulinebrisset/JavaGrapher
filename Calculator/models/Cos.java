package Calculator.models;

import Calculator.exception.DivisionByZeroException;
import Calculator.exception.LogByZeroException;

public class Cos extends UnaryNode {

	public Cos(Node cldNode) {
		this.childNode = cldNode;
	}

	@Override
	public float eval(float x) throws DivisionByZeroException, LogByZeroException {
		return (float) Math.cos(this.childNode.eval(x));
	}

	@Override
	public void buidTree() {
		System.out.println("\t\tCos\n\t\t|");
		this.childNode.buidTree();
	}

}