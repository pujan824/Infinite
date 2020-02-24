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
//import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.Utilities.Util;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */

   private final WPI_TalonSRX m_rightMaster, m_rightSlave;
   private final WPI_TalonSRX m_leftMaster, m_leftSlave;

   public static final int kTimeoutMs = 5;

   double mQuickStopAccumulator;
   public static final double THROTTLE_DEADBAND = 0.02;
   private static final double WHEEL_DEADBAND = 0.02;
   private static final double TURN_SENS = 1.0;

  public DriveTrain() {
    m_rightMaster = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_DRIVE);
    m_rightSlave = new WPI_TalonSRX(RobotMap.RIGHT_BACK_DRIVE);

    m_leftMaster = new WPI_TalonSRX(RobotMap.LEFT_FRONT_DRIVE);
    m_leftSlave = new WPI_TalonSRX(RobotMap.LEFT_BACK_DRIVE);

    m_rightMaster.configFactoryDefault();
    m_rightSlave.configFactoryDefault();
    m_leftMaster.configFactoryDefault();
    m_leftSlave.configFactoryDefault();

    m_rightMaster.setNeutralMode(NeutralMode.Coast);
    m_rightSlave.setNeutralMode(NeutralMode.Coast);
    m_leftMaster.setNeutralMode(NeutralMode.Coast);
    m_leftSlave.setNeutralMode(NeutralMode.Coast);

    m_rightSlave.follow(m_rightMaster, FollowerType.PercentOutput);
    m_leftSlave.follow(m_leftMaster, FollowerType.PercentOutput);

    m_leftMaster.setInverted(true);
    m_leftSlave.setInverted(InvertType.FollowMaster);

    m_rightMaster.configOpenloopRamp(Constants.DT_OPENLOOP_RAMP_RATE);
    m_rightSlave.configOpenloopRamp(Constants.DT_OPENLOOP_RAMP_RATE);
    m_leftMaster.configOpenloopRamp(Constants.DT_OPENLOOP_RAMP_RATE);
    m_leftSlave.configOpenloopRamp(Constants.DT_OPENLOOP_RAMP_RATE);

    m_rightMaster.configContinuousCurrentLimit(Constants.DT_CONTINUOUS_CURRENT);
    m_rightSlave.configContinuousCurrentLimit(Constants.DT_CONTINUOUS_CURRENT);
    m_leftMaster.configContinuousCurrentLimit(Constants.DT_CONTINUOUS_CURRENT);
    m_leftSlave.configContinuousCurrentLimit(Constants.DT_CONTINUOUS_CURRENT);

    //m_leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, arg1, arg2);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    updateShuffleboard();
  }

  public void curvatureDrive(double forward, double rotation, boolean isQuickTurn)
    {
        rotation = Util.handleDeadband(rotation, WHEEL_DEADBAND);
        forward = Util.handleDeadband(forward, THROTTLE_DEADBAND);

        double overPower;

        double angularPower;

        if(isQuickTurn)
        {
            if ((Math.abs(forward) < 0.2))
            {
                double alpha = 0.1;
                mQuickStopAccumulator = (1 - alpha * mQuickStopAccumulator + alpha * Util.limit(rotation, 1.0) * 2);
            }
            overPower = 1.0;
            angularPower = rotation;
        }
        else
        {
            overPower = 0.0;
            angularPower = Math.abs(forward) * rotation * TURN_SENS - mQuickStopAccumulator;
            if(mQuickStopAccumulator > 1)
            {
                mQuickStopAccumulator -= 1;
            }
            else if (mQuickStopAccumulator < -1)
            {
                mQuickStopAccumulator += 1;
            }
            else
            {
                mQuickStopAccumulator = 0.0;
            }
        }

        double rightMotors = forward - angularPower;
        double leftMotors = forward + angularPower;

        if (leftMotors > 1.0)
        {
            rightMotors -= overPower *(leftMotors - 1.0);
            leftMotors = 1.0;
        }
        else if (rightMotors > 1)
        {
            leftMotors -= overPower * (-1.0 - leftMotors);
            leftMotors = -1.0;
        }
        else if (leftMotors < -1.0)
        {
            rightMotors += overPower * (-1.0 - leftMotors);
            leftMotors = -1.0;
        }
        else if (rightMotors < -1.0)
        {
            leftMotors += overPower * (-1.0 - rightMotors);
            rightMotors = -1.0;
        }
        
        setLeftMotors(leftMotors);
        setRightMotors(rightMotors);
    }

  private void setRightMotors(double power) {
    m_rightMaster.set(ControlMode.PercentOutput, power);
  }

  private void setLeftMotors(double power) {
    m_leftMaster.set(ControlMode.PercentOutput, power);
  }

  public void arcadeDrive(double forward, double rotation){
    setLeftMotors(forward - rotation);
    setRightMotors(forward + rotation);
  }

  public void stop(){
    setRightMotors(0);
    setLeftMotors(0);
  }

  public void updateShuffleboard()
  {
      SmartDashboard.putNumber("Right Master", m_rightMaster.getMotorOutputPercent());
      SmartDashboard.putNumber("Right Slave", m_rightSlave.getMotorOutputPercent());
      SmartDashboard.putNumber("Left Master", m_leftMaster.getMotorOutputPercent());
      SmartDashboard.putNumber("Left Slave", m_leftSlave.getMotorOutputPercent());
      
  }

}
