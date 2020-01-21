package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Bull extends SubsystemBase {
  private final Spark collector;
  private final double COLLECTOR_SPEED = 0.5;

  private final Compressor compressor;
  private final DoubleSolenoid solenoid;

  private static Bull instance;

  private Bull() {
    collector = new Spark(Constants.COLLACTER_MOTOR);
    compressor = new Compressor();
    solenoid = new DoubleSolenoid(Constants.SOLONOID_A, Constants.SOLONOID_B);
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
}
