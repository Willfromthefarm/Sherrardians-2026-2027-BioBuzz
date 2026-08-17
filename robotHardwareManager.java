package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class robothardwaremanager {
    // Motors:
    public DcMotor frontLeft, frontRight, backLeft, backRight;

    //Servos:
    
    // Pinpoint odometry and localization:
    public GoBildaPinpointDriver odo;

    //Sensors:

    
    public void init(HardwareMap hardwareMap) {
        // Initialize motors - motor names from your config
        frontLeft = hardwareMap.get(DcMotor.class, "motor 1");
        frontRight = hardwareMap.get(DcMotor.class, "motor 3");
        backLeft = hardwareMap.get(DcMotor.class, "motor 2");
        backRight = hardwareMap.get(DcMotor.class, "motor 4");

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

        // Initialize Pinpoint - ADJUST NAME TO MATCH YOUR CONFIG
        odo = hardwareMap.get(GoBildaPinpointDriver.class, "odo");
        odo.setOffsets(-84.0, -168.0, DistanceUnit.MM); // ADJUST TO YOUR ROBOT'S OFFSETS (mm)
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD);
        odo.resetPosAndIMU();
    }
}
