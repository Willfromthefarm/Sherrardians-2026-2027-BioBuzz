package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "turrettest (Blocks to Java)")
public class turrettest extends LinearOpMode {

    /**
     * This sample contains the bare minimum Blocks for any regular
     OpMode. The 3 blue
     * Comment Blocks show where to place Initialization code (runs
     once, after touching the
     * DS INIT button, and before touching the DS Start arrow), Run code
     (runs once, after
     * touching Start), and Loop code (runs repeatedly while the OpMode
     is active, namely not
     * Stopped).
     */
    @Override
    public void runOpMode() {
        Servo turretservo = hardwareMap.get(Servo.class, "turretservo");
        Servo turretangle = hardwareMap.get(Servo.class, "turretangle");
        DcMotorEx launcher = hardwareMap.get(DcMotorEx.class, "Launcher");
        DcMotorEx launcher_2 = hardwareMap.get(DcMotorEx.class, "Launcher 2");

        launcher.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        launcher_2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        launcher.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        launcher_2.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        launcher.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,
                new PIDFCoefficients(40, 0, 0, 12.5));
        launcher_2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,
                new PIDFCoefficients(40, 0, 0, 12.5));

        turretservo.setPosition(0);
        turretangle.setPosition(.93);
        //      Launcher.setVelocity(3000);
        //     Launcher_2.setVelocity(3000);

        // Put initialization blocks here.
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                // Put loop blocks here.
                if (gamepad1.dpadUpWasPressed()) {
                    turretservo.setPosition(0);
                }else if (gamepad1.dpadDownWasPressed()) {
                    turretservo.setPosition((double) 50 / 300);
                }else{
                    turretservo.setPosition(0);

                }
                if (gamepad1.a) {
                    turretangle.setPosition(0.32);
                }
                if (gamepad1.b) {
                    turretangle.setPosition(.93);
                }
                telemetry.update();

                launcher.setVelocity(1250);
                launcher.setVelocity(1250);




            }
        }
    }
}