package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Utility;

import org.firstinspires.ftc.robotcore.external.JavaUtil;

@Utility(name="red comp")
public class motorcontrolR extends OpMode {
    PIDclass aPID = new PIDclass(0.002, 0, 1); //we'll figure kD out when we get there (see motorcontrolB.java)
    static PIDclass mPID = new PIDclass(1, 1, 1); //we'll figure it all out when we get there
    static org.firstinspires.ftc.teamcode.robothardwaremanager robot;
    static localizationhub mCLocalization = new localizationhub(robot);
    public char xchange;
    public char ychange;
    public double headingchange;
    private double xpose;
    private double ypose;
    private double thetapose;
    public void motorcontroller() {

    }
    public static void Driveto(double xpose, double ypose, double thetapose) {
        while (xpose != mCLocalization.currentxpose && ypose != mCLocalization.currentypose && thetapose != mCLocalization.currentheading) {
            double axial = mPID.calculateCorrection(mCLocalization.currentypose - ypose); // the Y
            double lateral = mPID.calculateCorrection(mCLocalization.currentxpose - xpose); // the X
            double yaw = mPID.calculateCorrection(mCLocalization.currentheading - thetapose); // the angle

            // motor power calculations
            double frontLeftPower = axial + lateral + yaw;
            double frontRightPower = (axial - lateral) - yaw;
            double backLeftPower = (axial - lateral) + yaw;
            double backRightPower = (axial + lateral) - yaw;

            double max = JavaUtil.maxOfList(JavaUtil.createListWith(Math.abs(frontLeftPower), Math.abs(frontRightPower), Math.abs(backLeftPower), Math.abs(backRightPower)));
            if (max > 1) {
                frontLeftPower = (float) (frontLeftPower / max);
                frontRightPower = (float) (frontRightPower / max);
                backLeftPower = (float) (backLeftPower / max);
                backRightPower = (float) (backRightPower / max);
            }

            robot.frontLeft.setPower(frontLeftPower);
            robot.frontRight.setPower(frontRightPower);
            robot.backLeft.setPower(backLeftPower);
            robot.backRight.setPower(backRightPower);
        }
    }
    public void collisionReaction() {
        collisionsensorfusion.checkColl();
        if(collisionsensorfusion.collisions[0] == 2 || collisionsensorfusion.collisions[1] == 2 || collisionsensorfusion.collisions[2] == 2 || collisionsensorfusion.collisions[3] == 2){
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);
        }else if(collisionsensorfusion.collisions[0] == 3){
            Driveto(mCLocalization.currentxpose, mCLocalization.currentypose, mCLocalization.currentheading+3.1415926535897932384); //that's enough digits i hope
            robot.frontLeft.setPower(-1);
            robot.frontRight.setPower(1);
            robot.backLeft.setPower(-1);
            robot.backRight.setPower(1);
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                String exception; new RuntimeException(e);
            }
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);
        }

    }
    public void aimrobot(){
        if(turretandcameracontrolsR.currentDetections.isEmpty()){
            turretandcameracontrolsR.scan();
        }
        if(turretandcameracontrolsR.isDetectionFacingUp){
            while(turretandcameracontrolsR.targetB != mCLocalization.currentheading) {
                robot.turretservo.setPosition(turretandcameracontrolsR.degreestoturretservoposition(aPID.calculateCorrection(turretandcameracontrolsR.targetB))); //turns the robot to match the bearing it detects of a target tag cluster, so it points at the cluster. read it and weep, will
                turretandcameracontrolsR.operate();
            }
        }
    }


    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}
