package org.firstinspires.ftc.teamcode;
import static java.lang.Math.PI;
import static java.lang.Math.acos;

import java.lang.Math.*;
public class triangle {
    public double a, b, c, x, y, z;
    public triangle(double ax, double bx, double cx){
        this.a=ax;
        this.b=bx;
        this.c=cx;

        this.x=angle(a,b,c);
        this.y=angle(b,c,a);
        this.z=angle(c,a,b);

    }
    static double angle(double a, double b, double c) {
        // From Cosine law
        double angle = (double) acos((b * b + c * c - a * a) / (2 * b * c));

        // Converting to degrees
        return (double) (angle * 180 / PI);
    }
}
