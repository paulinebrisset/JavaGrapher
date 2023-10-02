package Grapher.models;

import Grapher.exception.DivisionByZeroException;
import Grapher.exception.LogByZeroException;

public class Cos extends UnaryNode{

	public Cos(Node cldNode){
		this.childNode = cldNode;
	}
	
	@Override
	public float eval(float x) throws DivisionByZeroException, LogByZeroException {
		return (float) Math.cos(this.childNode.eval(x));
	}

}