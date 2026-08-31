// originally from will's branch

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

    // initialize variables for the checks
    double previousX = 0
    double previousY = 0
    double xDiff = 0
    double yDiff = 0
    double estdHeading = 0
    double slope = 0
    double lastEstdHeading = 0
    double headingChg = 0
    double acceptableMax = (3.14 / 2) // maximum accepted turn radius, set to a right angle for now
  
    public Beziergen(double targetX, double targetY, double targetHeading){
        bGLocalization.updatePosition();
        double Y_0 = bGLocalization.currentypose;
        double X_0 = bGLocalization.currentxpose;
        double Y_1 = ((bGLocalization.currentypose)+Math.cos(bGLocalization.currentheading))*currentVelocity;
        double X_1 = ((bGLocalization.currentxpose)+Math.cos(bGLocalization.currentheading))*currentVelocity;
        double Y_2 = ((targetY)+Math.cos(targetHeading))*targetVelocity;
        double X_2 = ((targetX)+Math.cos(targetHeading))*targetVelocity;
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
                    utility.new coordinates(xPoseCoords,yPoseCoords);
            );
          
            if (slice != 0) { // doing this with the first point would cause issues with the last point being undefined
              
              xDiff = previousX - xPoseCoords; // change in X value
              yDiff = previousY - yPoseCoords; // change in Y value
              slope = yDiff / xDiff; // rise over run
              estdHeading = arcsin(slope); // estimated heading going from the last point to this point - import a math function or something to get arcsin
              
              if (slice >= 2) { // same with the last est'd heading being undefined before the second point
                headingChg = lastEstdHeading - estdHeading // est'd heading change between points, in radians
                  
                if (headingChg >= acceptableMax) {
                  // break function and stop generating curve
                  return String[Too steep!] // or something
                    
                }
                
              }
              
            }
              
            lastEstdHeading = estdHeading;
            previousX = xPoseCoords;
            previousY = yPoseCoords;
          
        }
      
    }
    public utilityclass.coordinates nextPoint(){
        return pathQueue.remove();
    }
    public boolean empty(){
        return pathQueue.isEmpty();
    }
}
