package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.IntakeForwordCommand;
import frc.robot.commands.IntakeReverseCommand;
import frc.robot.commands.SetMotorCommand;
import frc.robot.commands.AngleVisionPidCommand;
import frc.robot.subsystems.Bull;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Everest;
import frc.robot.commands.ShootCommand;


public class RobotContainer {
  public static Chassis chassis = Chassis.getInstance();
  public static Bull bull = Bull.getInstance();
  public static Everest everest = Everest.getInstance();

  public static Joystick driver_joystick = new Joystick(Constants.DRIVER_STICK);
  public static Joystick arms_joystick = new Joystick(Constants.ARMS_STICK);

  private static JoystickButton collect_button = new JoystickButton(driver_joystick, Constants.COLLECTER_COLLECT_BUTTON);
  private static JoystickButton eject_button = new JoystickButton(driver_joystick, Constants.COLLECTER_EJECT_BUTTON);
  private static JoystickButton intake_forword_button = new JoystickButton(arms_joystick, Constants.INTAKE_FORWORD_BUTTON);
  private static JoystickButton intake_reverse_button = new JoystickButton(arms_joystick, Constants.INTAKE_REVERSE_BUTTON);

  private static JoystickButton everest_pullup_button = new JoystickButton(arms_joystick, Constants.Everest_Pullup_BUTTON);
  private static JoystickButton everest_dis_pullup_button = new JoystickButton(arms_joystick, Constants.Everest_Dis_Pullup_BUTTON);

  private static JoystickButton everest_climb_button = new JoystickButton(arms_joystick, Constants.Everest_Climb_BUTTON);
  private static JoystickButton everest_dis_climb_button = new JoystickButton(arms_joystick, Constants.Everest_Dis_Climb_BUTTON);
  
  private static JoystickButton angle_vision_pid_button = new JoystickButton(arms_joystick, Constants.ANGLE_VISION_PID_BUTTON);
  
  private static JoystickButton shoot_button = new JoystickButton(arms_joystick, Constants.SHOOT_BUTTON);
  private static JoystickButton shoot_dis_button = new JoystickButton(arms_joystick, Constants.SHOOT_BUTTON);

  public RobotContainer() {
    configureButtonBindings();
  }


  private void configureButtonBindings() {
    collect_button.whileHeld(new SetMotorCommand(bull, Constants.COLLACTER_MOTOR, bull.COLLECTOR_SPEED));
    eject_button.whileHeld(new SetMotorCommand(bull, Constants.COLLACTER_MOTOR, -bull.COLLECTOR_SPEED));
    
    intake_forword_button.whileHeld(new IntakeForwordCommand());
    intake_reverse_button.whileHeld(new IntakeReverseCommand());

    //angle_vision_pid_button.whileHeld(new AngleVisionPidCommand());

    everest_pullup_button.whileHeld(new SetMotorCommand(everest, Constants.PULLUP_MOTOR, everest.PULLUP_SPEED));
    everest_dis_pullup_button.whileHeld(new SetMotorCommand(everest, Constants.PULLUP_MOTOR, -everest.PULLUP_SPEED));   

    everest_climb_button.whileHeld(new SetMotorCommand(everest, Constants.CLIMER_MOTOR, everest.CLIMER_SPEED));
    everest_dis_climb_button.whileHeld(new SetMotorCommand(everest, Constants.CLIMER_MOTOR, -everest.CLIMER_SPEED));   

    shoot_button.whileHeld(new ShootCommand(true));
    shoot_dis_button.whileHeld(new ShootCommand(false));
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
