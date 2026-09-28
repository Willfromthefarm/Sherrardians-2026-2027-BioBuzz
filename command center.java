package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

// this code demonstrates the advanced decision making process of the Sherrardian Autonomous

// can we call it Authonomas Thomas

@Autonomous(name="CommandAuto", group="Robot")
//@Disabled
public class commandcenter extends LinearOpMode {
    //nevermind, the motors will be set up in another program evidently

    org.firstinspires.ftc.teamcode.robothardwaremanager robot = new org.firstinspires.ftc.teamcode.robothardwaremanager();
    @Override

    public void runOpMode() {

        robot.init(hardwareMap);

        localizationhub localization = new localizationhub(robot);



        waitForStart();
        //do fun things
        localization.updatePosition();
        telemetry.addData("X", localization.currentxpose);
        telemetry.addData("Y", localization.currentypose);
        telemetry.addData("Heading", localization.currentheading);
        telemetry.update();


    }
}
