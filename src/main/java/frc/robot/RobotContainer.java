package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.PullUpCommend;
import frc.robot.commands.RolateCommand;
import frc.robot.commands.SetMotorCanCommand;
import frc.robot.commands.SetMotorCommand;
import frc.robot.commands.ShootVelocityCommand;
import frc.robot.commands.ShotWithClachCommand;
import frc.robot.commands.TurnToAngle;
import frc.robot.commands.AngleVisionPidCommand;
import frc.robot.commands.AutoCommand;
import frc.robot.commands.ChassisCommand;
import frc.robot.commands.DriveStraight;
import frc.robot.subsystems.Bull;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Everest;
import frc.robot.subsystems.NetworktablesSubSystem;

public class RobotContainer {
  private static Command autoCommand = new AutoCommand();
  public static Chassis chassis = Chassis.getInstance();
  public static Bull bull = Bull.getInstance();
  public static Everest everest = Everest.getInstance();
  private static NetworktablesSubSystem nts = NetworktablesSubSystem.getInstance();

  public static Joystick driver_joystick = new Joystick(Constants.DRIVER_STICK);
  public static Joystick arms_joystick = new Joystick(Constants.ARMS_STICK);

  private static JoystickButton collect_button = new JoystickButton(driver_joystick, Constants.COLLECTER_COLLECT_BUTTON);
  private static JoystickButton eject_button = new JoystickButton(driver_joystick, Constants.COLLECTER_EJECT_BUTTON);

  private static JoystickButton intake_forword_button = new JoystickButton(arms_joystick, Constants.INTAKE_BUTTON);

  private static JoystickButton angle_vision_pid_button = new JoystickButton(driver_joystick, Constants.ANGLE_VISION_PID_BUTTON);
  private static JoystickButton gyro_button = new JoystickButton(driver_joystick, 4);
  private static JoystickButton gyro_button1 = new JoystickButton(driver_joystick, 1);
  
  private static JoystickButton shoot_button = new JoystickButton(arms_joystick, Constants.SHOOT_BUTTON);
  private static JoystickButton shoot_dis_button = new JoystickButton(arms_joystick, Constants.SHOOT_DIS_BUTTON);
  
  private static JoystickButton trans_button = new JoystickButton(arms_joystick, Constants.TRANS_BUTTON);
  private static JoystickButton trans_dis_button = new JoystickButton(arms_joystick, Constants.TRANS_DIS_BUTTON);

  private static JoystickButton rolate_button = new JoystickButton(arms_joystick, Constants.ROLATE_BUTTON);

  private static POVButton everest_pullup_button = new POVButton(arms_joystick, Constants.Everest_Pullup_BUTTON);
  private static POVButton everest_dis_pullup_button = new POVButton(arms_joystick, Constants.Everest_Dis_Pullup_BUTTON);

  private static POVButton everest_climb_button = new POVButton(arms_joystick, Constants.Everest_Climb_BUTTON);
  private static POVButton everest_dis_climb_button = new POVButton(arms_joystick, Constants.Everest_Dis_Climb_BUTTON);
  

  public RobotContainer() {
    configureButtonBindings();
  }


  private void configureButtonBindings() {
    collect_button.whileHeld(new SetMotorCommand(bull, bull.collector, bull.COLLECTOR_SPEED));
    eject_button.whileHeld(new SetMotorCommand(bull, bull.collector, -bull.COLLECTOR_SPEED));
    
    intake_forword_button.whileHeld(new IntakeCommand(!bull.get_intake_state()));

    angle_vision_pid_button.whileHeld(new AngleVisionPidCommand());
    gyro_button.whenPressed(new DriveStraight(2, true));//new TurnToAngle(90));
    gyro_button1.whileHeld(new ChassisCommand());

    everest_pullup_button.whileHeld(new PullUpCommend(true));
    everest_dis_pullup_button.whileHeld(new PullUpCommend(false));   

    everest_climb_button.whileHeld(new SetMotorCommand(everest, everest.climber, everest.CLIMER_SPEED));
    everest_dis_climb_button.whileHeld(new SetMotorCommand(everest, everest.climber, -everest.CLIMER_SPEED));   

    shoot_button.whileHeld(new ShotWithClachCommand());// new ShootVelocityCommand(100));//new SetMotorCommand(bull.shoot, bull.SHOOT_SPEED));
    shoot_dis_button.whileHeld(new SetMotorCommand(bull.shoot, -bull.SHOOT_SPEED));

    trans_button.whileHeld(new SetMotorCanCommand(bull.transportation, -bull.TRANS_SPEED));
    trans_dis_button.whileHeld(new SetMotorCanCommand(bull.transportation, bull.TRANS_SPEED));


    rolate_button.whileHeld(new RolateCommand());
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoCommand;
  }
}
