package com.gryffingear.y2016.Autonomous;

import com.gryffingear.y2016.Auton.Commands.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/********** GENERATED BY AUTON BUILDER **********/

public class BatchTestAuton2 extends CommandGroup {
	public BatchTestAuton2() {
		//this is a comment
		this.addSequential(new ArcadeDriveCommand(1.0, 0.0, 10.0));
		this.addSequential(new IntakeShooterCommand(true, 1.0, 0.0));
	}

	public String toString() {
		 return "BatchTestAuton2";
	}

/********** INPUT FILE CONTENTS: **********
#BatchTestAuton2
//this is a comment
drive 1.0 10.0
intake up 1.0 0.0

*****************************************/

}