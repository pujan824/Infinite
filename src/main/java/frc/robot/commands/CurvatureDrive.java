/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class CurvatureDrive extends CommandBase {
  /**
   * Creates a new CurvatureDrive.
   */
  private final DriveTrain m_DriveTrain;
  private final Joystick m_driverController;

  public CurvatureDrive(DriveTrain driveTrain, Joystick driverController) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_DriveTrain = driveTrain;
    m_driverController = driverController;

    addRequirements(m_DriveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double forward = m_driverController.getRawAxis(1)*-1;
    double rotation = m_driverController.getRawAxis(4)*-1;
    boolean isQuickTurn = m_driverController.getRawButton(6);

    m_DriveTrain.curvatureDrive(forward, rotation, isQuickTurn);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
