package frc.robot.auton;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;
import frc.robot.framework.Motors;
import frc.robot.framework.Tools;
import frc.robot.robotfunctions.Conveyor;
import frc.robot.robotfunctions.DriveBase;
import frc.robot.robotfunctions.Shooter;

public class AutomatedFunctions extends Robot {

    private static boolean linearInit = true;
    private static boolean linearTimeInit = true;
    private static double linearTimeTarget = 0;
    private static double targetLinearDistanceL = 0;
    private static double targetLinearDistanceR = 0;
    private static double currentLinearPosition = 0;

    private static boolean conveyorInit = true;
    private static boolean latchConveyor = false;
    private static double conveyorRunTimeTarget = 0;
    private static int ballsAttemptedinAuton = 0;
    private static double turnSpeed;
    private static boolean isFinished = false;


    public static void init(){
      linearInit = true;
      linearTimeInit = true;
      conveyorInit = true;
      latchConveyor = false;
      ballsAttemptedinAuton = 0;
    }
    
    public static void visionAlign(double offset, double speed) {

        
        if(Math.abs(offset) > 0.0) {
            Motors.motorBL.set(speed);
            Motors.motorBR.set(speed);
            Motors.motorFR.set(speed);
            Motors.motorFL.set(speed); 
        } else{ 
            Motors.motorBL.set(0.0);
            Motors.motorBR.set(0.0);
            Motors.motorFR.set(0.0);
            Motors.motorFL.set(0.0); 
        }
           
      
        }
    

    public static boolean runLinearDistanceWithoutEncoders(String direction, double speed, double time){
        boolean isFinished = false;
        
        if (linearTimeInit){
            linearTimeTarget = Timer.getFPGATimestamp() + time;
            linearTimeInit = false;
        }
 
        if (Timer.getFPGATimestamp() < linearTimeTarget){
            if (direction == "Backwards"){
              Motors.motorFL.set(-speed);
              Motors.motorBL.set(-speed);
              Motors.motorFR.set(speed);
              Motors.motorBR.set(speed);
            }else if (direction == "Forward"){
            Motors.motorFL.set(speed);
            Motors.motorBL.set(speed);
            Motors.motorFR.set(-speed);
            Motors.motorBR.set(-speed);
            }
            isFinished = false;
        } else{
            DriveBase.stopAllDrivetrainMotors();
            isFinished = true;
        }
        
        return isFinished;
    }


    public static boolean shooterManualAuton(double timeout) {

        Boolean routineComplete = false;

        Motors.shooter.set(1.0);

        if (latchConveyor && !routineComplete){
            if (runConveyorAuton(1.2)){
               ballsAttemptedinAuton += 1;
               latchConveyor = false;
            }
        }
           
        if (ballsAttemptedinAuton ==3 || Timer.getFPGATimestamp() >= timeout){
            Motors.shooter.set(0.0);
            Motors.intakeConveyor.set(0);
            System.out.println("Finished shooting all balls in auton");
            Motors.shooter.set(0.0);
            routineComplete = true;
        }
            
        return routineComplete;

    }

   
    public static boolean runShooterAuton(double timeout) {
        
        Boolean shooterUpToSpeed = false;
        Boolean routineComplete = false;
       
        //Start the motors either with max PWM or run to a set speed
        Shooter.run(4200); 
        //Shooter.runShooterMotorAuto(4200);
        SmartDashboard.putNumber("Current Auton Step", 25);
        SmartDashboard.putNumber("Balls Shot in Auton", ballsAttemptedinAuton);

        //Check to see if the shooter motor has reached appropriate speed        
        if( Tools.shooterEncoder.getVelocity() < 3900) { //Revving up the shoot motor
            shooterUpToSpeed = false;
        } else if( Tools.shooterEncoder.getVelocity() >= 3900){
            shooterUpToSpeed = true;
            latchConveyor = true;  //Don't want control loop to drop moving conveyor if speed dips during timer
        } 
        
        //Run the conveyor to move the balls into shooter when shooter is at speed
        if (latchConveyor && !routineComplete){
            if (runConveyorAuton(1.2)){
               ballsAttemptedinAuton += 1;
               latchConveyor = false;
            }
        }
           
        if (ballsAttemptedinAuton ==3 || Timer.getFPGATimestamp() >= timeout){
            Motors.shooter.set(0.0);
            Motors.intakeConveyor.set(0);
            System.out.println("Finished shooting all balls in auton");
            routineComplete = true;
        }
            
        return routineComplete;
    } //End of runShooterAuton method


    public static boolean runConveyorAuton(double delay){
        boolean isFinished = false;
        
        if (conveyorInit){
            conveyorRunTimeTarget = Timer.getFPGATimestamp() + delay;
            System.out.println("Current Time = " + Timer.getFPGATimestamp()); 
            System.out.println("End Time for Conveyor Pulse" + conveyorRunTimeTarget);
            conveyorInit = false;
          }

          if (Timer.getFPGATimestamp() - conveyorRunTimeTarget <= 0){
            Conveyor.runConveyor( "In", 0.5);
            isFinished = false;
          }else{
            Conveyor.stop();
            conveyorInit = true;
            isFinished = true;
          }

          return isFinished;
    }  //End of runConveyorAuton

    

    
   

}