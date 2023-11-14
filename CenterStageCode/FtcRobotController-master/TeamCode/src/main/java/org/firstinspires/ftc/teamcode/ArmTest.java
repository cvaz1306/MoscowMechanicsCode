package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotX;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

// ...


@TeleOp(name="Arm Test")
public class ArmTest extends RobotX{

    @Override public void Start(){
        armj1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armj2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
    // todo: write your code here
    @Override public void initialise(){

    }
    @Override public void Loop(){
            armj1.setPower((gamepad2.left_stick_y)*MainConfig.ArmJ1Power);

            armj2.setPower((gamepad2.right_stick_y)*MainConfig.ArmJ1Power);

    }
}