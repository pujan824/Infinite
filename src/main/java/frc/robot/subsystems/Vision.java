/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {
  /**
   * Creates a new Vision.
   */

  private final NetworkTable m_limelight;
  private double tx, ty, tv;

  public Vision() {
    m_limelight = NetworkTableInstance.getDefault().getTable("limelight-shooter");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    tx = m_limelight.getEntry("tx").getDouble(0);
    ty = m_limelight.getEntry("ty").getDouble(0);
    tv = m_limelight.getEntry("tv").getDouble(0);

    SmartDashboard.putNumber("limelight X", tx);
    SmartDashboard.putNumber("limelight Y", ty);
    SmartDashboard.putNumber("Target Found", tv);
  }

  public double getTX() {
    return tx;
  }

  public double getTY() {
    return ty;
  }

  public boolean isTargetValid() {
    return (tv == 1.0); 
  }
}
