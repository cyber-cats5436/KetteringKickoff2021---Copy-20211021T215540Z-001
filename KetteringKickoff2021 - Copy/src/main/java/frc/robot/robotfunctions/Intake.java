package frc.robot.robotfunctions;

//import com.revrobotics.ControlType;

import frc.robot.PIDControllers;
import frc.robot.framework.Motors;
import frc.robot.framework.Tools;

public class Intake {
    
   
    public static void init() {
        Motors.intakeBottom.clearFaults();
        Motors.intakeSweeper.clearFaults();
        Motors.intakeMove.clearFaults();
    }

    public static void lift(String direction, double speed){
        if (direction == "Down"){
            Motors.intakeMove.set(-speed);
    }   else if (direction == "Up"){
        Motors.intakeMove.set(speed);
    }

    }


    public static void stop(){
        Motors.intakeMove.set(0);
    }

    public static void sweep(String direction, double speed){
        if (direction == "In"){
            Motors.intakeSweeper.set(speed);    
    }   else if (direction == "Out")
            Motors.intakeSweeper.set(-speed);
    }

    public static void stopSweep(){
        Motors.intakeSweeper.set(0);
    }
}
//Needs testing to confirm