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
    public void Driveto(double xpose,double ypose,double thetapose) {
        while(xpose != currentX && ypose != currentY && thetapose != currentHeading){
            axial = PIDclass.calculate(currentY, ypose); // the Y
            lateral = PIDclass.calculate(currentX, xpose); // the X
            yaw = PIDclass.calculate(currentHeading, thetapose); // the angle
            
            // motor power calculations
            frontLeftPower = axial + lateral + yaw;
            frontRightPower = (axial - lateral) - yaw;
            backLeftPower = (axial - lateral) + yaw;
            backRightPower = (axial + lateral) - yaw;
          
          max = JavaUtil.maxOfList(JavaUtil.createListWith(Math.abs(frontLeftPower), Math.abs(frontRightPower), Math.abs(backLeftPower), Math.abs(backRightPower)));
          if (max > 1) {
            frontLeftPower = (float) (frontLeftPower / max);
            frontRightPower = (float) (frontRightPower / max);
            backLeftPower = (float) (backLeftPower / max);
            backRightPower = (float) (backRightPower / max);
          }
          
            motor1.setPower(frontLeftPower);
            motor3.setPower(frontRightPower);
            motor2.setPower(backLeftPower);
            motor4.setPower(backRightPower);
        }
    }

}

}
