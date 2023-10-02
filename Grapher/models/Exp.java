package Grapher.models;

import Grapher.exception.DivisionByZeroException;
import Grapher.exception.LogByZeroException;

public class Exp extends UnaryNode{

	public Exp(Node cldNode){
		this.childNode = cldNode;
	}
	
	@Override
	public float eval(float x) throws DivisionByZeroException, LogByZeroException {
		return (float) Math.exp(this.childNode.eval(x));
	}

}