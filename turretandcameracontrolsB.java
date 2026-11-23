package org.firstinspires.ftc.teamcode;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.JavaUtil;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Utility;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagClusterDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagLibrary;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.ArrayList;
import java.util.List;

@Utility(name="blue comp")
public class turretandcameracontrolsB extends LinearOpMode {
    static robothardwaremanager robot;
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
    PIDclass autoaim = new PIDclass(.008,0,1);
    static double correction;
    static double distanceHorToTgt;

    public static void retrieveDesiredDetections(String targetName) { //targetID is the ID or whatever of our target
        // Get list of detections from AprilTag Processor
        currentDetections = robot.aprilTag.getDetections();
        for(AprilTagDetection detected : currentDetections){
            if(detected instanceof AprilTagClusterDetection) {
                AprilTagClusterDetection ourtag = (AprilTagClusterDetection) detected;
                if (ourtag.metadata.name == targetName) {
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
        retrieveDesiredDetections("BLUE AUDIENCE"); //set "our tag" to whatever our target tag ID is
        if(isDetectionFacingUp) {
            correction = (Math.sin(Math.toRadians(targetE)) * targetR) - 47; //should be ~50 in, +3 to account for the camera being a smidge off the ground
            distanceHorToTgt = (Math.cos(Math.toRadians(targetE)) * targetR) + correction;
            autocorrection = (float) autoaim.calculateCorrection(degreestoturretservoposition(angleoftargettag)); //now this is where the magic happens
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
    public double literallyanyslopefromafortyangleandx(double range) {
        return (Math.cos(Math.toRadians(40)) / range);
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
        turretangle = hardwareMap.get(Servo.class, "turretangle");
        turretservo = hardwareMap.get(Servo.class, "turretservo");
        Launcher = hardwareMap.get(DcMotor.class, "Launcher");
        Launcher2 = hardwareMap.get(DcMotor.class, "Launcher 2");
        bottomtrans = hardwareMap.get(CRServo.class, "bTrans");
        middletrans = hardwareMap.get(CRServo.class, "mTrans");
        toptrans = hardwareMap.get(CRServo.class, "tTrans");
        intake = hardwareMap.get(DcMotor.class, "intake");
        tiptoptrans = hardwareMap.get(CRServo.class, "tTTrans");
        upTrans = hardwareMap.get(CRServo.class, "upTrans");

        ElapsedTime runtime;
        float axial;
        float lateral;
        float yaw;
        double max;

        frontleft = hardwareMap.get(DcMotor.class, "frontleft");
        frontright = hardwareMap.get(DcMotor.class, "frontright");
        backleft = hardwareMap.get(DcMotor.class, "backleft");
        backright = hardwareMap.get(DcMotor.class, "backright");

        runtime = new ElapsedTime();
        // ########################################################################################
        // !!! IMPORTANT Drive Information. Test your motor directions. !!!!!
        // ########################################################################################
        //
        // Most robots need the motors on one side to be reversed to drive forward.
        // The motor reversals shown here are for a "direct drive" robot
        // (the wheels turn the same direction as the motor shaft).
        //
        // If your robot has additional gear reductions or uses a right-angled drive, it's important to ensure
        // that your motors are turning in the correct direction. So, start out with the reversals here, BUT
        // when you first test your robot, push the left joystick forward and observe the direction the wheels turn.
        //
        // Reverse the direction (flip FORWARD <-> REVERSE ) of any wheel that runs backward.
        // Keep testing until ALL the wheels move the robot forward when you push the left joystick forward.
        // <--- Click blue icon to see important note re. testing motor directions.
        frontleft.setDirection(DcMotor.Direction.REVERSE);
        frontright.setDirection(DcMotor.Direction.FORWARD);
        backleft.setDirection(DcMotor.Direction.REVERSE);
        backright.setDirection(DcMotor.Direction.FORWARD);
        // Wait for the game to start (driver presses START)
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        turretangle.setPosition(0.3);
        speed = 1550;
        waitForStart();
        runtime.reset();
        // Put initialization blocks here.
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                // Put loop blocks here.
                if (gamepad2.b) {
                    turretangle.setPosition(0.3);
                    speed = 1550;
                }
                if (gamepad2.a) {
                    turretangle.setPosition(0.7);
                    speed = 1650;
                }
                if (gamepad2.dpad_up) {
                    turretservo.setPosition(115.0 / 300.0);
                }
                if (gamepad2.dpad_down) {
                    turretservo.setPosition(0.0 / 300.0);
                }
                if (gamepad2.dpad_left) {
                    turretservo.setPosition(140.0 / 300.0);
                }
                if (gamepad1.dpad_up){
                    speed = speed + 10;
                }
                if (gamepad1.dpad_down){
                    speed = speed - 10;
                }
                if (gamepad1.dpad_left){
                    speed = 1480;
                }
                if (gamepad1.dpad_right){
                    speed = 1720;
                }
                telemetry.addData("speed", speed);
                ((DcMotorEx) Launcher).setVelocity(speed);
                ((DcMotorEx) Launcher2).setVelocity(speed);
                if (gamepad1.a){
                    bottomtrans.setPower(-1);
                    middletrans.setPower(1);
                    toptrans.setPower(-1);
                    tiptoptrans.setPower(1);
                    upTrans.setPower(1);
                }else if(gamepad1.b){
                    bottomtrans.setPower(1);
                    middletrans.setPower(-1);
                    toptrans.setPower(1);
                    tiptoptrans.setPower(-1);
                    upTrans.setPower(-1);
                }else{
                    bottomtrans.setPower(0);
                    middletrans.setPower(0);
                    toptrans.setPower(0);
                    tiptoptrans.setPower(0);
                    upTrans.setPower(0);
                }
                intake.setPower(gamepad1.right_trigger);

                // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
                // Note: pushing stick forward gives negative value
                axial = -gamepad1.left_stick_y;
                lateral = gamepad1.left_stick_x;
                yaw = gamepad1.right_stick_x;
                // Combine the joystick requests for each axis-motion to determine each wheel's power.
                // Set up a variable for each drive wheel to save the power level for telemetry.
                frontLeftPower = axial + lateral + yaw;
                frontRightPower = axial - lateral - yaw;
                backLeftPower = axial - lateral + yaw;
                backRightPower = axial + lateral - yaw;
                // Normalize the values so no wheel power exceeds 100%
                // This ensures that the robot maintains the desired motion.
                max = JavaUtil.maxOfList(JavaUtil.createListWith(Math.abs(frontLeftPower), Math.abs(frontRightPower), Math.abs(backLeftPower), Math.abs(backRightPower)));
                if (max > 1) {
                    frontLeftPower = frontLeftPower / max;
                    frontRightPower = frontRightPower / max;
                    backLeftPower = backLeftPower / max;
                    backRightPower = backRightPower / max;
                }
                // Send calculated power to wheels.
                frontleft.setPower(frontLeftPower);
                frontright.setPower(frontRightPower);
                backleft.setPower(backLeftPower);
                backright.setPower(backRightPower);
                // Show the elapsed game time and wheel power.
                telemetry.addData("Status", "Run Time: " + runtime);
                telemetry.addData("Front left/Right", JavaUtil.formatNumber(frontLeftPower, 4, 2) + ", " + JavaUtil.formatNumber(frontRightPower, 4, 2));
                telemetry.addData("Back  left/Right", JavaUtil.formatNumber(backLeftPower, 4, 2) + ", " + JavaUtil.formatNumber(backRightPower, 4, 2));
                telemetry.update();
            }
        }
    }
