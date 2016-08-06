package com.henrydangprg.triangularmovement.component;

public class Encoder {
	private int value = 0;
	
	public void setValue(int value){
		this.value = value;
	}
	
	public double getValue(){
		return this.value;
	}
}
