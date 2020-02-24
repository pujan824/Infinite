/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  private final VictorSP m_intakeMaster, m_intakeSlave;
  private final DoubleSolenoid m_intake;

  public Intake() {
    m_intakeMaster = new VictorSP(RobotMap.INTAKE);
    m_intakeSlave = new VictorSP(RobotMap.INTAKE_2);
    m_intake = new DoubleSolenoid(RobotMap.PISTON_INTAKE_IN, RobotMap.PISTON_INTAKE_OUT);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void updateShuffleboard(){
      
  }

  public void runIntake(double power){
    m_intakeMaster.setSpeed(-power);
    m_intakeSlave.setSpeed(power);
  }

  public void extendIntake(){
    m_intake.set(Value.kForward);
  }
  public void retractIntake(){
    m_intake.set(Value.kReverse);
  }
  public void setIntakeOff(){
    m_intake.set(Value.kOff);
  }
}
