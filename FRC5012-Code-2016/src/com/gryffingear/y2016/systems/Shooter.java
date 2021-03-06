package com.gryffingear.y2016.systems;

import com.gryffingear.y2016.config.Constants;
import com.gryffingear.y2016.utilities.Debouncer;
import com.gryffingear.y2016.utilities.Loopable;
import com.gryffingear.y2016.utilities.MovingAverage;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Solenoid;

public class Shooter {

	private CANTalon shooterMotorA = null;
	private CANTalon shooterMotorB = null;

	
	public Shooter(int sma, int smb, int encPort) {

		shooterMotorA = configureTalon(new CANTalon(sma));
		shooterMotorB = configureTalon(new CANTalon(smb));
/*
		shooterMotorB.changeControlMode(CANTalon.TalonControlMode.Speed);
		shooterMotorB.setFeedbackDevice(CANTalon.FeedbackDevice.EncRising);
		shooterMotorB.configEncoderCodesPerRev(1);

		shooterMotorB.setProfile(0);	// Might have been the secret sauce to get it working.
		shooterMotorB.configNominalOutputVoltage(0.0, 0.0);
		shooterMotorB.configPeakOutputVoltage(12.0, -12.0);	// swap these if shooter is reversed 
		shooterMotorB.setP(20);
		shooterMotorB.setI(00);
		shooterMotorB.setD(0);
		shooterMotorB.setF(70);
		shooterMotorB.enableControl();
		*/
		shooterMotorA.changeControlMode(CANTalon.TalonControlMode.Follower);
		shooterMotorA.set(shooterMotorB.getDeviceID());
		shooterMotorB.reverseOutput(true);
		
		
		
		//encoder = new Counter(encPort);
		
	}

	private CANTalon configureTalon(CANTalon in) {

		in.clearStickyFaults();
		in.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		in.setVoltageRampRate(Constants.Shooter.RAMP_RATE);
		in.enableBrakeMode(false);
		in.enableControl();
		System.out.println("[CANTalon]" + in.getDescription() + 
				" Initialized at device ID: " + in.getDeviceID());
		return in;
	}

	public void setPercentVBus(double shooterv) {

		shooterMotorB.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		shooterMotorA.set(shooterMotorB.getDeviceID());
		shooterMotorB.set(shooterv);
	}
	
	public void setVoltage(double shooterv) {
		shooterMotorB.changeControlMode(CANTalon.TalonControlMode.Voltage);
		shooterMotorA.set(shooterMotorB.getDeviceID());
		shooterMotorB.set(shooterv);
	}

	public double getCurrent() {
		double answer = Math.abs(shooterMotorA.getOutputCurrent()) + 
						Math.abs(shooterMotorB.getOutputCurrent());
		answer /= 2.0;

		return answer;
	}

	Debouncer currentFilter = new Debouncer(1.00);

	private boolean m_atSpeed = false;

	public boolean atSpeed() {
		
		return Math.abs(this.getError()) < 15;

	}
	
	public double getSpeed() {
		//return filteredSpeed;
		
		return shooterMotorB.getEncVelocity();
	}
	
	public double getError() {
		return shooterMotorB.getClosedLoopError();
	}
	
	public double get() {
		return shooterMotorB.getOutputVoltage();
	}
	


}
