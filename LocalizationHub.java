package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class localizationhub {
    robothardwaremanager localrobot;
    public double currentxpose;
    public double currentypose;
    public double currentheading;
    public localizationhub(robothardwaremanager robot) {
        localrobot = robot;
    }
    public void updatePinpointPosition(){
        localrobot.odo.update();
        Pose2D pose2D = localrobot.odo.getPosition();

        currentxpose = pose2D.getX(DistanceUnit.MM);
        currentypose = pose2D.getY(DistanceUnit.MM);
        currentheading = pose2D.getHeading(AngleUnit.DEGREES);
    }
    public void resetPosition(){
        //nada yet
    }
    public void compareInputs(){
        //compare the inputs of various sources, pinpoint, rev imu, and april tags
        //find the likelyhood of each being right
    }
    public void updateRobotLocation(){
        //input various sources to find where the robot most likely is
    }
    public void updateIMU(){
        //what it says
    }
    
}
