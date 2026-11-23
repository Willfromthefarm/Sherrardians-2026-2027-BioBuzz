package org.firstinspires.ftc.teamcode;

import static java.lang.Math.abs;

public class stallsensorfusion {
    static org.firstinspires.ftc.teamcode.robothardwaremanager robot;
    static localizationhub sFLocalization = new localizationhub(robot);
    public double acceptedstallvalue = 1; //whatever this is accepted as until it's considered a stall
    public double acceptedstallpower = 0.5; //ditto with the motor power

    public double estEncoderChangeX() {
        double odoEncoderX = robot.odo.getEncoderX();
        double odoEncoderY = robot.odo.getEncoderY();
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            String exception; new RuntimeException(e);
        }
        double encoderXAfterOneSec = robot.odo.getEncoderX();
        double encoderYAfterOneSec = robot.odo.getEncoderY();
        return abs(encoderXAfterOneSec - odoEncoderX);
    }
    public double estEncoderChangeY() {
        double odoEncoderX = robot.odo.getEncoderX();
        double odoEncoderY = robot.odo.getEncoderY();
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            String exception; new RuntimeException(e);
        }
        double encoderXAfterOneSec = robot.odo.getEncoderX();
        double encoderYAfterOneSec = robot.odo.getEncoderY();
        return abs(encoderYAfterOneSec - odoEncoderY);
    }

    double powerFL = robot.frontLeft.getPower(); //i don't think we need to go out of our way to get the voltage when the power being sent to the motors is right there anyway like what's the big difference
    double powerFR = robot.frontRight.getPower();
    double powerBR = robot.backRight.getPower();
    double powerBL = robot.backLeft.getPower();
    double encoderYChg = estEncoderChangeY();
    double encoderXChg = estEncoderChangeX();
    public boolean isMotorStallingFL = (encoderYChg < acceptedstallvalue || encoderXChg < acceptedstallvalue) && powerFL > acceptedstallpower;
    public boolean isMotorStallingFR = (encoderYChg < acceptedstallvalue || encoderXChg < acceptedstallvalue) && powerFR > acceptedstallpower;
    public boolean isMotorStallingBL = (encoderYChg < acceptedstallvalue || encoderXChg < acceptedstallvalue) && powerBL > acceptedstallpower;
    public boolean isMotorStallingBR = (encoderYChg < acceptedstallvalue || encoderXChg < acceptedstallvalue) && powerBR > acceptedstallpower;

    public boolean isAnyMotorStalling() {
        return (isMotorStallingBR || isMotorStallingBL || isMotorStallingFR || isMotorStallingFL);
    }
}
