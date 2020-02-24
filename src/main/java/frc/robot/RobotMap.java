package frc.robot;


public class RobotMap{
    
    //Drivetrain Motors-All needs to be changed (CAN) all is talonSRX DO NOT CHANGE
    public static final int RIGHT_FRONT_DRIVE = 3;
    public static final int RIGHT_BACK_DRIVE = 4;
    public static final int LEFT_FRONT_DRIVE = 2;
    public static final int LEFT_BACK_DRIVE = 9;
    
    

    //Joysticks DO NOT CHANGE
    public static final int DRIVER_CONTROLLER = 0;
    public static final int OPERATOR_CONTROLLER = 1;


    //Sensors
    public static final int RIGHT_ENC_IN = 2;
    public static final int RIGHT_ENC_OUT = 3;
    public static final int LEFT_ENC_IN = 0;
    public static final int LEFT_ENC_OUT = 1;
	

     // Intake (PWM) VICTOR SP MOTORS DO NOT CHANGE
     public static final int INTAKE = 0; // Port needs to be changed
     public static final int INTAKE_2 = 1;
     public static final int PISTON_INTAKE_IN =4; // Port needs to be changed
     public static final int PISTON_INTAKE_OUT = 5; // Port needs to be changed
     public static final int BEAM_BREAKER = 4; // Port needs to be changed

     //Climber (CAN) victor spx
     public static final int VICTOR_CLIMBER_MOTOR_1 =0; // use to be 4
     public static final int VICTOR_CLIMBER_MOTOR_2 = 11; // use to be 8

    //Indexer (CAN is 1, other PWM fuck you Cassi)

    public static final int INDEXER_ROLLER = 8; //Top roller
    public static final int INDEXER_CONVEYOR =5;//or 5

    //Shooter (CAN) DO NOT CHANGE
    public static final int SHOOTER_MASTER_LEFT = 11; //Talon Sensor left
    public static final int SHOOTER_SLAVE1_RIGHT = 10; //shooter Talon 

    public static final int SHOOTER_SLAVE2_RIGHT = 6; // shooter Victor spx
    public static final int SHOOTER_SLAVE_LEFT = 7; //shooter left Victor spx

    public static final int SHOOTER_BEAM_BREAKER = 5;
}