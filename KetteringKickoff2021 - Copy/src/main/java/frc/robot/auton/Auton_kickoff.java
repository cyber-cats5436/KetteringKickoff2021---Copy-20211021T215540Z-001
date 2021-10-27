package frc.robot.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.robotfunctions.Conveyor;
import frc.robot.robotfunctions.DriveBase;
import frc.robot.robotfunctions.Intake;
import frc.robot.robotfunctions.Shooter;


public class Auton_kickoff extends Robot{

    private static int step = 1;
    private static boolean initialPass = true;
    public static double initialDelayTime = 0.0;
    public static double autonBailTime = 0.0;


    
   public static void init() {
       //Reset the internal variables
        step = 1;
        initialPass = true;
        
    }

   
    public static void run(double delay, String choice) {        
        
        SmartDashboard.putNumber("Current Auton Step", step);
        
        switch(step) {

            case 1:   //Initial delay complete before shooting 
               if (initialPass){
                 initialDelayTime = Timer.getFPGATimestamp() + delay;
                 System.out.println("In Step 1");
                 System.out.println("Current Time = " + Timer.getFPGATimestamp() + "    Set Time to Delay " + initialDelayTime);
                 initialPass = false;
               }

               if (Timer.getFPGATimestamp() <= 1){
                 //Do nothing, couting down
               }else{
                 initialPass = true;
                 if (choice == "Shoot"){
                    step += 1;
                  }else if (choice == "Move Only"){
                    step = 3;
                 } else {
                     step = 3;
                 }
               }
            break;


            case 2:  //Shoot Balls
                if (initialPass){
                    autonBailTime = (13 - delay) + Timer.getFPGATimestamp();
                    initialPass = false;
                }
                    if(!AutomatedFunctions.runShooterAuton(autonBailTime)){
                        //Do nothing, running the routine
                    }/*else{
                        Shooter.stopShooterMotor();
                        Conveyor.stop();
                        initialPass = true;
                        step += 1;
                    }*/
            break;

            case 3:

                if(!AutomatedFunctions.runLinearDistanceWithoutEncoders("Backwards", .5, .5)) {
                    AutomatedFunctions.runLinearDistanceWithoutEncoders("Backwards", .5, .5);
                } else {
                    DriveBase.stopAllDrivetrainMotors();
                    step += 1;
                }
                   /* if( Timer.getFPGATimestamp() < 9) {
                        Conveyor.runConveyor("Out", .75);
                        Conveyor.runBottom("Out", .75);
                        Intake.stop();
                    } *//*else {
                        Conveyor.stop();
                        Conveyor.stopBottom();
                        step += 1;
                    }
                }
                */

            break;

            //case 4:  //Move Backwards 1 Robot Length
                //if (!AutomatedFunctions.runLinearDistanceAuton("PWM", 0.5, -Robot.robotLen)){
                    /*
                if (!AutomatedFunctions.runLinearDistanceWithoutEncoders("Backwards", 0.5, 5.0)){
                   ///Do Nothing, moving backwards
                } else{ 
                    DriveBase.stopAllDrivetrainMotors();
                    step +=1;
                }*/
           // break;
            
        }    

    }  //End of run method

/*
    //aligns robot to target
    public static void runVision(double delay, String choice) {        
        
        SmartDashboard.putNumber("Current Auton Step", step);
        
        switch(step) {

            case 1:   //Initial delay complete before shooting 
            if (initialPass){
                initialDelayTime = Timer.getFPGATimestamp() + delay;
                System.out.println("In Step 1");
                System.out.println("Current Time = " + Timer.getFPGATimestamp() + "    Set Time to Delay " + initialDelayTime);
                initialPass = false;
              }

              if (Timer.getFPGATimestamp() - initialDelayTime <= 0){
                //Do nothing, couting down
              }else{
                initialPass = true;
                if (choice == "Shoot"){
                   step += 1;
                 }else if (choice == "Move Only"){
                   step = 3;
                }
              }
            break;


            case 2:  //Shoot Balls
            if (initialPass){
                autonBailTime = (13 - delay) + Timer.getFPGATimestamp();
                initialPass = false;
            }
                if(!AutomatedFunctions.runShooterAuton(autonBailTime)){
                    //Do nothing, running the routine
                }else{
                    ApolloShooter.stopShooterMotor();
                    ApolloConveyor.stopConveyorUpperMotor();
                    initialPass = true;
                    step += 1;
                }
                //if (!AutomatedFunctions.runLinearDistanceAuton("PWM", 0.5, -Robot.robotLen)){
                
               
            break;


            case 3:  //Move Backwards 1 Robot Length
            if (!AutomatedFunctions.runLinearDistanceWithoutEncoders("Backwards", 0.25, 1.0)){
                ///Do Nothing, moving backwards
             } else{ 
                 ApolloDrive.stopAllDrivetrainMotors();
                 step +=1;
             }
             break;
            

            case 4:
            ApolloDrive.stopAllDrivetrainMotors();
            step +=1;
            break;
        }    

    }  //End of run method */
}
 //End of class



