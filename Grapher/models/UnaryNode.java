package Grapher.models;

import Grapher.exception.DivisionByZeroException;
import Grapher.exception.LogByZeroException;

public abstract class UnaryNode extends Node{
	protected Node childNode;
	
	public abstract float eval(float x) throws DivisionByZeroException, LogByZeroException;
}
