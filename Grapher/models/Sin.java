package Grapher.models;

import Grapher.exception.DivisionByZeroException;
import Grapher.exception.LogByZeroException;

public class Sin extends UnaryNode{

	
	public Sin(Node cldNode){
		this.childNode = cldNode;
	}
	@Override
	public float eval(float x) throws DivisionByZeroException, LogByZeroException {
		return (float) Math.sin(this.childNode.eval(x));
	}

}