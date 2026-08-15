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
    public void updatePosition(){
        localrobot.odo.update();
        Pose2D pose2D = localrobot.odo.getPosition();

        currentxpose = pose2D.getX(DistanceUnit.MM);
        currentypose = pose2D.getY(DistanceUnit.MM);
        currentheading = pose2D.getHeading(AngleUnit.DEGREES);
    }
}
