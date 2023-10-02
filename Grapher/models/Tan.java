package Grapher.models;

import Grapher.exception.DivisionByZeroException;
import Grapher.exception.LogByZeroException;

public class Tan extends UnaryNode{

	public Tan(Node cldNode){
		this.childNode = cldNode;
	}
	
	@Override
	public float eval(float x) throws DivisionByZeroException, LogByZeroException {
		return (float) Math.tan(this.childNode.eval(x));
	}
}
