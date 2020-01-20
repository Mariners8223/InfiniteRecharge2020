/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.ChassisCommand;
import frc.robot.subsystems.Chassis;


public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public ChassisCommand chassisCommand = new ChassisCommand();
  public Chassis chassis = Chassis.getInstance();
  public static Joystick joystick = new Joystick(Constants.JOYSTICK);


  public RobotContainer() {
    configureButtonBindings();
  }


  private void configureButtonBindings() {
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
