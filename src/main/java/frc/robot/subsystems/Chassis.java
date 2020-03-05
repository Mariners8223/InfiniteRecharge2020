package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {

  // Chassis Motors
  private VictorSPX front_right;
  private VictorSPX back_right;
  private VictorSPX front_left;
  private VictorSPX back_left;

  // Chassis Encoders
  public Encoder enc_left;
  public Encoder enc_right;

  // NavX Gyro
  private AHRS gyro;

  // Turn by angle PID
  private PIDController gyro_pid;
  private final double KP_GYRO = 0.006;
  private final double KI_GYRO = 0.0012;
  private final double KD_GYRO = 0;
  private final double GYRO_TOLERANCE = 0;

  // Turn by angle PID
  private PIDController gyro_turn_pid;
  private final double KP_TURN_GYRO = 0.005;
  private final double KI_TURN_GYRO = 0.0003;
  private final double KD_TURN_GYRO = 0;
  private final double GYRO_TURN_TOLERANCE = 2;

  // Turn by Vision PID
  private PIDController angle_vision_pid;
  private final double KP_ANGLE_VISION = 0.45;
  private final double KI_ANGLE_VISION = 0.1;
  private final double KD_ANGLE_VISION = 0;
  public final double PID_MAX_SPEED = 0.75;
  private final double ANGLE_VISION_TOLERANCE = 0.05;

  // Deacceleration Drive PID
  private PIDController deacceleration_drive_pid;
  private final double KP_DEACCELERATION = 0.42;
  private final double KI_DEACCELERATION = 0.04;
  private final double KD_DEACCELERATION = 0;
  public final double DEACCELERATION_TOLERANCE = 0.05;
  private final double PID_DEACCELERATION_MAX_SPEED = 0.6;

  // Singletone Instance
  private static Chassis instance;

  private Chassis() {
    // Motors Steup
    front_left = new VictorSPX(Constants.LEFT_FRONT_MOTOR);
    back_left = new VictorSPX(Constants.LEFT_BACK_MOTOR);
    
    front_right = new VictorSPX(Constants.RIGHT_FRONT_MOTOR);
    back_right = new VictorSPX(Constants.RIGHT_BACK_MOTOR);

    front_left.setNeutralMode(NeutralMode.Brake);
    back_left.setNeutralMode(NeutralMode.Brake);
    front_right.setNeutralMode(NeutralMode.Brake);
    back_left.setNeutralMode(NeutralMode.Brake);

    // Encoders setup
    enc_left = new Encoder(Constants.ENC_LEFT_PORT_A, Constants.ENC_LEFT_PORT_B);
    enc_left.setDistancePerPulse(Constants.LEFT_DISTANCE_PER_PULSE);
    enc_left.reset();
    
    enc_right = new Encoder(Constants.ENC_RIGHT_PORT_A, Constants.ENC_RIGHT_PORT_B);
    enc_right.setDistancePerPulse(Constants.RIGHT_DISTANCE_PER_PULSE);
    enc_right.reset();

    // Turn by Vision PID
    angle_vision_pid = new PIDController(KP_ANGLE_VISION, KI_ANGLE_VISION, KD_ANGLE_VISION);
    angle_vision_pid.setTolerance(ANGLE_VISION_TOLERANCE);
    angle_vision_pid.setSetpoint(0);

    // NavX Setup and PID
    try {
      gyro = new AHRS(); 
    } catch (RuntimeException ex ) {
        DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
    }
    gyro.isCalibrating();
    gyro_pid = new PIDController(KP_GYRO, KI_GYRO, KD_GYRO);
    gyro_pid.setTolerance(GYRO_TOLERANCE);
    gyro_pid.enableContinuousInput(-180, 180);
    gyro_pid.setSetpoint(0);
    SendableRegistry.setName(gyro_pid, "GyroPID");

    gyro_turn_pid = new PIDController(KP_TURN_GYRO, KI_TURN_GYRO, KD_TURN_GYRO);
    gyro_turn_pid.setTolerance(GYRO_TURN_TOLERANCE);
    gyro_turn_pid.enableContinuousInput(-180, 180);

    // Deacceleration Drive PID
    deacceleration_drive_pid = new PIDController(KP_DEACCELERATION, KI_DEACCELERATION, KD_DEACCELERATION);
    deacceleration_drive_pid.setTolerance(DEACCELERATION_TOLERANCE);
  }

  /**
   * Singleton function, returns the ONLY instance of the class.
   * @return instance of the class.
   */
  public static Chassis getInstance() {
    if (instance == null)
      instance = new Chassis();
    return instance;
  }

  /**
   * set speed for the drivetrain
   * @param Speed - Speed of the motors
   * @param Steering  - Stiring of the motors
   */
  public void set_speed(double speed, double steering){
    front_left.set(ControlMode.PercentOutput, speed + steering);
    back_left.set(ControlMode.Follower, front_left.getDeviceID());

    front_right.set(ControlMode.PercentOutput, -speed + steering);
    back_right.set(ControlMode.Follower, front_right.getDeviceID());
  }

  /**
   * Turn by angle PID caculate
   * @param angle current angle - error
   * @return PID output
   */
  public double angle_vision_pid_output(double angle) {
    return angle_vision_pid.calculate(angle, 0.0);
  }

  /**
   * Turn the robot by a given value (from the razbery pi)
   * @param angle given angle
   */
  public void pid_vision(double angle) {
    double speed = angle_vision_pid_output(angle);
    speed = MathUtil.clamp(speed, -0.5, 0.5);
    set_speed(0, -speed);
    System.out.println(angle_vision_pid.getPositionError());
  }

  /**
   * Is the vision PID at setpoint
   * @return Is at setpoint
   */
  public boolean is_stop_angle_vision_pid() {
    return angle_vision_pid.atSetpoint();
  }

  /**
   * reset the Vision PID
   */
  public void pid_reset() {
    angle_vision_pid.reset();
    set_speed(0, 0);
  }

  /**
   * reset the Turn to angle PID
   */
  public void pid_angle_reset(){
    gyro_pid.reset();
  }

  public void pid_turn_reset(){
    gyro_turn_pid.reset();
  }

  public void pid_acc_reset(){
    deacceleration_drive_pid.reset();
  }

  public void Log(){
    
    
    SmartDashboard.putBoolean("Gyro", gyro.isCalibrating() || SmartDashboard.getBoolean("Gyro", false));
    SmartDashboard.putBoolean(  "IMU_Connected",        gyro.isConnected());
    SmartDashboard.putBoolean(  "IMU_IsCalibrating",    gyro.isCalibrating());
    SmartDashboard.putNumber(   "IMU_Yaw",              gyro.getYaw());
    SmartDashboard.putNumber(   "IMU_Pitch",            gyro.getPitch());
    SmartDashboard.putNumber(   "IMU_Roll",             gyro.getRoll());
    
  }

  public double get_angle(){
    Log();
    return gyro.getYaw();
  }

  public void reset_angle(){
    gyro.reset();
  }
  
  public void pid_gyro_enable(double degrees){
    gyro_turn_pid.setSetpoint(degrees);
  }

  public void pid_gyro_set(double p, double i, double d){
    gyro_pid.setPID(p,i,d);
  }

  public double gyro_calculate(){
    return gyro_pid.calculate(get_angle());
  }

  public double gyro_turn_calculate(){
    return gyro_turn_pid.calculate(get_angle());
  }

  public boolean stop_gyro() {
    return gyro_pid.atSetpoint();
  }

  /**
   * distance travled by th robot
   * @return mean of the chassis encoders
   */
  public double get_distance(){
    return (enc_left.getDistance() + enc_right.getDistance())/2;
  }
  
  /**
   * start the deacceleration PID
   */
  public void deacceleration_enable(double setpoint){
    deacceleration_drive_pid.setSetpoint(setpoint);
  }

  /**
   * calculate the PID output for the deacceleration PID
   * @param stop_val input - time/distance
   * @return speed for motors
   */
  public double get_deacceleration(double stop_val){
    double speed = deacceleration_drive_pid.calculate(stop_val);
    return MathUtil.clamp(speed, -PID_DEACCELERATION_MAX_SPEED, PID_DEACCELERATION_MAX_SPEED);
  }

  public void enc_reset() {
    enc_left.reset();
    enc_right.reset();
  }

  public boolean deacc_stop(){
    return deacceleration_drive_pid.atSetpoint();
  }
  
}
