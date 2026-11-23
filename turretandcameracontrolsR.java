package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Utility;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.vision.apriltag.AprilTagClusterDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;
import java.util.Objects;

@Utility(name="red comp")
public class turretandcameracontrolsR extends LinearOpMode {
    static org.firstinspires.ftc.teamcode.robothardwaremanager robot = new org.firstinspires.ftc.teamcode.robothardwaremanager();
    static localizationhub turretLocalization = new localizationhub(robot);

    public static double angleoftargettag;
    public final double maxangle = 89;
    public final double minangle = 45; //45 for now, it'll be the minimum angle we find later
    static List<AprilTagDetection> currentDetections;
    public static boolean isDetectionFacingUp;
    static double targetB;
    static double targetE;
    static double targetR;
    static double targetY;
    static double correction;
    static double distanceHorToTgt;

    public static void retrieveDesiredDetections(String targetName) { //targetID is the ID or whatever of our target
        // Get list of detections from AprilTag Processor
        currentDetections = robot.aprilTag.getDetections();
        for(AprilTagDetection detected : currentDetections){
            if(detected instanceof AprilTagClusterDetection) {
                AprilTagClusterDetection ourtag = (AprilTagClusterDetection) detected;
                if (Objects.equals(ourtag.metadata.name, targetName)) {
                    angleoftargettag = detected.ftcPose.bearing;
                }

                // 1. Get the rotation matrix from the detection pose
                // In newer SDKs, rawPose provides the underlying Matrix
                // If using the standard detection.rawPose.R matrix:
                Orientation orientation = Orientation.getOrientation(
                        ourtag.rawPose.R,
                        AxesReference.EXTRINSIC,
                        AxesOrder.XYZ,
                        AngleUnit.DEGREES
                );
                targetB = ourtag.ftcPose.bearing;
                targetE = ourtag.ftcPose.elevation;
                targetR = ourtag.ftcPose.range;
                targetY = ourtag.ftcPose.yaw;

                // 2. Extract the Pitch (typically the Y-axis or X-axis depending on camera orientation)
                // For standard forward-facing cameras in XYZ order:
                double pitch = orientation.secondAngle;
                isDetectionFacingUp = pitch > 0;
            }
        }
    }



    double turretservopositiontodegrees(double pos) {
        return( ( pos * 720 ) - 360 );
    }

    static double degreestoturretservoposition(double deg) {
        double servopos = ( deg + 360 ) / 720;
        if(servopos > 1) {
            while(servopos > 1){
                servopos = servopos - 0.5;
            }
        }else if(servopos < 0){
            while(servopos < 0){
                servopos = servopos + 0.5;
            }
        }
        return servopos;
    }

    public static void operate() {
        retrieveDesiredDetections("RED AUDIENCE"); //set "our tag" to whatever our target tag ID is

        if(isDetectionFacingUp) {
            correction = (Math.sin(Math.toRadians(targetE)) * targetR) - 47; //it should be ~50 in, +3 to account for the camera being a smidge off the ground
            distanceHorToTgt = (Math.cos(Math.toRadians(targetE)) * targetR) + correction;
        }
    }
    public double literallyanyhorizontaldistancefromafortyangle(double range) {
        return (Math.cos(Math.toRadians(40)) * range);
    }
    public double literallyanyverticaldistancefromafortyangle(double range, double correction) {
        return (Math.sin(Math.toRadians(40)) * range) + correction;
    }
    public double literallyanyverticaldistancefromafortyangle(double range) {
        return literallyanyverticaldistancefromafortyangle(range,3);
    }
    public double literallyanysldfromafortyangleandy(double range, double correction) { //sld = straight line distance
        return (Math.sin(Math.toRadians(40)) / (range-correction));
    }
    public double literallyanysldfromafortyangleandy(double range) {
        return literallyanysldfromafortyangleandy(range, 3);
    }
    public static void scan() {
        robot.turretservo.setPosition(0);
        operate();
        for (int i = 1; i < 10; i++) {
            robot.turretservo.setPosition(i/10);
            operate();
            if(!currentDetections.isEmpty()){
                break;
            }
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        //ALL OF TURRETTEST.JAVA
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
        //TURRETTEST ENDS HERE
// }
// } Uncomment these if you're cutting out turrettest