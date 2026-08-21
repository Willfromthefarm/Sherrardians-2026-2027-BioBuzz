package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


public class Mecanumdrive {
    /* Declare OpMode members. */
    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;
    private DcMotor backLeftDrive;
    private DcMotor backRightDrive;
    private ElapsedTime     runtime = new ElapsedTime();
    
    public void moveTo(double xPower, double yPower, double turnPower){
        //pid controller goes here
        //motor feedforward goes here
        //motor motion profile goes here
        //power applied to motors here
    }
}



