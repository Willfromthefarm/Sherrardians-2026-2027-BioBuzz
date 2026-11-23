package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class collisionsensorfusion {
    static org.firstinspires.ftc.teamcode.robothardwaremanager robot;
    static localizationhub sFCLocalization = new localizationhub(robot);
    public static char[] collisions = new char[4];
    /* COLLISIONS:

       0 - Front
       1 - Right
       2 - Left
       3 - Back

     */
    /*

        STATES:
        0 - No collision
        1 - Collision possible (<~3 in)
        2 - Collision likely (<~1 in)
        3 - Colliding (2 + stalling)

     */

    static stallsensorfusion stalldetection = new stallsensorfusion();

    public static boolean stalling = stalldetection.isAnyMotorStalling();

    public static void checkColl() {
        if (robot.distanceSensFC.getDistance(DistanceUnit.INCH) >= 2) {
            collisions[0] = 0;
        }else if (robot.distanceSensFC.getDistance(DistanceUnit.INCH) < 2) {
            if (robot.distanceSensFC.getDistance(DistanceUnit.INCH) < 1) {
                if (stalling) {
                    collisions[0] = 3;
                }else{
                    collisions[0] = 2;
                }
            }else{
                collisions[0] = 1;
            }
        }
        if (robot.distanceSensR.getDistance(DistanceUnit.INCH) >= 2) {
            collisions[1] = 0;
        }else if (robot.distanceSensR.getDistance(DistanceUnit.INCH) < 2) {
            if (robot.distanceSensR.getDistance(DistanceUnit.INCH) < 1) {
                if (stalling) {
                    collisions[1] = 3;
                }else{
                    collisions[1] = 2;
                }
            }else{
                collisions[1] = 1;
            }
        }
        if (robot.distanceSensL.getDistance(DistanceUnit.INCH) >= 2) {
            collisions[2] = 0;
        }else if (robot.distanceSensL.getDistance(DistanceUnit.INCH) < 2) {
            if (robot.distanceSensL.getDistance(DistanceUnit.INCH) < 1) {
                if (stalling) {
                    collisions[2] = 3;
                }else{
                    collisions[2] = 2;
                }
            }else{
                collisions[2] = 1;
            }
        }
        if (robot.distanceSensB.getDistance(DistanceUnit.INCH) >= 2) {
            collisions[3] = 0;
        }else if (robot.distanceSensB.getDistance(DistanceUnit.INCH) < 2) {
            if (robot.distanceSensB.getDistance(DistanceUnit.INCH) < 1) {
                if (stalling) {
                    collisions[3] = 3;
                }else{
                    collisions[3] = 2;
                }
            }else{
                collisions[3] = 1;
            }
        }

    }
}
