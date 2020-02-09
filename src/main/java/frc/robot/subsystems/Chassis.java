package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

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
  private Spark front_left;
  private Spark back_left;
  private SpeedControllerGroup left;

  private AHRS gyro;
  private PIDController gyro_pid;
  private final double KP_GYRO = 7;
  private final double KI_GYRO = 0.5;
  private final double KD_GYRO = 0.1;
  private final double GYRO_TOLERANCE = 2;

  private Spark front_right;
  private Spark back_right;
  private SpeedControllerGroup right;

  private DifferentialDrive drive;

  //private Encoder enc_left;
  //private Encoder enc_right;

  private PIDController angle_vision_pid;
  private final double KP_ANGLE_VISION = 7;
  private final double KI_ANGLE_VISION = 0.1;
  private final double KD_ANGLE_VISION = 0.1;
  private final double PID_MAX_SPEED = 0.75;
  private final double ANGLE_VISION_TOLERANCE = 0.05;

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


    
    gyro = new AHRS();
    gyro.isCalibrating();
    gyro_pid = new PIDController(KP_GYRO, KI_GYRO, KD_GYRO);
    gyro_pid.setTolerance(GYRO_TOLERANCE);
    gyro_pid.enableContinuousInput(-180, 180);

    SendableRegistry.setName(gyro_pid, "GyroPID");
    SmartDashboard.putNumber("gyro-P", 7);
    SmartDashboard.putNumber("gyro-I", 0);
    SmartDashboard.putNumber("gyro-D", 0);
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
    SmartDashboard.putNumber("GyroXq", gyro.getQuaternionX());
    SmartDashboard.putNumber("GyroZq", gyro.getQuaternionZ());
    SmartDashboard.putNumber("GyroYq", gyro.getQuaternionY());
    SmartDashboard.putNumber("Gyrox", gyro.getRawGyroX());
    SmartDashboard.putNumber("Gyroz", gyro.getRawGyroY());
    SmartDashboard.putNumber("Gyroy", gyro.getRawGyroZ());
    SmartDashboard.putNumber("Yaw", gyro.getYaw());
    SmartDashboard.putNumber("Roll", gyro.getRoll());
    SmartDashboard.putNumber("Pitch", gyro.getPitch());
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
}
