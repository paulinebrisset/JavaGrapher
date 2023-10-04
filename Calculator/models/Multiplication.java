package Calculator.models;

import Calculator.exception.DivisionByZeroException;
import Calculator.exception.LogByZeroException;

public class Multiplication extends BinaryNode {

	public Multiplication(Node lf, Node rg) {
		this.leftNode = lf;
		this.rightNode = rg;
	}

	@Override
	public float eval(float x) throws DivisionByZeroException, LogByZeroException {
		// TODO Auto-generated method stub
		return this.leftNode.eval(x) * this.rightNode.eval(x);
	}

	@Override
	public void buidTree() {
		// System.out.print("\t\t*\n\t/\t\\");;
		this.leftNode.buidTree();
		// System.out.print("\t\t");
		this.rightNode.buidTree();
	}

}
