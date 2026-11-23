package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "comp")
public class mangr extends LinearOpMode { //I'M MANGR.

    public static boolean autoalignmode = false;
    public static boolean robotautoalignmode = false;

    @Override
    public void runOpMode(){
        while(opModeIsActive()){
            if(gamepad1.dpad_up){
                autoalignmode = !autoalignmode;
                telemetry.addData("Auto-Alignment (Turret)", autoalignmode);
                sleep(500);
            }
            if(gamepad1.dpad_down){ //another button for now
                robotautoalignmode = !robotautoalignmode;
                telemetry.addData("Auto-Alignment (Robot)", autoalignmode);
                sleep(500);
            }
        }
    }
}
