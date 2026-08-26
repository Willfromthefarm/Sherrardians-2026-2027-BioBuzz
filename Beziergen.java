package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.auto.localizationhub;
import org.firstinspires.ftc.teamcode.auto.robothardwaremanager;

public class Beziergen {
    robothardwaremanager bGRobot = new robothardwaremanager();
    localizationhub bGLocalization = new localizationhub(bGRobot);
    utilityclass utility = new utilityclass();
    utilityclass.Queue pathQueue = utility.new Queue(201);
    public double currentVelocity = 2;
    public double targetVelocity = 2;
    public Beziergen(double targetX, double targetY, double targetHeading){
        bGLocalization.updatePosition();
        double Y_0 = bGLocalization.currentypose;
        double X_0 = bGLocalization.currentxpose;
        double Y_1 = ((bGLocalization.currentypose)+Math.cos(bGLocalization.currentheading))*currentVelocity;
        double X_1 = ((bGLocalization.currentxpose)+Math.sin(bGLocalization.currentheading))*currentVelocity;
        double Y_2 = ((targetY)+Math.cos(targetHeading))*targetVelocity;
        double X_2 = ((targetX)+Math.sin(targetHeading))*targetVelocity;
        double Y_3 = targetY;
        double X_3 = targetX;
        for (int slice = 0; slice <= 200; slice++) {
            //converts the slice into the step
            double t = (double) slice/200;
            //eliminates some of the math in the curve gen
            double oneLessThanT = 1- t;
            //the curve gen, converts 4 points into a vector navigation path
            double xPoseCoords = (oneLessThanT*oneLessThanT*oneLessThanT*X_0)+(3*oneLessThanT*oneLessThanT*t*X_1)+(3*oneLessThanT*t*t*X_2)+(t*t*t*X_3);
            double yPoseCoords = (oneLessThanT*oneLessThanT*oneLessThanT*Y_0)+(3*oneLessThanT*oneLessThanT*t*Y_1)+(3*oneLessThanT*t*t*Y_2)+(t*t*t*Y_3);
            //stores the values in a queue
            pathQueue.insert(
                    utility.new coordinates(xPoseCoords,yPoseCoords)
            );
        }
    }
}
