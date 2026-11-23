package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "comp")
public class launchmotormangr extends LinearOpMode { //I'M MANGR.

    private Servo turretangle;
    private Servo turretservo;
    private DcMotor Launcher;
    private DcMotor Launcher2;
    private DcMotor frontleft;
    private DcMotor frontright;
    private DcMotor backleft;
    private DcMotor backright;
    private CRServo bottomtrans;
    private CRServo middletrans;
    private CRServo toptrans;
    private DcMotor intake;
    double frontLeftPower;
    double backLeftPower;
    double frontRightPower;
    double backRightPower;
    private CRServo tiptoptrans;

    public static boolean autoalignmode = false;

    @Override
    public void runOpMode(){
        while(opModeIsActive()){
            if(gamepad1.left_bumper){
                autoalignmode = !autoalignmode;
                telemetry.addData("Auto-Alignment", autoalignmode);
                sleep(500);
            }
        }
    }
}
