package org.firstinspires.ftc.teamcode.auto;

public class motorcontroller {
    robothardwaremanager mCRobot;
    public motorcontroller(robothardwaremanager robot) {
        mCRobot = robot;
    }
    public char xchange;
    public char ychange;
    public double headingchange;
    private double xpose;
    private double ypose;
    private double thetapose;
    public void turnto(double thetapose) {

    }
    public void forwardTo(double xpose) {

    }
    public void strafeTo(double ypose) {

    }
    public void pathTo(double xpose,double ypose,double thetapose) {
        //path generator here
        //- bezier curve gen here
        //- direct path gen here
        //tight turn detector here
        //colision correction here
        //motion profiling here

    }

}
