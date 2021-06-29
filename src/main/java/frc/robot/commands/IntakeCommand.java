// package frc.robot.commands;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.subsystems.Bull;

// public class IntakeCommand extends CommandBase {
//   Bull bull = Bull.getInstance();

//   public IntakeCommand() {
//     addRequirements(bull);
//   }

//   @Override
//   public void initialize() {
//     System.out.println("intake_toggle");
//     bull.intake_move_forword();
//   }


//   @Override
//   public void end(boolean interrupted) {
//     System.out.println("intake_toggle///////////////////////////");
//     bull.intake_move_reverse();
//     bull.intake_toggle = !bull.intake_toggle;
//   }

//   @Override
//   public boolean isFinished() {
//     return false;
//   }
// }
