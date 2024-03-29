package org.firstinspires.ftc.teamcode;

public class CustomGeometry {
    public static double angle(double a, double b, double c) {
        return Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) / (2 * a * b));
    }
    public static double clamp(double x,double max, double min){
        return Math.max(min,Math.min(x,max));
    }
}
