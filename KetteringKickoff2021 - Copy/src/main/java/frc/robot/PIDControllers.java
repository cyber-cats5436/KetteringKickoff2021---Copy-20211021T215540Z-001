package frc.robot;

import com.revrobotics.CANPIDController;

import frc.robot.framework.Motors;

public class PIDControllers {
    
    //public static CANPIDController kPIDClimb = new CANPIDController(Motors.climberMotor);
    public static CANPIDController kPIDConveyor = Motors.intakeConveyor.getPIDController();
    public static CANPIDController kPIDIntakeBottom = Motors.intakeBottom.getPIDController();
    public static CANPIDController kPIDIntakeMove = Motors.intakeMove.getPIDController();
    public static CANPIDController kPIDShooter = Motors.shooter.getPIDController();
    public static CANPIDController kPIDintakeSweeper = Motors.intakeSweeper.getPIDController();
    public static CANPIDController kPIDDriveFL = Motors.motorFL.getPIDController();
    public static CANPIDController kPIDDriveBL =Motors.motorBL.getPIDController();
    public static CANPIDController kPIDDriveFR = Motors.motorFR.getPIDController();
    public static CANPIDController kPIDDriveBR = Motors.motorBR.getPIDController();

    public static void configPID(double shooterkP) {
        kPIDShooter.setP(shooterkP);
        kPIDShooter.setI(0.0);
        kPIDShooter.setD(0.0);
    }

}
