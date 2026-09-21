package org.firstinspires.ftc.teamcode.auto;

public class motorcontroller {
    robothardwaremanager robot;
    public char xchange;
    public char ychange;
    public double headingchange;
    private double xpose;
    private double ypose;
    private double thetapose;
    private double xcorr;
    private double ycorr;
    public void motorcontroller() {

    }
    public void Driveto(double xpose,double ypose,double thetapose,bool absoluteposition) {

        xchange= currentX + xpose; // i'm getting tired, the currentX,Y, Heading and other odometry stuff can be added later
        ychange = currentY + ypose;
        startheading = currentHeading;
        double thetatotarget = Math.tan(ychange,xchange);
        double turningneeded = currentHeading-thetatotarget
        while (startheading != turningneeded) { 
                PIDclass.calculate(startheading, turningneeded);
                // turn (move the motors needed to turn that direction, functionality that doesn't seem to be implemented yet) to make it turn this direction
        }
        if (absoluteposition == false){
            while (xchange != currentX && ychange != currentY) {
                xcorr = PIDclass.calculate(currentX, xchange);
                ycorr = PIDclass.calculate(currentY, ychange);
                Math.arctan( (ycorr / xcorr) ) //Distance needed to correct
                // move forward by moving the necessary motors in the necessary directions to make it go a given direction
            
            }
        }else{
            while (xpose != currentX && ypose != currentY) {
                xcorr = PIDclass.calculate(currentX, xpose);
                ycorr = PIDclass.calculate(currentY, ypose);
                Math.arctan( (ycorr / xcorr) ) //Distance needed to correct
                // move forward
            
            }
        }

        while (currentHeading != thetapose) { 

            if ( (thetapose - currentHeading) > 3.14 ) {
                
                // turn left
                
            }else{
                
                // turn right
                
            }
        }
    }

}

}
