package org.firstinspires.ftc.teamcode;

public class controls {
    public void pathtodestination(double destx,double desty,double desth) {

        new Beziergen(destx, desty, desth); // find path to thing
        if (Beziergen.full()) {
            while (!Beziergen.empty()) {

                utilityclass.coordsandangle destination = new utilityclass.coordsandangle(Beziergen.nextPoint()); // i think this is how it works
                motorcontrol.Driveto(destination.getXCoord(), destination.getYCoord(), destination.getHdng()); // go get thing

            }
        } else { // if the pathqueue is full after the curve is generated, it shouldn't have aborted in the middle due to a curve that's too steep, so the curve should be valid unless the queue isn't full of all 201 points

            motorcontrol.Driveto(destx, desty, desth);

        }

    }
}
