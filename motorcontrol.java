package org.firstinspires.ftc.teamcode;
import org.firstinspires.ftc.robotcore.external.JavaUtil;
import org.firstinspires.ftc.teamcode.localizationhub;
public class motorcontrol {
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
        while(xpose != mCLocalization.currentxpose && ypose != mCLocalization.currentypose && thetapose != mCLocalization.currentheading){
            double axial = PIDclass.calculateCorrection(mCLocalization.currentypose - ypose); // the Y
            double lateral = PIDclass.calculateCorrection(mCLocalization.currentxpose - xpose); // the X
            double yaw = PIDclass.calculateCorrection(mCLocalization.currentheading - thetapose); // the angle

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

}
