/*
    WINDMILL BALL CHAR GUIDE:
    1 = Red nectar
    2 = Blue nectar
    3 = Pollen
    0 = Empty
 */
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.NormalizedRGBA;

public class ballmanager {
    org.firstinspires.ftc.teamcode.robothardwaremanager robot = new org.firstinspires.ftc.teamcode.robothardwaremanager();
    public char leftCS;
    public char rightCS;
    public char launch;
    public char intake;
    public char prevleftCS;
    public char prevrightCS;
    public char prevlaunch;
    public char previntake;
    public double acceptedError = 10;
    private void shiftleft() {
        if((robot.storageservo.getPosition()+0.2) < 0){
            robot.storageservo.setPosition(1);
        }
        robot.storageservo.setPosition(robot.storageservo.getPosition()+0.05);
        prevleftCS = leftCS;
        prevrightCS = rightCS;
        prevlaunch = launch;
        previntake = intake;
        launch = prevleftCS;
        rightCS = prevlaunch;
        intake = prevrightCS;
        leftCS = previntake;
    }
    private void shiftright() {
        if((robot.storageservo.getPosition()-0.2) > 1){
            robot.storageservo.setPosition(0);
        }
        robot.storageservo.setPosition(robot.storageservo.getPosition()-0.05);
        prevleftCS = leftCS;
        prevrightCS = rightCS;
        prevlaunch = launch;
        previntake = intake;
        intake = prevleftCS;
        launch = prevrightCS;
    }
    public void senseballs() {
        NormalizedRGBA colorInLeftCS = robot.ballSensorL.getNormalizedColors();
        float[] lcs = new float[3];
        android.graphics.Color.colorToHSV(colorInLeftCS.toColor(), lcs);
        NormalizedRGBA colorInRightCS = robot.ballSensorR.getNormalizedColors();
        float[] rcs = new float[3];
        android.graphics.Color.colorToHSV(colorInRightCS.toColor(), rcs);
        if(rcs[0] > 355 - acceptedError || rcs[0] > 0 - acceptedError){
            rightCS = 1; // Red Nectar
        } else if (rcs[0] < 240 + acceptedError && rcs[0] > 240 - acceptedError){
            rightCS = 2; // Blue Nectar
        } else if (rcs[0] < 50 + acceptedError && rcs[0] > 50 - acceptedError){
            rightCS = 3; // Pollen
        } else {
            rightCS = 0; // Nothing
        }
    }
    public void nectarRtoFire() {
        if(rightCS == 1){
            shiftright();
        }
        if(leftCS == 1) {
            shiftleft();
        }
        if(intake == 1) {
            shiftleft();
            shiftleft();
        }
    }
    public void nectarBtoFire() {
        if(rightCS == 2){
            shiftright();
        }
        if(leftCS == 2) {
            shiftleft();
        }
        if(intake == 2) {
            shiftleft();
            shiftleft();
        }
    }
    public void pollentoFire() {
        if(rightCS == 3){
            shiftright();
        }
        if(leftCS == 3) {
            shiftleft();
        }
        if(intake == 3) {
            shiftleft();
            shiftleft();
        }
    }
    public void clearintake() {

        if(rightCS == 0){
            shiftleft();
        }
        if(leftCS == 0) {
            shiftright();
        }
        if(launch == 0) {
            shiftleft();
            shiftleft();
        }
        
    }
}
