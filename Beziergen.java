package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.auto.localizationhub;
import org.firstinspires.ftc.teamcode.auto.robothardwaremanager;

public class Beziergen {
    // create instances of the robot, localization, and the utilities
    robothardwaremanager bGRobot = new robothardwaremanager();
    localizationhub bGLocalization = new localizationhub(bGRobot);
    utilityclass utility = new utilityclass();
    //create a queue with 201 points
    utilityclass.Queue pathQueue = utility.new Queue(201);
    //how far away P_1 and P_2 should be from P_0 and P_3 respectivly
    public double currentVelocity = 2;
    public double targetVelocity = 2;
    //constructor
    public Beziergen(double targetX, double targetY, double targetHeading){
        bGLocalization.updatePosition();
        //P_0 calculator
        double Y_0 = bGLocalization.currentypose;
        double X_0 = bGLocalization.currentxpose;
        //P_1 calculator
        double Y_1 = ((bGLocalization.currentypose)+Math.cos(bGLocalization.currentheading))*currentVelocity;
        double X_1 = ((bGLocalization.currentxpose)+Math.sin(bGLocalization.currentheading))*currentVelocity;
        //P_2 calculator
        double Y_2 = ((targetY)+Math.cos(targetHeading))*targetVelocity;
        double X_2 = ((targetX)+Math.sin(targetHeading))*targetVelocity;
        //P_3 calculator
        double Y_3 = targetY;
        double X_3 = targetX;
        //Queue creator
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
