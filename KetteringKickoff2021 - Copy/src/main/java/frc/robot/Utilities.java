package frc.robot;

public class Utilities {
    public static double deaden(double input, double deadband) {
        deadband = Math.min(1, deadband);
        deadband = Math.max(0, deadband);
       
        if (Math.abs(input) - deadband < 0) {
          return 0;
        }
        else {
          return Math.signum(input) * ((Math.abs(input) - deadband) / (1 - deadband));
        }
    } 
}
