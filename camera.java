package org.firstinspires.ftc.teamcode;


import org.firstinspires.ftc.teamcode.auto.PIDclass;
import org.firstinspires.ftc.teamcode.auto.localizationhub;
import org.firstinspires.ftc.teamcode.auto.robothardwaremanager;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

public class camera {
    public double angleoftargettag;
    public final double maxangle = 89;
    public final double minangle = 45; //45 for now, it'll be the minimum angle we find later
    public float autocorrection = 0;
    List<AprilTagDetection> currentDetections;
    static robothardwaremanager robot = new robothardwaremanager();
    PIDclass autoaim = new PIDclass(.008,0,1);
    public void retrieveDesiredDetections(double targetID) { //targetID is the ID or whatever of our target
        // Get list of detections from AprilTag Processor
        currentDetections = robot.aprilTag.getDetections();
        for(AprilTagDetection detected : currentDetections){
            if (detected.id == targetID){
                angleoftargettag = detected.ftcPose.bearing;
            }
        }

    }
    static localizationhub turretLocalization = new localizationhub(robot);
    private double turretservopositiontodegrees(double pos) {
        return( ( pos * 720 ) - 360 );
    }
    private double degreestoturretservoposition(double deg) {
        return( ( deg + 360 ) / 720 );
    }

    public void operate() {
        retrieveDesiredDetections(0); //set 0 to whatever our target tag ID is

        autocorrection = (float) autoaim.calculateCorrection(degreestoturretservoposition(angleoftargettag)); //now this is where the magic happens
    }
}

