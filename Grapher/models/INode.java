package Grapher.models;

import Grapher.exception.DivisionByZeroException;
import Grapher.exception.LogByZeroException;

public interface INode {
	
	public float eval(float x) throws DivisionByZeroException, LogByZeroException;
}

