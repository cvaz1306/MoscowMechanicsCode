package org.firstinspires.ftc.teamcode;

public class Segment {
    public int time;
    public double xS;
    public double yS;

    public double rotS;
    public Segment(double x, double y, double rot, int time){
        xS=x;
        yS=y;
        rotS=rot;
        this.time=time;
    }
}
