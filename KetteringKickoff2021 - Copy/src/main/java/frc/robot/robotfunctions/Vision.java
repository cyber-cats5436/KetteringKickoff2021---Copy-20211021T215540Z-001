package frc.robot.robotfunctions;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Vision {
    
    public static NetworkTable tableLimelight;
    public static NetworkTableEntry tx; //horizontal offset
    public static NetworkTableEntry ty; //verticle offset
    public static NetworkTableEntry ta; //area of target 
    public static double horizontalError = 0.0;
    public static double verticalError = 0.0;
    public static double area = 0.0;
    public static double xOffset;
    public static Double aim = -0.1;
    public static Double distance = -0.1;
    public static Double min_aim = -0.1;
    public static double visionSpeed;


    public static void init() {
        tableLimelight = NetworkTableInstance.getDefault().getTable("limelight");
        tx = tableLimelight.getEntry("tx"); // communicates horizontal degree offset from target
        ty = tableLimelight.getEntry("ty"); // communicates verticle degree offset from target
        ta = tableLimelight.getEntry("ta"); // communicates percentage of image the target takes up
    }

    public static void updateLimelight() {
        //System.out.println("updating limelight");
        horizontalError = tx.getDouble(0.0); 
        verticalError = ty.getDouble(0.0);
        area = ta.getDouble(0.0);
      }


    public static double getTx() {
        return horizontalError;
    }

    public static double getTy() {
        return verticalError;
    }

    public static double getTa() {
        return area;
    }

    public static boolean alignX(double offset) {
        boolean targetAligned = false;
        double speed;
        double currentError = Math.abs(offset);
        currentError = Math.max(currentError, .1);
        speed = Math.min(.02 * currentError, 1);

        if(offset > 0) {
            DriveBase.drive(speed, speed);

        } else if(offset < 0) {
            DriveBase.drive(-speed, -speed);
            
        }

        if(currentError == 1) {
            targetAligned = true;
            DriveBase.stopAllDrivetrainMotors();

        }
        
        return targetAligned;
    }

    public static boolean alignY(double offset) {
        boolean targetAligned = false;
        double speed;

        double currentError = Math.abs(offset);

        currentError = Math.max(currentError, .1);

        speed = Math.min(.09 * currentError, 1);

        if( offset < 0) {
            DriveBase.drive(speed, -speed);
        } else if(offset > 0) {
            DriveBase.drive(-speed, speed);
        }

        if (currentError == 1) {
            DriveBase.stopAllDrivetrainMotors();
        }



        return targetAligned;
    }


    public boolean alignGyro() {
        boolean targetAligned = false;
        return targetAligned;
    }

    public static double getXOffset() {
      

        if(horizontalError > -2.0 && horizontalError < 2.0) {
          horizontalError = 0;
        } 
    
        return horizontalError;
      } 

      public static double getYOffset(double setPoint, double op) {
          double offset = 0.0;
          offset = op - setPoint;
          return offset;
      }

    public static double getSetPoint(String zone) {
        double setPoint = 0.0;

        if(zone.equals("Y")) {
            setPoint = 0.0;
        } else if(zone.equals("B")) {
            setPoint = -6.7;
        }else if (zone.equals("R")) {
            setPoint = -11;
        }

        return setPoint;
    }

    //d = distance to target
    public static double calcRPM(double d) {
        System.out.println("in calcRPM()");

        double rpm = 4500; //placeholder -- need to use distance to calculate rpm at those difference distances
        
        return rpm;
    }
  
   

    
}
