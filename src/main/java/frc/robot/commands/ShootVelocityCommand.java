package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Bull;
// import edu.wpi.first.wpilibj.Timer;

public class ShootVelocityCommand extends CommandBase {
  Bull bull = Bull.getInstance();
  double speed;
  // private double target_last_time = 0;
  // private double wait = 0.3;

  public ShootVelocityCommand(double speed) {
    this.speed = -speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    bull.shoot_enable(speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    bull.shoot();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    bull.shoot_disable();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
    /*boolean stop = bull.shoot_stop();
    if (stop) {
      target_last_time = Timer.getFPGATimestamp();
    }
    return Timer.getFPGATimestamp() - target_last_time > wait && stop;
  */
  }
}
