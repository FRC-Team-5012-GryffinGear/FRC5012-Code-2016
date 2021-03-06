package com.gryffingear.y2016.config;

public class Ports {

	public static class Controls {

		public static int DRIVER_PORT_A = 0;
		public static int DRIVER_PORT_B = 1;
		public static int OPERATOR_PORT = 2;
	}

	public static class Drivetrain {

		public static int DRIVE_LEFT_A_PORT = 0;
		public static int DRIVE_LEFT_B_PORT = 1;
		public static int DRIVE_RIGHT_A_PORT = 4;
		public static int DRIVE_RIGHT_B_PORT = 3;
	}

	public static class Intake {

		public static int INTAKE_MOTOR = 7;
		public static int INTAKE_SOLENOID = 7;
		public static int STAGE_SENSOR = 0;

	}

	public static class Shooter {

		public static int SHOOTER_MOTOR_A = 2;
		public static int SHOOTER_MOTOR_B = 5;
		public static int SHOOTER_ENCODER_PORT = 1;
		public static int FLASHLIGHT_PORT = 6;
		public static int SHOOTER_INDICATOR_LIGHT = 3;
	}

	public static class Stager {
		public static int STAGER_MOTOR = 8;
		public static int STAGER_INDICATOR_LIGHT = 2;
	}

	public static class Pixycam {
		public static int PIXYCAM_PORT = 1;
	}


	public static class Arm {

		public static int ARM_SOLENOID = 5;
	}

	public static class Winch {
		public static int WINCH_SOLENOID = 4;
		public static int WINCH_MOTOR_A = 9;
		public static int WINCH_MOTOR_B = 10;
		public static int CLIMBER_MOTOR = 6;
		public static int CLIMBER_SENSOR = 1;
	}

	public static class Pneumatics {

		public static int PCM_CAN_ID = 0;

	}

}
