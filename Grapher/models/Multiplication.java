package Grapher.models;

import Grapher.exception.DivisionByZeroException;
import Grapher.exception.LogByZeroException;

public class Multiplication extends BinaryNode{

	
	public Multiplication(Node lf,Node rg){
		this.leftNode = lf;
		this.rightNode = rg;
	}
	@Override
	public float eval(float x) throws DivisionByZeroException, LogByZeroException {
		// TODO Auto-generated method stub
		return this.leftNode.eval(x) * this.rightNode.eval(x);
	}

}
