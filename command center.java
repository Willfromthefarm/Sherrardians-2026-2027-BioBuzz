package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

// this code demonstrates the advanced decision making process of the Sherrardian Autonomous

    @Autonomous(name="CommandAuto", group="Robot")
    //@Disabled
    public class commandcenter extends LinearOpMode {


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
    

