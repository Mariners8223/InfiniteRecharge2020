package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {
  private Spark front_left;
  private Spark back_left;
  private SpeedControllerGroup left;

  private Spark front_right;
  private Spark back_right;
  private SpeedControllerGroup right;

  private DifferentialDrive drive;

  private Encoder enc_left;
  private Encoder enc_right;

  private final PIDController angle_vision_pid;
  private final double KP_ANGLE_VISION = 0;
  private final double KI_ANGLE_VISION = 0;
  private final double KD_ANGLE_VISION = 0;
  private final double PID_MAX_SPEED = 0.5;
  private final double ANGLE_VISION_TOLERANC = 0.05;

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
    enc_left = new Encoder(Constants.ENC_LEFT_PORT_A, Constants.ENC_LEFT_PORT_B);
    enc_left.setDistancePerPulse(Constants.LEFT_DISTANCE_PER_PULSE);
    enc_left.reset();
    
    enc_right = new Encoder(Constants.ENC_RIGHT_PORT_A, Constants.ENC_RIGHT_PORT_B);
    enc_right.setDistancePerPulse(Constants.RIGHT_DISTANCE_PER_PULSE);
    enc_right.reset();

    angle_vision_pid = new PIDController(KP_ANGLE_VISION, KI_ANGLE_VISION, KD_ANGLE_VISION);
    angle_vision_pid.setTolerance(ANGLE_VISION_TOLERANC);
    angle_vision_pid.setSetpoint(0);
  }

  public static Chassis getInstance() {
    if (instance == null)
      instance = new Chassis();
    return instance;
  }

  public void set_speed(double l, double r){
    drive.tankDrive(l, r);
  }

  public double angle_vision_pid_output(double angle) {
    return angle_vision_pid.calculate(angle, 0.0);
  }

  public void pid_vision(double angle) {
    double speed = angle_vision_pid_output(angle);
    speed = MathUtil.clamp(speed, -1, 1);
    set_speed(speed, -speed);
  }

  public boolean stop_angle_vision_pid() {
    return angle_vision_pid.atSetpoint();
  }

  public void pid_reset() {
    angle_vision_pid.reset();
    set_speed(0, 0);
  }
}
