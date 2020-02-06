package frc.robot.subsystems;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;

public class Bull extends SubsystemBase {
  public final Spark collector;
  public final Spark transportation;
  public final Spark shot;

  private Encoder enc_shot;
  private Encoder enc_trans;

  private PIDController shooter_speed_pid;

  private final double KP_SHOOTER_SPEED = 0;
  private final double KI_SHOOTER_SPEED = 0;
  private final double KD_SHOOTER_SPEED = 0;
  private final double SHOOTER_TOLERANCE = 0.03;

  public final double COLLECTOR_SPEED = 0.7;
  public final double TRANS_SPEED = 0.4;

  // private Compressor compressor;
  private DoubleSolenoid solenoid;

  private static Bull instance;

  private Bull() {

    // compressor = new Compressor();

    solenoid = new DoubleSolenoid(Constants.SOLONOID_A, Constants.SOLONOID_B);

    transportation = new Spark(Constants.TRANS_MOTOR);
    shot = new Spark(Constants.SHOT_MOTOR);
    collector = new Spark(Constants.COLLACTER_MOTOR);

    // Eencoder setup
    /*
     * enc_shot = new Encoder(Constants.ENC_SHOT_PORT_A, Constants.ENC_SHOT_PORT_B);
     * enc_shot.setDistancePerPulse(Constants.SHOT_DISTANCE_PER_PULSE);
     * enc_shot.reset();
     * 
     * enc_trans = new Encoder(Constants.ENC_TRANS_PORT_A,
     * Constants.ENC_TRANS_PORT_B);
     * enc_trans.setDistancePerPulse(Constants.TRANS_DISTANCE_PER_PULSE);
     * enc_trans.reset();
     */

    shooter_speed_pid = new PIDController(KP_SHOOTER_SPEED, KI_SHOOTER_SPEED, KD_SHOOTER_SPEED);
    shooter_speed_pid.setTolerance(SHOOTER_TOLERANCE);
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
   * Strat/Stop the compressor.
   * 
   * @param comp Start = true , Stop = false
   */
  // public void set_compressor(final boolean comp) {
  // if(comp) compressor.start();
  // else compressor.stop();
  // }

  /**
   * Set the solenoid to forword postion ("DoubleSolenoid.Value.kForward")
   */
  public void intake_move_forword() {
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

  public void shot_set_speed(double speed) {
    shot.set(speed);
  }

  public void shoot_enable(double speed) {
    shooter_speed_pid.setSetpoint(speed);
  }

  public void shoot() {
    // shot_set_speed(MathUtil.clamp(shooter_speed_pid.calculate(enc_shot.getRate()),
    // 0, 1));
  }

  public void shoot_disable() {
    shooter_speed_pid.reset();
    shot_set_speed(0);
  }

  public void trans_move() {
    transportation.set(-TRANS_SPEED);
  }

  public void trans_move_reverse() {
    transportation.set(TRANS_SPEED);
  }
}
