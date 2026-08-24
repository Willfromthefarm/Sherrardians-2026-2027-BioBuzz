package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

// this code demonstrates the advanced decision making process of the Sherrardian Autonomous

// can we call it Authonomas Thomas

    @Autonomous(name="CommandAuto", group="Robot")
    //@Disabled
    public class commandcenter extends LinearOpMode {
        //so anyways, we set up the motors
        private DcMotor FR, FL, BR, BL //front and back left and right, could change if the robot isn't a 4-wheeler for some reason or another

        robothardwaremanager robot = new robothardwaremanager();
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
    

