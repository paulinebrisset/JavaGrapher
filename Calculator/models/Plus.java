package Calculator.models;

import Calculator.exception.DivisionByZeroException;
import Calculator.exception.LogByZeroException;

public class Plus extends BinaryNode {

	public Plus(Node lf, Node rg) {
		this.leftNode = lf;
		this.rightNode = rg;
	}

	@Override
	public float eval(float x) throws DivisionByZeroException, LogByZeroException {
		return this.leftNode.eval(x) + this.rightNode.eval(x);
	}

	@Override
	public void buidTree() {
		System.out.println("\t+");
		this.leftNode.buidTree();
		System.out.print("\t\t");
		this.rightNode.buidTree();
	}

}
