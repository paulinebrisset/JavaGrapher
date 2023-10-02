package Grapher.models;

public class Constant extends Node{
	
	float value;
	
	//Constructor
	public Constant(float v){
		this.value = v;
	}

	@Override
	public float eval(float x) {
		// TODO Auto-generated method stub
		return this.value;
	}
}