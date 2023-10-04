package Calculator.models;

import Calculator.exception.DivisionByZeroException;
import Calculator.exception.LogByZeroException;

public abstract class BinaryNode extends Node {

	protected Node leftNode;
	protected Node rightNode;

	public abstract float eval(float x) throws DivisionByZeroException, LogByZeroException;
	public abstract void buidTree();
}
