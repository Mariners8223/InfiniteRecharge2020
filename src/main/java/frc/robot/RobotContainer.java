package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.CollectLemonCommand;
import frc.robot.commands.EjectLemonCommand;
import frc.robot.commands.IntakeForwordCommand;
import frc.robot.commands.IntakeReverseCommand;
import frc.robot.subsystems.Chassis;


public class RobotContainer {
  public static Chassis chassis = Chassis.getInstance();
  public static Joystick joystick = new Joystick(Constants.DRIVER_STICK);
  private static JoystickButton collectButton = new JoystickButton(joystick, Constants.COLLECTER_COLLECT_BUTTON);
  private static JoystickButton ejectButton = new JoystickButton(joystick, Constants.COLLECTER_EJECT_BUTTON);
  private static JoystickButton intakeForwordButton = new JoystickButton(joystick, Constants.INTAKE_FORWORD_BUTTON);
  private static JoystickButton intakeReverseButton = new JoystickButton(joystick, Constants.INTAKE_REVERSE_BUTTON);

  public RobotContainer() {
    configureButtonBindings();
  }


  private void configureButtonBindings() {
    collectButton.whileHeld(new CollectLemonCommand());
    ejectButton.whileHeld(new EjectLemonCommand());
    intakeForwordButton.whileHeld(new IntakeForwordCommand());
    intakeReverseButton.whileHeld(new IntakeReverseCommand());
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  /*public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }*/
}
