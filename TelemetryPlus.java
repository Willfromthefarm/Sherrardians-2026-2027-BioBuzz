// ENTER THE SURVIVAL HORROR.

package org.firstinspires.ftc.teamcode.auto; // i think this is all i need (i think)
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode; // and now here i sit with egg all over my face
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit; // i'm gonna bring in the angle things from the 25-26 project
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

// wait, it's too scary, no, STOP-

public class telemetryplus extends LinearOpMode { // OBJECTIVE: simple functions to make telemetry printing n' all that garbage (affectionately) marginally less tedious

  private GoBildaPinpointDriver odo; // i yoinked this from the 25-26 codebase hehehehe, anyways here's the odometry init from 25-26 that someone smarter than me can go tweaking at
  odo = hardwareMap.get(GoBildaPinpointDriver.class, "odo");
  odo.setOffsets(-84.0, -168.0, DistanceUnit.MM); // ADJUST TO YOUR ROBOT'S OFFSETS (mm)
  odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
  odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);
  odo.resetPosAndIMU();


  
  public void printtotelem(yourstrhere) {

    // come on man do i really need to say what this does? sighhhhhhhhhhhhhhh it's meant to be a 1-line solution to printing a single line of text to the telemetry


    // in retrospect that seems kinda silly but it's a surprise tool that could help you later i guess
    telemetry.addLine(yourstrhere);
    telemetry.update();
    
    
  }

  public void showlocationdata() {
    
    // print all of the odometry/location data stuff (x, y, and heading) - probably useful for debugging stuff or whatever
    
    odo.update();
    double currentX = odo.getPosX(DistanceUnit.MM);
    double currentY = odo.getPosY(DistanceUnit.MM);
    double currentHeading = odo.getHeading(AngleUnit.DEGREES);
    telemetry.addData("Current Y", "%.1f", currentY);
    telemetry.addData("Current X", "%.1f", currentX);
    telemetry.addData("Current Heading", "%.1f", currentHeading);
  }
}

// EXIT THE SURVIVAL HORROR.
