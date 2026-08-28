package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.Beziergen;
import org.firstinspires.ftc.teamcode.utilityclass;

public class motorcontroller {
    robothardwaremanager mCRobot;
    public motorcontroller(robothardwaremanager robot) {
        mCRobot = robot;
    }
    private Beziergen bezier;
    utilityclass utility = new utilityclass();
    utilityclass.coordinates firstpoint = utility.new coordinates(0,0);
    utilityclass.coordinates nextpoint = utility.new coordinates(0,0);

    public char xchange;
    public boolean curveEnabled = false;
    public char ychange;
    public double headingchange;
    private double xpose;
    private double ypose;
    private double thetapose;
    private double firstX;
    private double firstY;
    private double nextX;
    private double nextY;

    public void turnto(double thetapose) {

    }
    public void forwardTo(double xpose) {

    }
    public void strafeTo(double ypose) {

    }
    public void pathTo(double xpose,double ypose,double thetapose) {
        localizationhub localization = new localizationhub(mCRobot);
        double headingDifference = Math.abs(
                ((thetapose - localization.currentheading + 180) % 360) - 180
        );
        //path generator here
        if(headingDifference<90){
            curveEnabled = true;
            bezier = new Beziergen(xpose,ypose,thetapose); //bezier gen
        }else{
            curveEnabled = false;
        }

        if(curveEnabled = true){
            firstpoint = bezier.nextPoint();
            while(bezier.empty()) {
                firstX = firstpoint.getXCoord();
                firstY = firstpoint.getYCoord();
                nextX = nextpoint.getXCoord();
                nextY = nextpoint.getYCoord();

                nextpoint = bezier.nextPoint();
            }
        }else{

        }


        //- direct path gen here
        //tight turn detector here
        //colision correction here
        //motion profiling here

    }

}
        //- direct path gen here
        //tight turn detector here
        //colision correction here
        //motion profiling here

    }

}
