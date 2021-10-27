package frc.robot.framework;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Dashboard {
    public static SendableChooser<String> autonSelector = new SendableChooser<>();

    public static void init() {
        SmartDashboard.putNumber("Shooter RPM: ", 0.0);
        SmartDashboard.putNumber("Limelight X:", 0.0);
        SmartDashboard.putNumber("Limelight Y:", 0.0);
        SmartDashboard.putNumber("Shooter RPM Current: ", 0.0);
        SmartDashboard.putNumber("Auton delay: ", 0.0);
        SmartDashboard.putNumber("Autonomous Delay", 0);

        SmartDashboard.putNumber("Current Auton Step", 0);
        SmartDashboard.putNumber("Balls Shot in Auton", 0);
        autonSelector.setDefaultOption("Shoot", "Shoot");
        autonSelector.addOption("Move Only", "Move Only");
        SmartDashboard.putData("Autonomous Choices", autonSelector);

    }
    public static double getAutonDelay(){
        return SmartDashboard.getNumber("Autonomous Delay", 0);
    }

    public static String getAutonChoice(){
        return autonSelector.getSelected();
    }

    
    public static double getRPM() {
        return SmartDashboard.getNumber("Shooter RPM: ", 0.0);
    }
    public static double getDelay() {
        return SmartDashboard.getNumber("Auton delay: ", 0.0);
    }
    

}
