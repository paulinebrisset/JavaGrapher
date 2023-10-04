package Calculator.models;

import Calculator.exception.DivisionByZeroException;
import Calculator.exception.LogByZeroException;

public class Exp extends UnaryNode {

	public Exp(Node cldNode) {
		this.childNode = cldNode;
	}

	@Override
	public float eval(float x) throws DivisionByZeroException, LogByZeroException {
		return (float) Math.exp(this.childNode.eval(x));
	}

	@Override
	public void buidTree() {
		System.out.println("\t\tExp\n\t\t|");
		this.childNode.buidTree();
	}

}