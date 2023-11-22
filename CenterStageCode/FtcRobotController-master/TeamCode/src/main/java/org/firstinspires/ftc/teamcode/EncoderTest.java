package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Encoder test")
public class EncoderTest extends RobotX{

    @Override
    public void initialise() {

    }

    @Override
    public void Start() {

    }

    @Override

    public void Loop() {
        telemetry.addData("Encoder 1 pos", armj1.getCurrentPosition()/2);
        telemetry.addData("Encoder 2 pos", armj2.getCurrentPosition()/2);
        telemetry.update();
    }
}
