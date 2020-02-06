package frc.robot;

public final class Constants {
    // PWM
    public static final int LEFT_FRONT_MOTOR = 4;
    public static final int RIGHT_FRONT_MOTOR = 2;
    public static final int LEFT_BACK_MOTOR = 3;
    public static final int RIGHT_BACK_MOTOR = 1;
    public static final int COLLACTER_MOTOR = 5;
    public static final int SHOT_MOTOR = 8;
    public static final int TRANS_MOTOR = 7;
    public static final int CLIMER_MOTOR = 0;
    public static final int PULLUP_MOTOR = 6;  // TODO
    public static final int PULLUP1_MOTOR = 7;
    public static final int PULLUP2_MOTOR = 8;
    public static final int SPINNER_MOTOR = 9;

    // JoySticks And Bottons
    public static final int DRIVER_STICK = 0;
    public static final int ARMS_STICK = 1;

    // Driver Joystick
    public static final int ANGLE_VISION_PID_BUTTON = 5; // TODO
    public static final int DRIVER_LEFT_AXIS = 1;
    public static final int DRIVER_RIGHT_AXIS = 5;
    public static final int COLLECTER_COLLECT_BUTTON = 5;
    public static final int COLLECTER_EJECT_BUTTON = 6;


    // Arms Joystick
    public static final int SHOOT_BUTTON = 4;
    public static final int SHOOT_DIS_BUTTON = 2;
    public static final int TRANS_BUTTON = 3;
    public static final int TRANS_DIS_BUTTON = 1;

    public static final int INTAKE_BUTTON = 6;
    public static final int ROLATE_BUTTON = 5;
    
    //POV
    public static final int Everest_Climb_BUTTON = 2 * 45;
    public static final int Everest_Dis_Climb_BUTTON = 6 * 45;
    public static final int Everest_Pullup_BUTTON = 0 * 45;
    public static final int Everest_Dis_Pullup_BUTTON = 4 * 45;

    // PCM
    public static final int SOLONOID_A = 3;
    public static final int SOLONOID_B = 4;
    public static final int ROLATE_SOLONOID_A = 5; 
    public static final int ROLATE_SOLONOID_B = 6; 

    // Encoder Staff
    public static final double SHOT_DISTANCE_PER_PULSE = 0.000396;//0.000505433;
    public static final double TRANS_DISTANCE_PER_PULSE = 0.000396; // TODO
    public static final double RIGHT_DISTANCE_PER_PULSE = 0.000396; // TODO
    public static final double LEFT_DISTANCE_PER_PULSE = 0.000396; // TODO

    // DIO
    public static final int ENC_SHOT_PORT_A = 3;
    public static final int ENC_SHOT_PORT_B = 2;

    public static final int ENC_TRANS_PORT_A = 4; // TODO
    public static final int ENC_TRANS_PORT_B = 5; // TODO

    public static final int ENC_LEFT_PORT_A = 4; // TODO
    public static final int ENC_LEFT_PORT_B = 5; // TODO

    public static final int ENC_RIGHT_PORT_A = 4; // TODO
    public static final int ENC_RIGHT_PORT_B = 5; // TODO
    
}
