package frc.robot.robotfunctions;

import com.revrobotics.ControlType;

import frc.robot.PIDControllers;
import frc.robot.framework.Motors;
import frc.robot.framework.Tools;

public class Shooter {
    public static void init() {
        Motors.shooter.clearFaults();
        
    }

    public static void runShooterMotorManual(String direction, double speed) {
        if (direction == "Shoot"){
            Motors.shooter.set(speed);
        } else if (direction == "Out"){
           Motors.shooter.set(-speed);
        }
    } 

    public static void run(double speed){
        PIDControllers.kPIDShooter.setReference(speed, ControlType.kVelocity);
    } 

    public static void stopShooterMotor(){
        Motors.shooter.set(0);
    }
}
//work on auton function