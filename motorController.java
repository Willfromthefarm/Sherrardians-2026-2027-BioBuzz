package org.firstinspires.ftc.teamcode.auto;

public class motorcontroller {
    robothardwaremanager robot;
    public char xchange;
    public char ychange;
    public double headingchange;
    private double xpose;
    private double ypose;
    private double thetapose;
    public void motorcontroller() {

    }
    public void Driveto(double xpose,double ypose,double thetapose,bool absoluteposition) {

        xchange= currentX + xpose; // i'm getting tired, the currentX,Y, Heading and other odometry stuff can be added later
        ychange = currentY + ypose;
        startheading = currentHeading;
        double thetatotarget = Math.tan(ychange,xchange);
        double turningneeded = currentHeading-thetatotarget
        while (startheading != turningneeded) { 

        if ( (turningneeded - startheading) > 3.14 ) {
                
                // turn left
                
        }else{
                
                // turn right
                    
        }
        if (absoluteposition == false){
            while (xchange != currentX && ychange != currentY) {

                // move forward
            
            }
        }else{
            while (xpose != currentX && ypose != currentY) {

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
