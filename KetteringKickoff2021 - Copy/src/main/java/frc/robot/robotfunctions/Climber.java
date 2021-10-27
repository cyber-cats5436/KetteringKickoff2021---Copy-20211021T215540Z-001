package frc.robot.robotfunctions;

import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.framework.Motors;

public class Climber {
    
    public static void init() {
        Motors.climberMotor.clearFaults();
        
    }
/*
    public static void climbManual(Boolean climbUpButtonStatus, Boolean climbDownButtonStatus, Double speed) {
        if (climbUpButtonStatus == true) {
            Motors.climberMotor.set(speed);
        } else if(climbDownButtonStatus == true) {
            Motors.climberMotor.set(-speed);
        } else {
            Motors.climberMotor.set(0);
        }                                        
    } */
    public static void stopClimberMotor() {
        Motors.climberMotor.set(0.0);
    }

    public static void runClimberManual(String direction, Double speed) {
        if (direction == "Lift") {
            Motors.climberMotor.set(speed);
        } else if (direction == "Lower") {
            Motors.climberMotor.set(-speed);
        } else {
            stopClimberMotor();
        }
    }

    public static void retractPin() {
        Motors.climberSolenoid.set(ControlMode.PercentOutput, .7);
    }

    public static void engagePin() {
        Motors.climberSolenoid.set(ControlMode.PercentOutput, 0);
    }
}
//Needs testing to confirm
