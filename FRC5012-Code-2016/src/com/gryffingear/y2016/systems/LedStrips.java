package com.gryffingear.y2016.systems;

import com.gryffingear.y2016.config.Ports;

import edu.wpi.first.wpilibj.Solenoid;

public class LedStrips {


	Solenoid B = null;
	Solenoid C = null;
	Solenoid D = null;

	public LedStrips( int BChannel, int CChannel) {

		B = new Solenoid(Ports.Pneumatics.PCM_CAN_ID, BChannel);
		C = new Solenoid(Ports.Pneumatics.PCM_CAN_ID, CChannel);

	}

	

	public void setB(boolean state) {
		B.set(state);
	}

	public void setC(boolean state) {
		C.set(state);
	}

	public void setD(boolean state) {
		D.set(state);
	}

}
