package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class robothardwaremanager {
    private float colorGain=2;
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
    public NormalizedColorSensor ballSensor;
    

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
        ballSensor = hardwareMap.get(NormalizedColorSensor.class, "ballSensor");

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
        ballSensor.setGain(colorGain);

        // Initialize Pinpoint - ADJUST NAME TO MATCH YOUR CONFIG
        odo = hardwareMap.get(GoBildaPinpointDriver.class, "odo");
        odo.setOffsets(-84.0, -168.0, DistanceUnit.MM); // ADJUST TO YOUR ROBOT'S OFFSETS (mm)
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD);
        odo.resetPosAndIMU();
    }
}
