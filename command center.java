package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

// this code demonstrates the advanced decision making process of the Sherrardian Autonomous

// can we call it Authonomas Thomas

    @Autonomous(name="CommandAuto", group="Robot")
    //@Disabled
    public class commandcenter extends LinearOpMode {
        //nevermind, the motors will be set up in another program evidently

        robothardwaremanager robot = new robothardwaremanager();
        @Override
        public void runOpMode() {
            public void pathtodestination(double destx, double desty, double desth) { // i want that thing
            
                Beziergen.Beziergen(destx, desty, desth); // find path to thing
                if (pathQueue.isFull();) {
                    while (pathQueue.isEmpty() == false) {
                        
                        public utilityclass.coordsandangle nextdestination = utility.new coordsandangle(Beziergen.nextPoint()); // i think this is how it works
                        motorcontroller.Driveto(nextdestination.xCoord, nextdestination.yCoord, nextdestination.hdng); // go get thing
                        
                    }
                } else { // if the pathqueue is full after the curve is generated, it shouldn't have aborted in the middle due to a curve that's too steep, so the curve should be valid unless the queue isn't full with all 201 points

                    motorcontroller.Driveto(destx, desty, desth)
                    
                }
                
            }
            robot.init(hardwareMap);

            localizationhub localization = new localizationhub(robot);



            waitForStart();
            //do fun things
            localization.updatePosition();
            telemetry.addData("X", localization.currentxpose);
            telemetry.addData("Y", localization.currentypose);
            telemetry.addData("Heading", localization.currentheading);
            telemetry.update();


            }
        }
    

