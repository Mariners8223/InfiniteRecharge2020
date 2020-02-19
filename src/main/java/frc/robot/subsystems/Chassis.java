package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {
  public Spark front_left;
  public Spark back_left;
  private SpeedControllerGroup left;

  private AHRS gyro;
  private PIDController gyro_pid;
  private final double KP_GYRO = 0.005;
  private final double KI_GYRO = 0;
  private final double KD_GYRO = 0;
  private final double GYRO_TOLERANCE = 2;

  public Spark front_right;
  public Spark back_right;
  private SpeedControllerGroup right;

  private DifferentialDrive drive;

  //private Encoder enc_left;
  //private Encoder enc_right;

  private PIDController angle_vision_pid;
  private final double KP_ANGLE_VISION = 0.6;
  private final double KI_ANGLE_VISION = 0.0;
  private final double KD_ANGLE_VISION = 0;
  private final double PID_MAX_SPEED = 0.75;
  private final double ANGLE_VISION_TOLERANCE = 0.1;
  
  // drive stight deacceleration
  private PIDController deacceleration_drive_pid;
  private final double KP_DEACCELERATION = 0.4;
  private final double KI_DEACCELERATION = 0.001;
  private final double KD_DEACCELERATION = 0;
  private final double PID_DEACCELERATION_MAX_SPEED = 0.6;

  private static Chassis instance;

  private Chassis() {
    front_left = new Spark(Constants.LEFT_FRONT_MOTOR);
    back_left = new Spark(Constants.LEFT_BACK_MOTOR);
    left = new SpeedControllerGroup(front_left, back_left);

    front_right = new Spark(Constants.RIGHT_FRONT_MOTOR);
    back_right = new Spark(Constants.RIGHT_BACK_MOTOR);
    right = new SpeedControllerGroup(front_right, back_right);

    drive = new DifferentialDrive(left, right);

    // Encoder setup
    /*enc_left = new Encoder(Constants.ENC_LEFT_PORT_A, Constants.ENC_LEFT_PORT_B);
    enc_left.setDistancePerPulse(Constants.LEFT_DISTANCE_PER_PULSE);
    enc_left.reset();
    
    enc_right = new Encoder(Constants.ENC_RIGHT_PORT_A, Constants.ENC_RIGHT_PORT_B);
    enc_right.setDistancePerPulse(Constants.RIGHT_DISTANCE_PER_PULSE);
    enc_right.reset();*/

    angle_vision_pid = new PIDController(KP_ANGLE_VISION, KI_ANGLE_VISION, KD_ANGLE_VISION);
    angle_vision_pid.setTolerance(ANGLE_VISION_TOLERANCE);
    angle_vision_pid.setSetpoint(0);

    // NavX setup
    try {
      gyro = new AHRS(); 
    } catch (RuntimeException ex ) {
        DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
    }
    gyro.isCalibrating();
    gyro_pid = new PIDController(KP_GYRO, KI_GYRO, KD_GYRO);
    gyro_pid.setTolerance(GYRO_TOLERANCE);
    gyro_pid.enableContinuousInput(-180, 180);

    SendableRegistry.setName(gyro_pid, "GyroPID");

    deacceleration_drive_pid = new PIDController(KP_DEACCELERATION, KI_DEACCELERATION, KD_DEACCELERATION);
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
   * @param left - left motors speed
   * @param right  - right motors speed
   */
  public void set_speed(double left, double right){
    drive.tankDrive(left, right);
  }

  public double angle_vision_pid_output(double angle) {
    return angle_vision_pid.calculate(angle, 0.0);
  }

  public void pid_vision(double angle) {
    double speed = angle_vision_pid_output(angle);
    speed = MathUtil.clamp(speed, -PID_MAX_SPEED, PID_MAX_SPEED);
    set_speed(speed, -(speed));
    System.out.println("spd: " + speed);
    System.out.println("ang: " + angle);
  }

  public boolean stop_angle_vision_pid() {
    return angle_vision_pid.atSetpoint();
  }

  public void pid_reset() {
    angle_vision_pid.reset();
    set_speed(0, 0);
  }

  public void Log(){
    SmartDashboard.putBoolean("Gyro", gyro.isCalibrating() || SmartDashboard.getBoolean("Gyro", false));
    SmartDashboard.putBoolean(  "IMU_Connected",        gyro.isConnected());
    SmartDashboard.putBoolean(  "IMU_IsCalibrating",    gyro.isCalibrating());
    SmartDashboard.putNumber(   "IMU_Yaw",              gyro.getYaw());
    SmartDashboard.putNumber(   "IMU_Pitch",            gyro.getPitch());
    SmartDashboard.putNumber(   "IMU_Roll",             gyro.getRoll());
    //gyro
    
  }

  public double get_angle(){
    Log();
    return gyro.getYaw();
  }

  public void reset_angle(){
    gyro.reset();
  }
  
  public void pid_gyro_enable(double degrees){
    gyro_pid.setSetpoint(degrees);
  }

  public void pid_gyro_set(double p, double i, double d){
    gyro_pid.setPID(p,i,d);
  }

  public void pid_gyro_execute(){
    double speed = gyro_pid.calculate(get_angle());
    speed = MathUtil.clamp(speed, -PID_MAX_SPEED, PID_MAX_SPEED);
    set_speed(speed, -(speed));
  }

  public boolean stop_gyro() {
    return gyro_pid.atSetpoint();
  }

  /**
   * distance travled by th robot
   * @return mean of the chassis encoders
   */
  public double get_distance(){
    return 0.0;
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
  


}