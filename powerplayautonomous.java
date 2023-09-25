package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "powerplayautonomous (Blocks to Java)")
public class powerplayautonomous extends LinearOpMode {

  private DcMotor frontleft;
  private CRServo claw1;
  private DcMotor backleft;
  private DcMotor backright;
  private DcMotor frontright;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    frontleft = hardwareMap.get(DcMotor.class, "front left");
    claw1 = hardwareMap.get(CRServo.class, "claw 1");
    backleft = hardwareMap.get(DcMotor.class, "back left");
    backright = hardwareMap.get(DcMotor.class, "back right");
    frontright = hardwareMap.get(DcMotor.class, "front right");

    // Reverse one of the drive motors.
    frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
    claw1.setDirection(DcMotorSimple.Direction.REVERSE);
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        // Put loop blocks here.
        // Use left stick to drive and right stick to turn
        // The Y axis of a joystick ranges from -1 in its topmost position
        // to +1 in its bottommost position. We negate this value so that
        // the topmost position corresponds to maximum forward power.
        backleft.setPower(-gamepad1.left_stick_y + gamepad1.right_stick_x);
        backright.setPower(-gamepad1.left_stick_y - gamepad1.right_stick_x);
        telemetry.update();
      }
    }
  }

  /**
   * Describe this function...
   */
  private void movef(double x, double y, double t, double times) {
    for (int count = 0; count < times; count++) {
      sleep(100);
      move(x, y, t);
      stop2();
    }
  }

  /**
   * Describe this function...
   */
  private void stop2() {
    frontright.setPower(0);
    backright.setPower(0);
    frontleft.setPower(0);
    backleft.setPower(0);
  }

  /**
   * Describe this function...
   */
  private void move(double speedX, double speedY, double steeringAngle) {
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
}
