package frc.robot.robotfunctions;

import frc.robot.framework.Motors;
//import frc.robot.framework.Tools;

public class Conveyor {
    
    public static void init() {
        Motors.intakeConveyor.clearFaults();
        Motors.intakeBottom.clearFaults();
   
    }
    
    public static void runConveyor(String direction, double speed){
        if (direction.equals("In")){
            Motors.intakeConveyor.set(speed);
        }else if (direction.equals("Out")){
            Motors.intakeConveyor.set(-speed);
        }
    }

    
    public static void stop(){
        Motors.intakeConveyor.set(0);
    }



    public static void runBottom (String direction, double speed){
        if (direction.equals("In")){
            Motors.intakeBottom.set(-speed);
        } else if (direction.equals("Out")){
            Motors.intakeBottom.set(speed);
        }
    }  


    public static void stopBottom(){
        Motors.intakeBottom.set(0);
    }




}
//Needs testing to confirm