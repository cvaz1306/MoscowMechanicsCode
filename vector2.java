package org.firstinspires.ftc.teamcode;
import java.lang.Math;
import java.util.Vector;

public class Vector2{
    static float x;
  static float y;
  public Vector2(float x,float y){
   x=x;
   y=y;
  }


  public static float magnitude(float x, float y){
    return (float)Math.sqrt((x*x)+(y*y));
  }
  public static float magnitude(Vector2 in){
    return (float)Math.sqrt((in.x*in.x)+(in.y*in.y));
  }
    public static float magnitude(){
    return (float)Math.sqrt((x*x)+(y*y));
  }
  public static Vector2 normalised(){
    float mag=Vector2.magnitude(new Vector2(x,y));
    float pro=1/mag;
    return new Vector2(x*pro, y*pro);
  }
  public static Vector2 subtract(Vector2 in2){

     return new Vector2(x-in2.x,y-in2.y);
  }
}