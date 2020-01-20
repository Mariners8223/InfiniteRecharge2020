/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {
  private Spark m_frontLeft;
  private Spark m_backLeft;
  private SpeedControllerGroup m_left;

  private Spark m_frontRight;
  private Spark m_backRight;
  private SpeedControllerGroup m_right;

  private DifferentialDrive m_drive;

  private static Chassis instance;

  private Chassis(){
    m_frontLeft = new Spark(Constants.LEFT_FRONT_MOTOR);
    m_backLeft = new Spark(Constants.LEFT_BACK_MOTOR);
    m_left = new SpeedControllerGroup(m_frontLeft, m_backLeft);

    m_frontRight = new Spark(Constants.RIGHT_FRONT_MOTOR);
    m_backRight = new Spark(Constants.RIGHT_BACK_MOTOR);
    m_right = new SpeedControllerGroup(m_frontRight, m_backRight);

    m_drive = new DifferentialDrive(m_left, m_right);
  }

  public static Chassis getInstance() {
    if(instance == null) instance = new Chassis();
    return instance;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setSpeed(double l, double r){
    m_drive.tankDrive(l, r);
  }
}
