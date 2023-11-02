package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotX;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

// ...


@TeleOp(name="Arm Test")
public class ArmTest extends RobotX{

    @Override public void Start(){

    }
    // todo: write your code here
    @Override public void initialise(){

    }
    @Override public void Loop(){
        if(gamepad1.a){
            armj1.setPower((gamepad1.right_trigger-gamepad1.left_trigger)*3);
        }
        if(gamepad1.b){
            armj2.setPower((gamepad1.right_trigger-gamepad1.left_trigger)*3);
        }
    }
}