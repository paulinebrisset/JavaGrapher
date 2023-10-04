package Calculator.models;

import Calculator.exception.DivisionByZeroException;
import Calculator.exception.LogByZeroException;

public abstract class UnaryNode extends Node {
	protected Node childNode;

	public abstract float eval(float x) throws DivisionByZeroException, LogByZeroException;
	public abstract void buidTree();
}
