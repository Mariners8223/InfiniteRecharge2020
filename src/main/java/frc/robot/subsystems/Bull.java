package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;

public class Bull extends SubsystemBase {
  private Spark collector;
  private Spark shooter_motor;
  private Encoder shooter_encoder;

  private PIDController shooter_speed_pid;

  private final double KP_SHOOTER_SPEED = 0;
  private final double KI_SHOOTER_SPEED = 0;
  private final double KD_SHOOTER_SPEED = 0;
  private final double SHOOTER_TOLERANCE = 0.03;
  
  private final double COLLECTOR_SPEED = 0.5;

  private Compressor compressor;
  private DoubleSolenoid solenoid;

  private static Bull instance;

  private Bull() {
    collector = new Spark(Constants.COLLACTER_MOTOR);

    shooter_motor = new Spark(Constants.SHOOTER_MOTOR);
    shooter_encoder = new Encoder(Constants.SHOOTER_ENCODER_A, Constants.SHOOTER_ENCODER_B);
    shooter_encoder.setDistancePerPulse(Constants.DISTANCE_PER_PULSE);

    compressor = new Compressor();

    solenoid = new DoubleSolenoid(Constants.SOLONOID_A, Constants.SOLONOID_B);
    
    shooter_speed_pid = new PIDController(KP_SHOOTER_SPEED, KI_SHOOTER_SPEED, KD_SHOOTER_SPEED);
    shooter_speed_pid.setTolerance(SHOOTER_TOLERANCE);
  }

  public static Bull getInstance() {
    if (instance == null)
      instance = new Bull();
    return instance;
  }

  public void collect() {
    collector.set(COLLECTOR_SPEED);
  }

  public void collector_stop() {
    collector.set(0);
  }

  public void eject() {
    collector.set(-COLLECTOR_SPEED);
  }

  public void set_compressor(final boolean comp) {
    if(comp) compressor.start();
    else compressor.stop();
  }

  public void intake_move_forword(){
    solenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void intake_stop(){
    solenoid.set(DoubleSolenoid.Value.kOff);
  }

  public void intake_move_reverse(){
    solenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void shoot_enable(double speed){
    shooter_speed_pid.setSetpoint(speed);
  }

  public void shoot(){
    shooter_motor.set(MathUtil.clamp(shooter_speed_pid.calculate(shooter_encoder.getRate()), 0, 1));
  }

  public void shoot_disable(){
    shooter_speed_pid.reset();
    shooter_motor.set(0);
  }
}
