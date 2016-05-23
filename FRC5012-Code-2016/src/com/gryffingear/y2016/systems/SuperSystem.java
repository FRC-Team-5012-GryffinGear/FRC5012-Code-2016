package com.gryffingear.y2016.systems;

import com.gryffingear.y2016.config.Constants;
import com.gryffingear.y2016.config.Ports;
import com.gryffingear.y2016.utilities.PulseTriggerBoolean;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SuperSystem {

	private static SuperSystem instance = null;
	public LedStrips led = null;
	public Drivetrain drive = null;
	public Intake intake = null;
	public Shooter shoot = null;
	public Compressor compressor = null;
	public Climber climb = null;

	private SuperSystem() {

		drive = new Drivetrain(Ports.Drivetrain.DRIVE_LEFT_A_PORT, Ports.Drivetrain.DRIVE_LEFT_B_PORT,
				Ports.Drivetrain.DRIVE_RIGHT_A_PORT, Ports.Drivetrain.DRIVE_RIGHT_B_PORT);

		led = new LedStrips(Ports.Leds.LED_STRIP_1_PORT, Ports.Leds.LED_STRIP_2_PORT, Ports.Leds.LED_STRIP_3_PORT);

		intake = new Intake(Ports.Intake.INTAKE_MOTOR, Ports.Intake.INTAKE_SOLENOID, Ports.Intake.STAGE_SENSOR);
		
		// Shoot? Yes, shoot.
		shoot = new Shooter(Ports.Shooter.SHOOTER_MOTOR_A, Ports.Shooter.SHOOTER_MOTOR_B, Ports.Shooter.HOOD_SOLENOID);

		climb = new Climber(Ports.Climber.CLIMBER_SOLENOID, Ports.Climber.CLIMBER_MOTOR);
		
		
		compressor = new Compressor(Ports.Pneumatics.PCM_CAN_ID);
		compressor.setClosedLoopControl(true);
		compressor.start();

	}

	public static SuperSystem getInstance() {

		if (instance == null) {
			instance = new SuperSystem();
		}
		return instance;
	}

	public void drive(double leftIn, double rightIn) {

		double throttle = (leftIn + rightIn) / 2.0;
		double turning = (leftIn - rightIn) / 2.0;

		turning = ((turning * Math.abs(turning)) + turning) / 2.0;

		drive.tankDrive(throttle + turning, throttle - turning);
	}
	
	boolean shooting = false;

	boolean atSpeed = false;

	boolean intakePos = false;

	boolean hoodPos = false;

	PulseTriggerBoolean intakeToggle = new PulseTriggerBoolean();

	public void magicshot(boolean toggleIntakePos, boolean wantIntake, boolean wantLowGoal, boolean wantHighGoal) {

		double intakeOut = 0.0;
		double shooterOut = 0.0;

		if (wantLowGoal) {
			intakeOut = Constants.Intake.INTAKE_OUT;
			shooting = false;
		} else if (wantHighGoal) {
			shooterOut = Constants.Shooter.SHOOTING_VOLTAGE;

			atSpeed = shoot.atSpeed();
			if (atSpeed) {
				shooting = true;
			}

			intakeOut = wantIntake ? Constants.Intake.INTAKE_IN : 0.0;
		} else {
			intakeOut = wantIntake ? Constants.Intake.INTAKE_IN : 0.0;

			if (intake.getBallStaged()) {
				if (intakeOut > 0.0)
					intakeOut = 0;
			}

			shooting = false;
		}
		shooting = false;

		intakeToggle.set(toggleIntakePos);

		if (intakeToggle.get()) {
			intakePos = !intakePos;
		}

		hoodPos = wantHighGoal;

		magicshotRaw(intakePos, shooterOut, intakeOut, hoodPos);

	}
	
	//public void block(double blockerSpeed){
	//	blocker.runBlocker(blockerSpeed);
	//}

	public void magicshotRaw(boolean intakeState, double shooterSpeed, double intakeSpeed, boolean hoodState) {
		intake.setIntake(intakeState);
		//shoot.runShooter(shooterSpeed);
		intake.runIntake(intakeSpeed);
		//led.setB(intake.getBallStaged());
		led.setA(atSpeed);
		shoot.setHood(hoodState);
	}
	
	

	public void updateSmartDashboard() {
		SmartDashboard.putNumber("ShooterCurrent", shoot.getCurrent());
		SmartDashboard.putBoolean("AtSpeed", atSpeed);
		SmartDashboard.putBoolean("Shooting", shooting);
		//
		// SmartDashboard.putBoolean("extBall", intake.getBallEntered());
		// SmartDashboard.putBoolean("intBall", intake.getBallStaged());
		SmartDashboard.putNumber("DriveTotalCurrent", drive.getTotalCurrent());
	}

	public void poke() {
		intake.update();
		shoot.update();
	}

}