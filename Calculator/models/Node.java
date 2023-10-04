package Calculator.models;

import Calculator.exception.DivisionByZeroException;
import Calculator.exception.LogByZeroException;

public class Node implements INode {

	public Node() {

	}

	public float eval(float x) throws DivisionByZeroException, LogByZeroException {
		return 0;
	}

	public void buidTree() {
		System.out.println("Generic Tree");
	}
}
