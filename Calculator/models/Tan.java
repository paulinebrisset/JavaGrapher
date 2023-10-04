package Calculator.models;

import Calculator.exception.DivisionByZeroException;
import Calculator.exception.LogByZeroException;

public class Tan extends UnaryNode {

	public Tan(Node cldNode) {
		this.childNode = cldNode;
	}

	@Override
	public float eval(float x) throws DivisionByZeroException, LogByZeroException {
		return (float) Math.tan(this.childNode.eval(x));
	}

	@Override
	public void buidTree() {
		System.out.println("\t\tTan\n\t\t|");
		this.childNode.buidTree();
	}
}
