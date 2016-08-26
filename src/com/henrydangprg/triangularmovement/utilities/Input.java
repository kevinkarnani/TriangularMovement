package com.henrydangprg.triangularmovement.utilities;

import java.util.HashMap;
import java.util.Map;

public class Input {
	private Map<String, Boolean> input = new HashMap<String, Boolean>();
	
	public void setKeyState(String key, boolean state) {
		input.put(key.toUpperCase(), state);
	}
	
	public boolean getKeyState(String key) {
		try {
			return input.get(key);
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isClashing(String key1, String key2) {
		try {
			if (input.get(key1) && input.get(key2)) return true;
			else return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isArrowPressed() {
		if (this.getKeyState("UP") || this.getKeyState("DOWN") ||
				this.getKeyState("LEFT") || this.getKeyState("RIGHT")) return true;
		
		return false;
	}
	
	public boolean isInputPressed() {
		for(String key : input.keySet()) {
			if (input.get(key)) return true;
		}
		return false;
	}
}