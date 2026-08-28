package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class localizationhub {

    robothardwaremanager localrobot;
    public double currentxpose;
    public double currentypose;
    public double currentheading;
    public double lastXPose, lastYPose, lastHeading;
    public double timeSinceReset;
    public double drivevelocity;
    private ElapsedTime runtime = new ElapsedTime();
    public localizationhub(robothardwaremanager robot) {
        localrobot = robot;
    }
    public void updatePosition(){
        //stores the last position for velocity calculations
        lastXPose = currentxpose;
        lastYPose = currentypose;
        lastHeading = currentheading;
        //updates the position of the robot
        localrobot.odo.update();

        timeSinceReset = runtime.milliseconds();

        Pose2D pose2D = localrobot.odo.getPosition();
        //get current position
        currentxpose = pose2D.getX(DistanceUnit.MM);
        currentypose = pose2D.getY(DistanceUnit.MM);
        currentheading = pose2D.getHeading(AngleUnit.DEGREES);
        //velocity math
        double xVelocity = (currentxpose - lastXPose)/(timeSinceReset);
        double yVelocity = (currentypose - lastYPose)/(timeSinceReset);

        //v^2 = v_0^2 + 2(a)(x-x_0)
        //reset timer
        runtime.reset();
    }
}
