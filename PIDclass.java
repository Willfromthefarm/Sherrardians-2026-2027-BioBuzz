package org.firstinspires.ftc.teamcode;
// DISCLAIMER!
//This is all generated using ChatGPT
// SECOND DISCLAIMER!
//Copy-pasted directly from Will's branch with annotations and some refactoring to prevent collisions
public class PIDclass {

    private static double kP;
    private static double kI;
    private static double kD;

    private static double integral;
    private static double previousError;

    private static long previousTime;

    private static double integralLimit = 1.0;

    public PIDclass(
            double kP,
            double kI,
            double kD
    ) {

        this.kP = kP;
        this.kI = kI;
        this.kD = kD;

        previousTime = System.nanoTime();
    }

    public static double calculate(
            double target,
            double current
    ) {

        double error = target - current;

        return calculateCorrection(error); //Returns error using a target number and the current number
    }

    public static double calculateCorrection(double iError) { //Returns necessary correction

        long now = System.nanoTime();

        double dt =
                (now - previousTime) / 1_000_000_000.0;

        previousTime = now;

        if (dt <= 0 || dt > 0.1) {
            dt = 0.01;
        }

        integral += iError * dt;

        // Prevent integral windup
        integral = Math.max(
                -integralLimit,
                Math.min(integralLimit, integral)
        );

        double derivative =
                (iError - previousError) / dt;

        previousError = iError;

        return
                kP * iError +
                        kI * integral +
                        kD * derivative;
    }

    public void reset() {

        integral = 0;
        previousError = 0;
        previousTime = System.nanoTime();
    }

    public void setIntegralLimit(double limit) {
        integralLimit = limit;
    }
}
