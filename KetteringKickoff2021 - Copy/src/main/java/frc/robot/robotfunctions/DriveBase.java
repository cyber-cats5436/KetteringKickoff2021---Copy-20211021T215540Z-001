package frc.robot.robotfunctions;

import frc.robot.framework.Motors;

public class DriveBase {
    
      

  public static void init() {

    Motors.motorFL.clearFaults();
    Motors.motorBL.clearFaults();
    Motors.motorFR.clearFaults();
    Motors.motorBR.clearFaults();
}  //End of init method





//Drive Command to send values to DriveBase Motors
public static void drive(double left, double right){
  Motors.motorFL.set(left);
  Motors.motorBL.set(left);
  Motors.motorFR.set(right);
  Motors.motorBR.set(right);
}  //End of driveRobot

public static double driveProcessing(double xAxis, double yAxis, String direction){

  double returnValue = 0;
  double fwd = -yAxis;
  double strafe = xAxis;
  

  double L = fwd + strafe;
  double R = fwd - strafe;


  if (direction.equals("Left")){
    returnValue = L;
  } else {
    returnValue = R;
  }
  return returnValue;
} // End of driveProcessing

public static void stopAllDrivetrainMotors(){
  Motors.motorFL.set(0);
  Motors.motorFR.set(0);
  Motors.motorBL.set(0);
  Motors.motorBR.set(0);
}//End of stopAllDrivetrainMotors()




}
