package org.firstinspires.ftc.teamcode.auto;
// DISCLAIMER!
//This is all generated using ChatGPT 

public class PIDclass {

    private double kP;
    private double kI;
    private double kD;

    private double integral;
    private double previousError;

    private long previousTime;

    private double integralLimit = 1.0;

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

    public double calculate(
            double target,
            double current
    ) {

        double error = target - current;

        return calculateError(error);
    }

    public double calculateError(double error) {

        long now = System.nanoTime();

        double dt =
                (now - previousTime) / 1_000_000_000.0;

        previousTime = now;

        if (dt <= 0 || dt > 0.1) {
            dt = 0.01;
        }

        integral += error * dt;

        // Prevent integral windup
        integral = Math.max(
                -integralLimit,
                Math.min(integralLimit, integral)
        );

        double derivative =
                (error - previousError) / dt;

        previousError = error;

        return
                kP * error +
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

