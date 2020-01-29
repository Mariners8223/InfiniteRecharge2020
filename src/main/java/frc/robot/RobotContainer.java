package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.CollectLemonCommand;
import frc.robot.commands.EjectLemonCommand;
import frc.robot.commands.IntakeForwordCommand;
import frc.robot.commands.IntakeReverseCommand;
import frc.robot.commands.AngleVisionPidCommand;
import frc.robot.commands.EverestClimbCommand;
import frc.robot.commands.EverestDisClimbCommand;
import frc.robot.commands.EverestDisPistonCommand;
import frc.robot.commands.EverestDisPullupCommand;
import frc.robot.commands.EverestPistonCommand;
import frc.robot.commands.EverestPullupCommand;
import frc.robot.subsystems.Chassis;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.TransCommand;


public class RobotContainer {
  public static Chassis chassis = Chassis.getInstance();

  public static Joystick driver_joystick = new Joystick(Constants.DRIVER_STICK);
  public static Joystick arms_joystick = new Joystick(Constants.ARMS_STICK);

  private static JoystickButton collect_button = new JoystickButton(arms_joystick, Constants.COLLECTER_COLLECT_BUTTON);
  private static JoystickButton eject_button = new JoystickButton(arms_joystick, Constants.COLLECTER_EJECT_BUTTON);
  private static JoystickButton intake_forword_button = new JoystickButton(arms_joystick, Constants.INTAKE_FORWORD_BUTTON);
  private static JoystickButton intake_reverse_button = new JoystickButton(arms_joystick, Constants.INTAKE_REVERSE_BUTTON);

  private static JoystickButton everest_pullup_button = new JoystickButton(arms_joystick, Constants.Everest_Pullup_BUTTON);
  private static JoystickButton everest_dis_pullup_button = new JoystickButton(arms_joystick, Constants.Everest_Dis_Pullup_BUTTON);

  private static JoystickButton everest_climb_button = new JoystickButton(arms_joystick, Constants.Everest_Climb_BUTTON);
  private static JoystickButton everest_dis_climb_button = new JoystickButton(arms_joystick, Constants.Everest_Dis_Climb_BUTTON);

  private static JoystickButton everest_piston_button = new JoystickButton(arms_joystick, Constants.Everest_Piston_BUTTON);
  private static JoystickButton everest_dis_piston_button = new JoystickButton(arms_joystick, Constants.Everest_Dis_Piston_BUTTON);

  private static JoystickButton angle_vision_pid_button = new JoystickButton(arms_joystick, Constants.ANGLE_VISION_PID_BUTTON);

  private static JoystickButton shoot_button = new JoystickButton(arms_joystick, 7);
  private static JoystickButton trans_button = new JoystickButton(arms_joystick, 8);

  public RobotContainer() {
    configureButtonBindings();
  }


  private void configureButtonBindings() {
    collect_button.whileHeld(new CollectLemonCommand());
    eject_button.whileHeld(new EjectLemonCommand());
    
    intake_forword_button.whileHeld(new IntakeForwordCommand());
    intake_reverse_button.whileHeld(new IntakeReverseCommand());

    angle_vision_pid_button.whileHeld(new AngleVisionPidCommand());

    everest_pullup_button.whileHeld(new EverestPullupCommand());
    everest_dis_pullup_button.whileHeld(new EverestDisPullupCommand());   

    everest_climb_button.whileHeld(new EverestClimbCommand());
    everest_dis_climb_button.whileHeld(new EverestDisClimbCommand());   
    
    everest_dis_piston_button.whileHeld(new EverestDisPistonCommand());   
    //everest_piston_button.whileHeld(new EverestPistonCommand());
    shoot_button.whileHeld(new ShootCommand());
    trans_button.whileHeld(new TransCommand());

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
