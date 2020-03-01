package frc.robot;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.IntakeToggleCommand;
import frc.robot.commands.RolateCommand;
import frc.robot.commands.SetMotorCanCommand;
import frc.robot.commands.ShotWithClachCommand;
import frc.robot.commands.TransportAutoCommand;
import frc.robot.commands.TurnToAngle;
import frc.robot.commands.Auto1Command;
import frc.robot.commands.Auto2_1Command;
import frc.robot.commands.ChassisCommand;
import frc.robot.commands.DriveStraight;
import frc.robot.subsystems.Bull;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Everest;
import frc.robot.subsystems.Rolate;

public class RobotContainer {
  public static Chassis chassis = Chassis.getInstance();
  public static Bull bull = Bull.getInstance();
  public static Everest everest = Everest.getInstance();
  public static Rolate rolate = Rolate.getInstance();

  public static Command autCommand;

  public static Joystick driver_joystick = new Joystick(Constants.DRIVER_STICK);
  public static Joystick arms_joystick = new Joystick(Constants.ARMS_STICK);

  private static JoystickButton collect_button = new JoystickButton(driver_joystick,
      Constants.COLLECTER_COLLECT_BUTTON);
  private static JoystickButton eject_button = new JoystickButton(driver_joystick, Constants.COLLECTER_EJECT_BUTTON);

  private static JoystickButton intake_forword_button = new JoystickButton(arms_joystick, Constants.INTAKE_BUTTON);

  // private static JoystickButton angle_vision_pid_button = new
  // JoystickButton(arms_joystick, Constants.ANGLE_VISION_PID_BUTTON);
  // private static JoystickButton gyro_button = new
  // JoystickButton(driver_joystick, 4);
  private static JoystickButton gyro_button1 = new JoystickButton(driver_joystick, 1);

  private static JoystickButton shoot_button = new JoystickButton(arms_joystick, Constants.SHOOT_BUTTON);
  private static JoystickButton shoot_dis_button = new JoystickButton(arms_joystick, Constants.SHOOT_DIS_BUTTON);

  private static JoystickButton trans_button = new JoystickButton(arms_joystick, Constants.TRANS_BUTTON);
  private static JoystickButton trans_dis_button = new JoystickButton(arms_joystick, Constants.TRANS_DIS_BUTTON);
  private static JoystickButton trans_full_button = new JoystickButton(driver_joystick, Constants.TRANS_BUTTON);



  private static JoystickButton rolate_button = new JoystickButton(arms_joystick, Constants.ROLATE_BUTTON);

  private static POVButton everest_pullup_button = new POVButton(arms_joystick, Constants.Everest_Pullup_BUTTON);
  private static JoystickButton everest_dis_pullup_button = new JoystickButton(arms_joystick, Constants.Everest_Dis_Pullup_BUTTON);
  private static JoystickButton everest_dis_pullup_button2 = new JoystickButton(arms_joystick, Constants.Everest_Dis_Pullup_BUTTON+1);

  private static POVButton everest_climb_button = new POVButton(arms_joystick, Constants.Everest_Climb_BUTTON);
  private static POVButton everest_dis_climb_button = new POVButton(arms_joystick, Constants.Everest_Dis_Climb_BUTTON);

  private static JoystickButton cam_button = new JoystickButton(arms_joystick, 8);

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    collect_button.whileHeld(new IntakeToggleCommand());
    // collect_button.whileHeld(new SetMotorCanCommand(bull, bull.collector, bull.COLLECTOR_SPEED));
    // eject_button.whileHeld(new SetMotorCanCommand(bull, bull.collector, -bull.COLLECTOR_SPEED));
    
    // angle_vision_pid_button.whileHeld(new AngleVisionPidCommand());
    // gyro_button.whenPressed(new TurnToAngle(90));
    gyro_button1.whileHeld(new ChassisCommand());

    everest_pullup_button.whileHeld(new SetMotorCanCommand(everest, everest.pullup, everest.PULLUP_SPEED));
    //everest_dis_pullup_button.whileHeld(new SetMotorCanCommand(everest, everest.pullup, -everest.PULLUP_SPEED));

    everest_climb_button.whileHeld(new SetMotorCanCommand(everest, everest.climber, everest.CLIMER_SPEED));
    everest_dis_climb_button.whileHeld(new SetMotorCanCommand(everest, everest.climber, -everest.CLIMER_SPEED));
    // SmartDashboard.putNumber("speed", 0);
    shoot_button.toggleWhenPressed(new ShotWithClachCommand(), bull.shoot_trigger);// new SetMotorCanCommand(bull.shoot,
                                                                                   // -bull.SHOOT_SPEED));
    shoot_dis_button.whileHeld(new SetMotorCanCommand(bull.shoot, 0.5));
    trans_button.whileHeld(new SetMotorCanCommand(bull.transportation, bull.TRANS_SPEED));
    trans_dis_button.whileHeld(new SetMotorCanCommand(bull.transportation, -bull.TRANS_SPEED));
    trans_full_button.whileHeld(new SetMotorCanCommand(bull.transportation, -0.7));

    // intake_forword_button.toggleWhenPressed(new IntakeCommand(), bull.intake_toggle);
    rolate_button.toggleWhenPressed(new RolateCommand(), rolate.rolate_toggle);

    //cam_button.whenPressed(new CameraCommand());
  }

  public static boolean is_eject_button(){
    return eject_button.get();
  }

  public static boolean is_dis_pullup_button(){
    return everest_dis_pullup_button2.get();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   return new Auto1Command();
  // }
// Path Weaver Aotunomos
  public Command getAutonomousCommand() {
    TrajectoryConfig config = new TrajectoryConfig(Constants.MAX_SPEED_M_PER_S, Constants.MAX_ACALERTON_M_PER_S_PER_S);
    config.setKinematics(chassis.getKinematics());

    Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
        Arrays.asList(new Pose2d(), new Pose2d(1.0, 0, new Rotation2d()),
            new Pose2d(2.3, 1.2, Rotation2d.fromDegrees(90.0))),
        config
    );

    // String trajectoryJSON = "paths/YourPath.wpilib.json";
    // try {
    //   Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
    //   Trajectory trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    // } catch (IOException ex) {
    //   DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
    // } 


    RamseteCommand command = new RamseteCommand(
        trajectory,
        chassis::getPose,
        new RamseteController(2, .7),
        chassis.getFeedforward(),
        chassis.getKinematics(),
        chassis::getSpeeds,
        chassis.getLeftPIDController(),
        chassis.getRightPIDController(),
        chassis::setOutputVolts,
        chassis
    );

    return command.andThen(() -> chassis.setOutputVolts(0, 0));
  }
}
