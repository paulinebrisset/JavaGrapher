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
		// TODO Auto-generated method stub
		return this.leftNode.eval(x) + this.rightNode.eval(x);
	}

}
