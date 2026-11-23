package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.JavaUtil;

@TeleOp(name = "comp")
public class comp extends LinearOpMode {

    private Servo turretangle;
    private Servo turretservo;
    private DcMotor Launcher;
    private DcMotor Launcher2;
    private DcMotor frontleft;
    private DcMotor frontright;
    private DcMotor backleft;
    private DcMotor backright;
    private CRServo bottomtrans;
    private CRServo middletrans;
    private CRServo toptrans;
    private DcMotor intake;
    double frontLeftPower;
    double backLeftPower;
    double frontRightPower;
    double backRightPower;
    private CRServo tiptoptrans;

    /**
     * This sample contains the bare minimum Blocks for any regular
     OpMode. The 3 blue
     * Comment Blocks show where to place Initialization code (runs
     once, after touching the
     * DS INIT button, and before touching the DS Start arrow), Run code
     (runs once, after
     * touching Start), and Loop code (runs repeatedly while the OpMode
     is active, namely not
     * Stopped).
     */
    @Override
    public void runOpMode() {
        turretangle = hardwareMap.get(Servo.class, "turretangle");
        turretservo = hardwareMap.get(Servo.class, "turretservo");
        Launcher = hardwareMap.get(DcMotor.class, "Launcher");
        Launcher2 = hardwareMap.get(DcMotor.class, "Launcher 2");
        bottomtrans = hardwareMap.get(CRServo.class, "bTrans");
        middletrans = hardwareMap.get(CRServo.class, "mTrans");
        toptrans = hardwareMap.get(CRServo.class, "tTrans");
        intake = hardwareMap.get(DcMotor.class, "intake");
        tiptoptrans = hardwareMap.get(CRServo.class, "tTTrans");

        ElapsedTime runtime;
        float axial;
        float lateral;
        float yaw;
        double max;

        frontleft = hardwareMap.get(DcMotor.class, "frontleft");
        frontright = hardwareMap.get(DcMotor.class, "frontright");
        backleft = hardwareMap.get(DcMotor.class, "backleft");
        backright = hardwareMap.get(DcMotor.class, "backright");
        camera autoaim = new camera();

        runtime = new ElapsedTime();
        // ########################################################################################
        // !!! IMPORTANT Drive Information. Test your motor directions. !!!!!
        // ########################################################################################
        //
        // Most robots need the motors on one side to be reversed to drive forward.
        // The motor reversals shown here are for a "direct drive" robot
        // (the wheels turn the same direction as the motor shaft).
        //
        // If your robot has additional gear reductions or uses a
        //right-angled drive, it's important to ensure
        // that your motors are turning in the correct direction. So,
        //start out with the reversals here, BUT
        // when you first test your robot, push the left joystick forward
       // and observe the direction the wheels turn.
                //
                // Reverse the direction (flip FORWARD <-> REVERSE ) of any wheel
               // that runs backward.
        // Keep testing until ALL the wheels move the robot forward when
        //you push the left joystick forward.
        // <--- Click blue icon to see important note re. testing motor directions.
        frontleft.setDirection(DcMotor.Direction.REVERSE);
        frontright.setDirection(DcMotor.Direction.FORWARD);
        backleft.setDirection(DcMotor.Direction.REVERSE);
        backright.setDirection(DcMotor.Direction.FORWARD);
        // Wait for the game to start (driver presses START)
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        runtime.reset();
        // Put initialization blocks here.
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                // Put loop blocks here.
                if (gamepad2.b) {
                    turretangle.setPosition(0.2);
                }
                if (gamepad2.a) {
                    turretangle.setPosition(0.75);
                }
                if (gamepad2.dpad_up) {
                    turretservo.setPosition(50 / 300);
                }
                if (gamepad2.dpad_down) {
                    turretservo.setPosition(0 / 300);
                }
                if (gamepad2.dpad_left) {
                    turretservo.setPosition(75 / 300);
                }
                ((DcMotorEx) Launcher).setVelocity(1200);
                ((DcMotorEx) Launcher2).setVelocity(1200);
                if (gamepad1.a){
                    bottomtrans.setPower(-1);
                    middletrans.setPower(1);
                    toptrans.setPower(-1);
                    tiptoptrans.setPower(1);

                }else if(gamepad1.b){
                    bottomtrans.setPower(1);
                    middletrans.setPower(-1);
                    toptrans.setPower(1);
                    tiptoptrans.setPower(-1);
                }else{
                    bottomtrans.setPower(0);
                    middletrans.setPower(0);
                    toptrans.setPower(0);
                    tiptoptrans.setPower(0);
                }
                intake.setPower(gamepad1.right_trigger);

                // POV Mode uses left joystick to go forward & strafe, and
                //right joystick to rotate.
                // Note: pushing stick forward gives negative value
                axial = -gamepad1.left_stick_y;
                lateral = gamepad1.left_stick_x;
                if (gamepad1.dpad_left) {
                    autoaim.operate();
                yaw = autoaim.autocorrection;
                }else {
                    yaw = gamepad1.right_stick_x;
                }
                // Combine the joystick requests for each axis-motion to
                //determine each wheel's power.
                // Set up a variable for each drive wheel to save the power
                //level for telemetry.
                        frontLeftPower = axial + lateral + yaw;
                frontRightPower = axial - lateral - yaw;
                backLeftPower = axial - lateral + yaw;
                backRightPower = axial + lateral - yaw;
                // Normalize the values so no wheel power exceeds 100%
                // This ensures that the robot maintains the desired motion.
                max = JavaUtil.maxOfList(JavaUtil.createListWith(Math.abs(frontLeftPower),
                        Math.abs(frontRightPower), Math.abs(backLeftPower),
                        Math.abs(backRightPower)));
                if (max > 1) {
                    frontLeftPower = frontLeftPower / max;
                    frontRightPower = frontRightPower / max;
                    backLeftPower = backLeftPower / max;
                    backRightPower = backRightPower / max;
                }
                // Send calculated power to wheels.
                frontleft.setPower(frontLeftPower);
                frontright.setPower(frontRightPower);
                backleft.setPower(backLeftPower);
                backright.setPower(backRightPower);
                // Show the elapsed game time and wheel power.
                telemetry.addData("Status", "Run Time: " + runtime);
                telemetry.addData("Front left/Right",
                        JavaUtil.formatNumber(frontLeftPower, 4, 2) + ", " +
                                JavaUtil.formatNumber(frontRightPower, 4, 2));
                telemetry.addData("Back  left/Right",
                        JavaUtil.formatNumber(backLeftPower, 4, 2) + ", " +
                                JavaUtil.formatNumber(backRightPower, 4, 2));
                telemetry.update();
            }
        }
    }
}
