package Calculator.models;

import Calculator.exception.DivisionByZeroException;
import Calculator.exception.LogByZeroException;

public interface INode {

	public float eval(float x) throws DivisionByZeroException, LogByZeroException;
}
