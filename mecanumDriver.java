package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;


public class Mecanumdrive {
    /* Declare OpMode members. */
    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;
    private DcMotor backLeftDrive;
    private DcMotor backRightDrive;
    private ElapsedTime     runtime = new ElapsedTime();
    private double Robotheading;
    public Mecanumdrive(HardwareMap hardwareMap){
    // Initialize the drive system variables.

    frontLeftDrive  = hardwareMap.get(DcMotor.class, "front_left");
    frontRightDrive = hardwareMap.get(DcMotor.class, "front_right");
    backLeftDrive  = hardwareMap.get(DcMotor.class, "back_left");
    backRightDrive = hardwareMap.get(DcMotor.class, "back_right");
    }
    public void driveTo(double xPower, double yPower, double turnPower){

    }
}



