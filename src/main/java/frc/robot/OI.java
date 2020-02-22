/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import frc.robot.Utilities.*;

/**
 * Add your docs here.
 */
/*
public class OI implements DriveController, OperatorController{
    controllerButtons driverController = new controllerButtons(RobotMap.DRIVER_CONTROLLER);
    controllerButtons operatorController = new controllerButtons(RobotMap.OPERATOR_CONTROLLER);

    public OI(){
    }

    @Override
    public double getForward(){
        double forward = driverController.getLeftAxisY();
        return Util.handleDeadband(forward, 0.03);
    }
    @Override
    public double getRotate(){
        double rotate = driverController.getRightAxisX();
        return  Util.handleDeadband(rotate, 0.03);
    }

    @Override
    public boolean getQuickTurn() {
        return driverController.getR1();
    }

    @Override
    public boolean getIntakeBtn() {
        return operatorController.getYellowButton();
    }

    @Override
    public double getManualShooter(){
        return operatorController.getLeftAxisY() * 0.75;
    }

    @Override
    public boolean getClimbingBtn() {
        return operatorController.getBlueButton();
    }
}
*/