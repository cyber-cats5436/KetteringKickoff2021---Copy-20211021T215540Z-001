package frc.robot.framework;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Motors {
     
            //drive base motors
            public static CANSparkMax motorFL = new CANSparkMax(1, MotorType.kBrushless);//CAN ID: 1
            public static CANSparkMax motorBL = new CANSparkMax(2, MotorType.kBrushless);//CAN ID: 2
            public static CANSparkMax motorFR = new CANSparkMax(3, MotorType.kBrushless);//CAN ID: 3
            public static CANSparkMax motorBR = new CANSparkMax(4, MotorType.kBrushless);//CAN ID: 4 
        
            //for sucking in balls to the shooter
            public static CANSparkMax intakeConveyor = new CANSparkMax(5, MotorType.kBrushless);//CAN ID: 5
            public static CANSparkMax intakeBottom = new CANSparkMax(6, MotorType.kBrushless);//CAN ID: 6 
            public static CANSparkMax intakeSweeper = new CANSparkMax(7, MotorType.kBrushless);//CAN ID: 5
            public static CANSparkMax intakeMove = new CANSparkMax(8, MotorType.kBrushless);//CAN ID: 8
        
               
        
                //to shoot the balls
            public static CANSparkMax shooter = new CANSparkMax(12, MotorType.kBrushless);//CAN ID: 12
            //public static CANSparkMax shooterLower = new CANSparkMax(, MotorType.kBrushless);//CAN ID: 
        
        
        
                //lift robot for climb
            public static CANSparkMax climberMotor = new CANSparkMax(11, MotorType.kBrushless);//CAN ID: ?
            public static VictorSPX climberSolenoid = new VictorSPX(13);
            
}
//Confirm CAN IDS
