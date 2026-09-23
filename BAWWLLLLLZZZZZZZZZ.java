
package org.firstinspires.ftc.teamcode.auto;

public class ballsensoring {
  robothardwaremanager robot;
  public char leftCS;
  public char rightCS;
  public char launch;
  public char intake;
  public char prevleftCS;
  public char prevrightCS;
  public char prevlaunch;
  public char previntake;
  private void shiftleft() {
    prevleftCS = leftCS;
    prevrightCS = rightCS;
    prevlaunch = launch;
    previntake = intake;
    launch = prevleftCS;
    rightCS = prevlaunch;
    intake = prevrightCS;
    leftCS = previntake;
  }
  private void shiftright() {
    prevleftCS = leftCS;
    prevrightCS = rightCS;
    prevlaunch = launch;
    previntake = intake;
    intake = prevleftCS;
    rightCS = previntake;
    launch = prevrightCS;
    leftCS = prevlaunch;
  }
}
