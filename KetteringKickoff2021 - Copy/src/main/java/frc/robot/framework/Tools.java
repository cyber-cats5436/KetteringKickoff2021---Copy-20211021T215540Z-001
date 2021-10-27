package frc.robot.framework;

import javax.management.timer.Timer;

import com.revrobotics.CANEncoder;

public class Tools {
    public static CANEncoder intakeLiftEncoder = Motors.intakeMove.getEncoder();
    public static CANEncoder climberEncoder = Motors.climberMotor.getEncoder();
    public static CANEncoder shooterEncoder = Motors.shooter.getEncoder();


    //conveyor timer
    public static Timer conveyorTimer = new Timer();
    public static Timer shooterTimer = new Timer();
    public static Timer autonTimer = new Timer();

}
//Confirm last