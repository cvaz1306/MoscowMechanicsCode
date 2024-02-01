package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotX;
@TeleOp(name = "ServoTest")
public class Servos extends RobotX {
    @Override
    public void initialise() {

    }

    @Override
    public void Start() {

    }

    @Override
    public void Loop() {
        armj3.setPosition(0);
        armj4.setPosition(0);
    }
}
