package Calculator.models;

import Calculator.exception.DivisionByZeroException;
import Calculator.exception.LogByZeroException;

public class Sin extends UnaryNode {

	public Sin(Node cldNode) {
		this.childNode = cldNode;
	}

	@Override
	public float eval(float x) throws DivisionByZeroException, LogByZeroException {
		return (float) Math.sin(this.childNode.eval(x));
	}

	@Override
	public void buidTree() {
		System.out.println("\nSin\n|");
		this.childNode.buidTree();
	}

}