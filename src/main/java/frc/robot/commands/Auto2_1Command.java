package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Bull;
public class Auto2_1Command extends ParallelCommandGroup {
  public Auto2_1Command() {
    super(new Auto2_2Command(), new IntakeToggleCommand(), new SetMotorCanCommand(Bull.getInstance().shoot, -1));
  }
}
