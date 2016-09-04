package com.henrydangprg.triangularmovement.utilities;

import java.util.HashMap;
import java.util.Map;

public class Input {
    private Map<String, Boolean> input = new HashMap<String, Boolean>();

    /**
     * Sets a boolean for a button.
     * 
     * @param key
     *            the button being pressed.
     * @param state
     *            whether the button is being pressed.
     */
    public void setKeyState(String key, boolean state) {
	input.put(key.toUpperCase(), state);
    }

    /**
     * Returns a boolean whether this button is pressed or not.
     * 
     * @param key
     *            the button pressed.
     * @return <tt>true</tt> if this button is pressed.
     */
    public boolean getKeyState(String key) {
	try {
	    return input.get(key);
	} catch (Exception e) {
	    return false;
	}
    }

    /**
     * Returns true if two buttons are pressed at the same time.
     * 
     * @param key1
     *            a button being pressed.
     * @param key2
     *            the second button being pressed.
     * @return <tt>true</tt> if two buttons are pressed at the same time.
     */
    public boolean isClashing(String key1, String key2) {
	try {
	    if (input.get(key1) && input.get(key2))
		return true;
	    else
		return false;
	} catch (Exception e) {
	    return false;
	}
    }

    /**
     * Returns true if any arrow keys are pressed.
     * 
     * @return <tt>true</tt> if any of the arrow keys are pressed.
     */
    public boolean isArrowPressed() {
	if (this.getKeyState("UP") || this.getKeyState("DOWN") || this.getKeyState("LEFT") || this.getKeyState("RIGHT"))
	    return true;

	else
	    return false;
    }

    /**
     * Returns true if any button is pressed.
     * 
     * @return <tt>true</tt> if any button is pressed.
     */
    public boolean isInputPressed() {
	for (String key : input.keySet()) {
	    if (input.get(key))
		return true;
	}
	return false;
    }
}