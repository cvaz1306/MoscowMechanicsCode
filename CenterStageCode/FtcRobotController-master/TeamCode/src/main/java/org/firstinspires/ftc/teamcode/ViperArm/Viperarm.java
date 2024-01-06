package org.firstinspires.ftc.teamcode.ViperArm;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Configs.MurderConfig;
import org.firstinspires.ftc.teamcode.Configs.ViperArmConfig;
import org.firstinspires.ftc.teamcode.MurderTest;
import org.firstinspires.ftc.teamcode.RobotX;
@TeleOp(name = "ViperArm")
public class Viperarm extends RobotX {

    @Override
    public void initialise() {

    }

    @Override
    public void Start() {

    }

    @Override
    public void Loop() {
        TelemetryPacket packet=new TelemetryPacket();
        packet.put("Claw Position",claw.getPosition());
        claw.setPosition((double) ViperArmConfig.targetPosition);
        claw.setDirection(Servo.Direction.FORWARD);
        packet.put("armj1",armj1.getCurrentPosition());
        packet.put("armj2",-armj2.getCurrentPosition());

        armj1.setPower(gamepad2.right_stick_y* MurderConfig.speed);
        armj2.setPower(gamepad2.right_stick_y*MurderConfig.speed*MurderConfig.speed2);


        dashboard.sendTelemetryPacket(packet);
    }
}
