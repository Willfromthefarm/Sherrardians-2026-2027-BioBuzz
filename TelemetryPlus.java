// ENTER THE SURVIVAL HORROR.

package org.firstinspires.ftc.teamcode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

// wait. it's too scary. no, STOP-
public class TelemetryPlus { // OBJECTIVE: simple functions to make telemetry printing n' all that garbage (affectionately) marginally less tedious



    public void qprint(String yourstrhere) { // qprint, for quick print

        // come on man do i really need to say what this does? sighhhhhhhhhhhhhhh it's meant to be a 1-line solution to printing a single line of text to the telemetry, for if you need to log an action or what-have-you


        // in retrospect that seems kinda silly but it's a surprise tool that could help you later i guess
        telemetry.addLine("yourstrhere");
        telemetry.update();

    }

    //i just realized the showplace function i laid out is made redundant by the command center already printing out the x, y, and heading 



}

// EXIT THE SURVIVAL HORROR.
