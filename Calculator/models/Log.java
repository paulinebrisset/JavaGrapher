package Calculator.models;

import Calculator.exception.DivisionByZeroException;
import Calculator.exception.LogByZeroException;

public class Log extends UnaryNode {

	public Log(Node cldNode) {
		this.childNode = cldNode;
	}

	@Override
	public float eval(float x) throws DivisionByZeroException, LogByZeroException {
		float result = (float) Math.log(this.childNode.eval(x));
		if (result == 0)
			throw new LogByZeroException();
		return (float) Math.log(this.childNode.eval(x));
	}

	@Override
	public void buidTree() {
		System.out.println("\t\tLog\n\t\t|");
		this.childNode.buidTree();
	}  
}
