package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagLibrary;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

public class robothardwaremanager {
    AprilTagProcessor.Builder ATPBuild;
    AprilTagProcessor aprilTag;
    AprilTagLibrary currentTagLibrary;
    private float colorGain = 2;
    // Motors
    public DcMotor frontLeft, frontRight, backLeft, backRight;
    public DcMotor speedy2;
    public DcMotor intake;
    public DcMotor speedy1;
    // public DcMotor intake1;
    public Servo servolaunch;
    public Servo storageservo;
    public Servo turretservo;
    // Pinpoint odometry
    public GoBildaPinpointDriver odo;
    public NormalizedColorSensor ballSensorL;
    public NormalizedColorSensor ballSensorR;


    public void init(HardwareMap hardwareMap) {
        // Initialize motors - motor names from your config
        frontLeft = hardwareMap.get(DcMotor.class, "motor 1");
        frontRight = hardwareMap.get(DcMotor.class, "motor 3");
        backLeft = hardwareMap.get(DcMotor.class, "motor 2");
        backRight = hardwareMap.get(DcMotor.class, "motor 4");
        intake = hardwareMap.get(DcMotor.class, "intake");
        speedy1 = hardwareMap.get(DcMotor.class, "upperlaunch");
        speedy2 = hardwareMap.get(DcMotor.class, "lowerlaunch");
        servolaunch = hardwareMap.get(Servo.class, "servolaunch");
        storageservo = hardwareMap.get(Servo.class, "storageservo");
        turretservo = hardwareMap.get(Servo.class, "turretservo");
        ballSensorL = hardwareMap.get(NormalizedColorSensor.class, "ballSensorL");
        ballSensorR = hardwareMap.get(NormalizedColorSensor.class, "ballSensorR");

        // Set motor directions - ADJUST IF ROBOT MOVES BACKWARDS
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        // Set zero power behavior
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //set the color sensor settings
        ballSensorL.setGain(colorGain);
        ballSensorR.setGain(colorGain);

        ((ServoImplEx) turretservo).setPwmRange(new PwmControl.PwmRange(50, 2500));

        // Initialize Pinpoint - ADJUST NAME TO MATCH YOUR CONFIG
        odo = hardwareMap.get(GoBildaPinpointDriver.class, "odo");
        odo.setOffsets(-84.0, -168.0, DistanceUnit.MM); // ADJUST TO YOUR ROBOT'S OFFSETS (mm)
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD);
        odo.resetPosAndIMU();

        // Create a new AprilTagProcessor.Builder object and assign it to a variable.
        ATPBuild = new AprilTagProcessor.Builder();

        // Get the AprilTagLibrary for the current season.
        currentTagLibrary = AprilTagGameDatabase.getCurrentGameTagLibrary();

        // Set the tag library.
        ATPBuild.setTagLibrary(currentTagLibrary);

        // Build the AprilTag processor and assign it to a variable.
        aprilTag = ATPBuild.build();

        // Initialize camera
        VisionPortal.Builder webcam = new VisionPortal.Builder();
                webcam.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
                webcam.addProcessor(aprilTag);
                webcam.build();

    }
}
