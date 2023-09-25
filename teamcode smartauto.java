package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import java.util.Vector;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.robotcore.external.android.AndroidAccelerometer;
import org.firstinspires.ftc.robotcore.external.android.AndroidGyroscope;

@Autonomous(name = "powerplayautonomous (Blocks to Java)")
public class smartautonomous extends LinearOpMode {
  private DcMotor frontleft;
  private DcMotor backleft;
  private DcMotor backright;
  private DcMotor frontright;
  private CRServo claw1;
  private CRServo claw2;
  private DcMotor liftmotor;
  private AndroidAccelerometer androidAccelerometer;
  private AndroidGyroscope androidGyroscope;
    double x_speed=0;
    double z_speed=0;
    double position_y=0;
    double position_x=0;
    public float rotation;
public void runOpMode(){
     double speed;
    int josephSpeed;
    double christianSpeed;
    boolean dpaddownwd;

    frontleft = hardwareMap.get(DcMotor.class, "front left");
    backleft = hardwareMap.get(DcMotor.class, "back left");
    backright = hardwareMap.get(DcMotor.class, "back right");
    frontright = hardwareMap.get(DcMotor.class, "front right");
    claw1 = hardwareMap.get(CRServo.class, "claw 1");
    claw2 = hardwareMap.get(CRServo.class, "claw 2");
    liftmotor = hardwareMap.get(DcMotor.class, "lift motor");
    androidAccelerometer = new AndroidAccelerometer();
    androidGyroscope = new AndroidGyroscope();
frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
    backleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    frontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    frontright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    speed = 1.25;
    sleep(2000);
    josephSpeed = 1;
    christianSpeed = 1.5;
    speed=christianSpeed;
Vector2 position=new Vector2(0,0);
  int max=0;
  float[] distances={0,0,0,0,0,0,0,0};
  Vector2[] lowbeacons={new Vector2(0,0)};//();

  lowbeacons[0].x=(float)2;lowbeacons[0].y=(float)1;
  lowbeacons[1].x=(float)1;lowbeacons[1].y=(float)2;
  lowbeacons[2].x=(float)4;lowbeacons[2].y=(float)1;
  lowbeacons[3].x=(float)5;lowbeacons[3].y=(float)2;
  lowbeacons[4].x=(float)5;lowbeacons[4].y=(float)4;
  lowbeacons[5].x=(float)1;lowbeacons[5].y=(float)4;
  lowbeacons[6].x=(float)2;lowbeacons[6].y=(float)5;
  lowbeacons[7].x=(float)4;lowbeacons[7].y=(float)5;
  Vector2 normalisedTarget=new Vector2(0,0);
 for(int i=0;i<7;i++){
    distances[i]=Vector2.magnitude(lowbeacons[i]);
 }
         for (int counter = 1; counter < distances.length; counter++)
        {
         if (distances[counter] < max)
         {
          max = counter;
          
         }

        }    
        //finished determining closest
        //calculate movement vectors:
        position.subtract(lowbeacons[max]).normalised();
        //move to closest:
        movef(lowbeacons[max].normalised().magnitude(),
        0,
        (float)Math.atan2((float)lowbeacons[max].normalised().x,(float)0),
        10);
}
private void xyz(float x){
  
}

  private void move(float speedX, float speedY, float steeringAngle) {
    if (gamepad1.right_bumper) {
      // move slowly
      frontright.setPower(((speedY + -speedX) / 2 + steeringAngle) / 3);
      backright.setPower(((speedY + speedX) / 2 + steeringAngle) / 3);
      frontleft.setPower((-((-speedY + -speedX) / 2) - steeringAngle) / 3);
      backleft.setPower(((-speedY + speedX) / 2 + steeringAngle) / 3);
    } else {
      frontright.setPower(((speedY + -speedX) / 2 + steeringAngle) * (1.2 - gamepad1.left_trigger));
      backright.setPower(((speedY + speedX) / 2 + steeringAngle) * (1.2 - gamepad1.left_trigger));
      frontleft.setPower((-((-speedY + -speedX) / 2) - steeringAngle) * (1.2 - gamepad1.left_trigger));
      backleft.setPower(((-speedY + speedX) / 2 + steeringAngle) * (1.2 - gamepad1.left_trigger));
    }
  }
    private void tracking_function() {


    x_speed = androidAccelerometer.getX() + x_speed;
    z_speed = androidAccelerometer.getZ() + z_speed;
    position_y = position_y + Math.sin(androidGyroscope.getY() / 180 * Math.PI) * z_speed;
    position_x = position_x + Math.sin(androidGyroscope.getY() / 180 * Math.PI) * x_speed;
    telemetry.addData("pos x", position_x);
    telemetry.addData("pos y", position_y);
    telemetry.update();
    }
      private void stop2() {
    frontright.setPower(0);
    backright.setPower(0);
    frontleft.setPower(0);
    backleft.setPower(0);
  }
    private void movef(float x, float y, float t, int times) {
    for (int count = 0; count < times; count++) {
      sleep(100);
      move(x, y, t);
    }
    stop2();
  }
  private void movef(Vector2 in, float t, float times) {
    for (float count = 0; count < times; count++) {
      sleep(100);
      move(in.x, in.y, t);
    }
    stop2();
  }
}
