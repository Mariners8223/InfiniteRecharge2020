package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {
  private Spark m_frontLeft;
  private Spark m_backLeft;
  private SpeedControllerGroup m_left;

  private Spark m_frontRight;
  private Spark m_backRight;
  private SpeedControllerGroup m_right;

  private DifferentialDrive m_drive;

  private final PIDController angle_vision_pid;
  private final double KP_ANGLE_VISION = 0;
  private final double KI_ANGLE_VISION = 0;
  private final double KD_ANGLE_VISION = 0;
  private final double PID_MAX_SPEED = 0.5;
  private final double ANGLE_VISION_TOLERANC = 0.05;

  private static Chassis instance;

  private Chassis(){
    m_frontLeft = new Spark(Constants.LEFT_FRONT_MOTOR);
    m_backLeft = new Spark(Constants.LEFT_BACK_MOTOR);
    m_left = new SpeedControllerGroup(m_frontLeft, m_backLeft);

    m_frontRight = new Spark(Constants.RIGHT_FRONT_MOTOR);
    m_backRight = new Spark(Constants.RIGHT_BACK_MOTOR);
    m_right = new SpeedControllerGroup(m_frontRight, m_backRight);

    m_drive = new DifferentialDrive(m_left, m_right);

    angle_vision_pid = new PIDController(KP_ANGLE_VISION, KI_ANGLE_VISION, KD_ANGLE_VISION);
    angle_vision_pid.enableContinuousInput(-1, 1);
    angle_vision_pid.setTolerance(ANGLE_VISION_TOLERANC);
  }

  public static Chassis getInstance() {
    if(instance == null) instance = new Chassis();
    return instance;
  }

  public void setSpeed(double l, double r){
    m_drive.tankDrive(l, r);
  }

  public double angle_vision_pid_output( double angle){
    return angle_vision_pid.calculate(angle, 0.0);
  }

  public void pid_vision( double angle ) {
    double speed = angle_vision_pid_output(angle);
    setSpeed(speed , -speed);
 }

  public boolean stop_angle_vision_pid() {
    return angle_vision_pid.atSetpoint();
  }

  public void pid_reset(){
    angle_vision_pid.reset();
    setSpeed(0, 0);
  }
}
