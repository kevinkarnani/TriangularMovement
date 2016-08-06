package com.henrydangprg.triangularmovement.component;

public class Ghost {

	private Encoder encoderLeft;
	private Encoder encoderTop;
	private Encoder encoderRight;
	
	public Ghost(Encoder encoderLeft, Encoder encoderTop, Encoder encoderRight){
		this.encoderLeft = encoderLeft;
		this.encoderTop = encoderTop;
		this.encoderRight = encoderRight;
	}
	
	public Coordinate getPosition(){
		//Stub method
		return null;
	}
}
