package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.Faults;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;

public class Bull extends SubsystemBase {
  // Motors Setup
  public final VictorSPX collector;
  public final VictorSPX plate;
  public final TalonFX shoot;
  public final VictorSPX omni;

  // Encoder Setup
  private Encoder enc_shot;

  // Shooter PID Setup
  public PIDController shooter_speed_pid;
  private final double KP_SHOOTER_SPEED = 0.01;
  private final double KI_SHOOTER_SPEED = 0.0; // 7
  private final double KD_SHOOTER_SPEED = 0.00; // 1
  private final double SHOOTER_TOLERANCE = 50;


  // Speed Contance
  public final double COLLECTOR_SPEED = -0.8;
  public final double TRANS_SPEED = 0.3;
  public final double SHOOT_SPEED = 1;
  public final double OMNI_SPEED = 0.25;

  // Command Setup
  public boolean shoot_trigger = false;
  public boolean intake_toggle = false;

  // Solenoid Setup
  private DoubleSolenoid solenoid;

  // Singletone Instance
  private static Bull instance;

  private Bull() {
    // Motors and Encoders
    solenoid = new DoubleSolenoid(Constants.SOLONOID_A, Constants.SOLONOID_B);
    plate = new VictorSPX(Constants.TRANS_MOTOR);
    shoot = new TalonFX(Constants.SHOT_MOTOR);
    omni = new VictorSPX(Constants.OMNI_MOTOR);
    collector = new VictorSPX(Constants.COLLACTER_MOTOR);

    // Eencoder setup
     enc_shot = new Encoder(Constants.ENC_SHOT_PORT_A, Constants.ENC_SHOT_PORT_B);
     enc_shot.setDistancePerPulse(Constants.SHOT_DISTANCE_PER_PULSE);
     enc_shot.reset();

     // PID Encoder
    shooter_speed_pid = new PIDController(KP_SHOOTER_SPEED, KI_SHOOTER_SPEED, KD_SHOOTER_SPEED);
    shooter_speed_pid.setTolerance(SHOOTER_TOLERANCE);
    shooter_speed_pid.reset();
  }

  /**
   * Singleton function, returns the ONLY instance of the class.
   * 
   * @return instance of the class.
   */
  public static Bull getInstance() {
    if (instance == null)
      instance = new Bull();
    return instance;
  }

  /**
   * Set the solenoid to forword postion ("DoubleSolenoid.Value.kForward")
   */
  public void intake_toggle() {
    solenoid.set(DoubleSolenoid.Value.kForward);
  }

  /**
   * @return true if the solenoid value is forword
   */
  public boolean get_intake_state() {
    return solenoid.get() == DoubleSolenoid.Value.kForward;
  }

  /**
   * Stop the solenoid ("DoubleSolenoid.Value.kOff")
   */
  public void intake_stop() {
    solenoid.set(DoubleSolenoid.Value.kOff);
  }

  /**
   * Set the solenoid to reverse postion ("DoubleSolenoid.Value.kReverse")
   */
  public void intake_move_reverse() {
    solenoid.set(DoubleSolenoid.Value.kReverse);
  }

  /**
   * Set the solenoid to reverse postion ("DoubleSolenoid.Value.kReverse")
   */
  public void intake_move_forword() {
    solenoid.set(DoubleSolenoid.Value.kForward);
  }


  /**
   * Set speed of the shooter motors
   * @param speed speed of the motor
   */
  public void shot_set_speed(double speed) {
    shoot.set(ControlMode.PercentOutput, speed);
  }

  /**
   * Setpoint for the shooter PID. calls at the sart of the command
   * @param speed whant speed of the motor measered by the encoder.
   */
  public void shoot_enable(double speed) {
    shooter_speed_pid.setSetpoint(speed);
  }

  public boolean shoot_on_t(){
    return shooter_speed_pid.atSetpoint();
  }

  public double enc_v_shooter(){
    // System.out.println("speed: " + shoot.getSelectedSensorVelocity());
    double enc_speed = shoot.getSelectedSensorVelocity(); // speed in unints per 100 ms
    enc_speed = enc_speed / Constants.FALCON_UNUTES_PER_ROTATION; // rotaion per 100 ms
    enc_speed = enc_speed * 600; // roation per min
    SmartDashboard.putNumber("Encoder Speed", enc_speed);
    return enc_speed;
  }

  /**
   * Set the motor speed by the PID
   */
  public void shoot() {
    double s = 2 * sigmoid(shooter_speed_pid.calculate(Math.abs(enc_v_shooter()))) - 1;
    shot_set_speed(s);
    //shot_set_speed(1);

    SmartDashboard.putNumber("motorspeed", s);
    // SmartDashboard.putNumber("velocity", enc_shot.getRate());
  }

  private double sigmoid(double a){
    return 1 / (1+Math.exp(-a));
  }

  /**
   * Stops the shoot PID
   */
  public void shoot_disable() {
    shooter_speed_pid.reset();
    shot_set_speed(0);
    plate.set(ControlMode.PercentOutput, 0);
  }

  public void trans_move() {
    plate.set(ControlMode.PercentOutput ,-TRANS_SPEED);
  }

  public void trans_move_reverse() {
    plate.set(ControlMode.PercentOutput, TRANS_SPEED);
  }

  public void trans_stop() {
    plate.set(ControlMode.PercentOutput, 0);
  }

  /**
   * Is the shooting PID at the setpoint
   * @return Is on setpoint
   */
  public boolean is_shoot_stop() {
  	return shooter_speed_pid.atSetpoint();
  }

  public double shoot_speed(){
    return enc_shot.getRate();
  }

  public void collector_set_speed(double speed){
    collector.set(ControlMode.PercentOutput, speed);
  }
}
