package com.gryffingear.y2016.utilities;

public class PulseTriggerBoolean {

	private boolean state = false;
	private boolean oldIn = false;

	public void set(boolean in) {

		if (oldIn == false && in == true) {
			state = true;
		} else {
			state = false;
		}
		oldIn = in;
	}

	public boolean get() {

		return state;
	}

	public void reset() {

		state = false;
		oldIn = false;
	}
}