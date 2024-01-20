package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotX;
@TeleOp(name = "Motor Test")
public class MotorTest extends RobotX {
    @Override
    public void initialise() {

    }

    @Override
    public void Start() {

    }

    @Override
    public void Loop() {
        if(gamepad1.y){
            frontright.setPower(.5);
        }
        else {
            frontright.setPower(0);
        }
        if(gamepad1.x){
            frontleft.setPower(.5);
        }
        else {
            frontleft.setPower(0);
        }
        if(gamepad1.a){
            backleft.setPower(.5);
        }
        else {
            backleft.setPower(0);
        }
        if(gamepad1.b){
            backright.setPower(.5);
        }
        else {
            backright.setPower(0);
        }
    }
}
