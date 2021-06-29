package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Bull;
public class Auto1_2Command extends ParallelCommandGroup {
  public Auto1_2Command() {
    super(new SetMotorCanCommand(Bull.getInstance().shoot, 0.75), new SetMotorCanCommand(Bull.getInstance().plate, Bull.getInstance().TRANS_SPEED,Bull.getInstance().omni, Bull.getInstance().OMNI_SPEED));
  }
}
