// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.auton.AutomatedFunctions;
import frc.robot.auton.Auton_kickoff;
import frc.robot.framework.Controls;
import frc.robot.framework.Dashboard;
import frc.robot.framework.Motors;
import frc.robot.framework.Tools;
import frc.robot.robotfunctions.Climber;
import frc.robot.robotfunctions.Conveyor;
import frc.robot.robotfunctions.DriveBase;
import frc.robot.robotfunctions.Intake;
import frc.robot.robotfunctions.Shooter;
import frc.robot.robotfunctions.Vision;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  double shootRPM = 0.0;
  private double autonomousShootDelay = 0;
  private double timeToExecuteAuton = 0;
  private String autonRoutine = "None Selected";


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    DriveBase.init();
    Vision.init();
    Dashboard.init();

    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    
    updateSmartDashboard();
    Vision.updateLimelight();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  
      autonomousShootDelay = Dashboard.getAutonDelay();  //get the delay from the driveTeam
      autonRoutine = Dashboard.getAutonChoice(); //get the choice of move or shoot and move from the driveTeam
      autonomousShootDelay = boundAutonomousDelay(autonomousShootDelay, autonRoutine); //binds the value to safeguard from bad input
      SmartDashboard.putNumber("Autonomous Delay", autonomousShootDelay); //Overwrite the input so drivers see updated value if bad input
      AutomatedFunctions.init();  //resets all variables used in methods
     // AutonWeek1.init();  //resets all variables including Step variable 
   
    }
  


    /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    Auton_kickoff.run(0.0, "Move Only");
    
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    DriveBase.init();
    Shooter.init();
    Vision.init();
    Climber.init();
    Intake.init();
    DriveBase.init();
    Conveyor.init();
    
    
    

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    
    getXBoxRequests();
    getFlightStickRequests();
    PIDControllers.configPID(.0033); //rpm = 4800 runs at 4552
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  public void updateSmartDashboard() {
    SmartDashboard.putNumber("Limelight X:", Vision.getTx());
    SmartDashboard.putNumber("Limelight Y:", Vision.getTy());
    SmartDashboard.putNumber("Shooter RPM Current: ", Tools.shooterEncoder.getVelocity());
   

  } //End of upddateSmartDashboard()

  public double boundAutonomousDelay(double inputDelay, String autonPath){

    //Set Variable for timeToExecuteAuton based on AutonPath chosen   
    switch (autonPath){
      case "Shoot":
        timeToExecuteAuton = 5;
      break;
      case "Move Only":
        timeToExecuteAuton = 2;
      break;
    }
    
    //bound if driverstation input is too large
    return inputDelay >= (15 - timeToExecuteAuton)?(15-timeToExecuteAuton):inputDelay;
       
  }//End of boudAutonomousDelay

  public void getFlightStickRequests(){
    //Basic Driving with primary joystick
    double TeleopLeftSideDrive = DriveBase.driveProcessing(-Utilities.deaden(Controls.joystick.getRawAxis(0), .1), Utilities.deaden(Controls.joystick.getRawAxis(1), .1), "left");
    double TeleopRightSideDrive = DriveBase.driveProcessing(Utilities.deaden(Controls.joystick.getRawAxis(0), .1), Utilities.deaden(Controls.joystick.getRawAxis(1), .1), "right");
    
    Motors.motorBL.set(TeleopLeftSideDrive);
    Motors.motorFL.set(TeleopLeftSideDrive);
    Motors.motorFR.set(-TeleopRightSideDrive);
    Motors.motorBR.set(-TeleopRightSideDrive);
  }
  
  public void getXBoxRequests() {

    //ALIGN THE ROBOT HORIZONTALLY
    if(Controls.xbox.getAButton()) {
      Vision.alignX(Vision.horizontalError);

    } else {
      //Do nothing
      DriveBase.stopAllDrivetrainMotors();

    }

    if (Controls.xbox.getBackButton()){
      Climber.retractPin();
   } else if (Controls.xbox.getStartButton()){
      Climber.engagePin();
   }


   if(Controls.xbox.getYButton()) {
     Climber.runClimberManual("Lift", .2);
    

  } else if(Controls.xbox.getXButton()) {
    Climber.runClimberManual("Lower", .3);

  } else {
    Climber.stopClimberMotor();
  }


    //ALIGN THE ROBOT VERTICALLY - Based on the zone that the robot is shooting from
    /*if(Controls.xbox.getYButton()) {
      Vision.alignY(Vision.getYOffset(Vision.getSetPoint(zone),
      Vision.getTy()));

    } else {
      //Do nothing

    } */
      
    //RUN SHOOTER
    if(Controls.xbox.getBButton()) {
      Shooter.run(4900);
    } else {
      Shooter.stopShooterMotor();
    }
  
    //INTAKE LIFT
    if ( 270 == Controls.xbox.getPOV()){
      Intake.lift("Up", 0.2);

    } else if( 90 == Controls.xbox.getPOV()){
      Intake.lift("Down", 0.2);

    } else{
      Intake.stop();

    }

    //SWEEP BALLS IN / SPIT BALLS OUT
    if(Controls.xbox.getBumper(Hand.kLeft) && Controls.xbox.getTriggerAxis(Hand.kRight) > .7) {
      Intake.sweep("In", 0.30);
      Conveyor.runConveyor("In", 0.50);
      Conveyor.runBottom("In", 0.50);

    } else if(Controls.xbox.getBumper(Hand.kLeft)){
      Intake.sweep("In", 0.30);
      Conveyor.stop();
      Conveyor.runBottom("In", 0.50);

    } else if(Controls.xbox.getTriggerAxis(Hand.kRight) > .7) {
      Intake.stopSweep();
      Conveyor.stopBottom();
      Conveyor.runConveyor("In", 0.50);

    } else if(Controls.xbox.getBumper(Hand.kRight)){
      Conveyor.runConveyor("Out", .5);
      Conveyor.runBottom("Out", .5);
      Intake.stop();

    } else {
      Intake.stopSweep();
      Conveyor.stopBottom();
      Conveyor.stop();

    }

  }
}
