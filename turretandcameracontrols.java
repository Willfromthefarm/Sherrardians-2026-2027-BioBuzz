package org.firstinspires.ftc.teamcode;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagLibrary;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

public class turretandcameracontrols {
    public double angleoftargettag;
    public final double maxangle = 89;
    public final double minangle = 45; //45 for now, it'll be the minimum angle we find later
    List<AprilTagDetection> currentDetections;

    public void retrieveDesiredDetections(double targetID) { //targetID is the ID or whatever of our target
        // Get list of detections from AprilTag Processor
        currentDetections = robot.aprilTag.getDetections();
        for(AprilTagDetection detected : currentDetections){
            if (detected.id == targetID){
                angleoftargettag = detected.ftcPose.bearing;
            }
        }

    }
    static org.firstinspires.ftc.teamcode.robothardwaremanager robot;
    static localizationhub turretLocalization = new localizationhub(robot);
    private double turretservopositiontodegrees(double pos) {
        return( ( pos * 720 ) - 360 );
    }
    private double degreestoturretservoposition(double deg) {
        return( ( deg + 360 ) / 720 );
    }

    public void operate() {
        retrieveDesiredDetections(0); //set 0 to whatever our target tag ID is

        PIDclass.calculateCorrection(degreestoturretservoposition(angleoftargettag)); //now this is where the magic happens
    }
}
