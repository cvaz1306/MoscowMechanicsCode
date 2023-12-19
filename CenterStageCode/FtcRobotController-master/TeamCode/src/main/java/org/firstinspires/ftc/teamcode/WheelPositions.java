package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Wheel Positions")
public class WheelPositions extends RobotX{

    @Override
    public void initialise() {
        frontleft.setTargetPosition(0);
        frontright.setTargetPosition(0);
        backleft.setTargetPosition(0);
        backright.setTargetPosition(0);

        frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontleft.setPower(.5);
        frontright.setPower(.5);
        backleft.setPower(.5);
        backright.setPower(.5);
    }

    @Override
    public void Start() {
        frontleft.setTargetPosition(0);
        frontright.setTargetPosition(0);
        backleft.setTargetPosition(0);
        backright.setTargetPosition(0);

        frontleft.setPower(.5);
        frontright.setPower(.5);
        backleft.setPower(.5);
        backright.setPower(.5);
    }

    @Override
    public void Loop() {
        TelemetryPacket packet = new TelemetryPacket();
        packet.put("Front Left", frontleft.getCurrentPosition());
        packet.put("Back Left", backleft.getCurrentPosition());
        packet.put("Front Right", frontright.getCurrentPosition());
        packet.put("Back Right", backright.getCurrentPosition());

        frontleft.setTargetPosition(0);
        frontright.setTargetPosition(0);
        backleft.setTargetPosition(0);
        backright.setTargetPosition(0);

        dashboard.sendTelemetryPacket(packet);
    }
}
