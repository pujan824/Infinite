/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  private final WPI_TalonSRX m_leftMaster, m_leftSlave;
  private final WPI_VictorSPX m_rightSlave1, m_rightSlave2;

  public Shooter() {
    m_leftMaster = new WPI_TalonSRX(RobotMap.SHOOTER_MASTER_LEFT);
    m_leftSlave = new WPI_TalonSRX(RobotMap.SHOOTER_SLAVE_LEFT);
    m_rightSlave1 = new WPI_VictorSPX(RobotMap.SHOOTER_SLAVE1_RIGHT);
    m_rightSlave2 = new WPI_VictorSPX(RobotMap.SHOOTER_SLAVE2_RIGHT);

    m_leftMaster.configFactoryDefault();
    m_leftSlave.configFactoryDefault();
    m_rightSlave1.configFactoryDefault();
    m_rightSlave2.configFactoryDefault();

    m_leftMaster.setNeutralMode(NeutralMode.Coast);
    m_leftSlave.setNeutralMode(NeutralMode.Coast);
    m_rightSlave1.setNeutralMode(NeutralMode.Coast);
    m_rightSlave2.setNeutralMode(NeutralMode.Coast);

    m_leftSlave.follow(m_leftMaster);
    m_rightSlave1.follow(m_leftMaster);
    m_rightSlave2.follow(m_leftMaster);

    m_leftSlave.setInverted(InvertType.FollowMaster);
    m_rightSlave1.setInverted(InvertType.OpposeMaster);
    m_rightSlave2.setInverted(InvertType.OpposeMaster);

    m_leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    m_leftMaster.setSelectedSensorPosition(0);

    m_leftMaster.setSensorPhase(true);

		/* Config the peak and nominal outputs */
		m_leftMaster.configNominalOutputForward(0, Constants.kTimeoutMs);
		m_leftMaster.configNominalOutputReverse(0, Constants.kTimeoutMs);
		m_leftMaster.configPeakOutputForward(1, Constants.kTimeoutMs);
		m_leftMaster.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		/* Config the Velocity closed loop gains in slot0 */
		m_leftMaster.config_kF(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kF, Constants.kTimeoutMs);
		m_leftMaster.config_kP(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kP, Constants.kTimeoutMs);
		m_leftMaster.config_kI(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kI, Constants.kTimeoutMs);
		m_leftMaster.config_kD(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kD, Constants.kTimeoutMs);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("RPM", getRPM());
    SmartDashboard.putNumber("current", getCurrent());

  }

  public void setRPM(double RPM){
    double targetVelocity_UnitsPer100ms = RPM * 4096 / 600;
			/* 500 RPM in either direction */
			m_leftMaster.set(ControlMode.Velocity, -targetVelocity_UnitsPer100ms);
  }
  public void setPower(double Power){
    m_leftMaster.set(ControlMode.PercentOutput, Power);
  }
  public double getRPM(){
    return -(m_leftMaster.getSelectedSensorVelocity()/6130.2)*600;
  }
  
  public double getCurrent(){
    return m_leftMaster.getSupplyCurrent();
  }
}
