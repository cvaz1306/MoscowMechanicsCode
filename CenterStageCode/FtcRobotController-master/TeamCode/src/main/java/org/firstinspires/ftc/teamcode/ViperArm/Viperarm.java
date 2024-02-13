package org.firstinspires.ftc.teamcode.ViperArm;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Configs.MainConfig;
import org.firstinspires.ftc.teamcode.Configs.MurderConfig;
import org.firstinspires.ftc.teamcode.Configs.ViperArmConfig;
import org.firstinspires.ftc.teamcode.MurderTest;
import org.firstinspires.ftc.teamcode.RobotX;
@TeleOp(name = "ViperArm")
public class Viperarm extends RobotX {

    int armj1Offset=0,armj2Offset=0;
    @Override
    public void initialise() {
        armj1Offset=-ViperArmConfig.armj1offset;
        armj2Offset=armj2.getCurrentPosition();
        armj4.setDirection(Servo.Direction.REVERSE);
    }

    @Override
    public void Start() {

    }

    @Override
    public void Loop() {
        super.moveRobot();
        if(gamepad2.right_bumper&&getRuntime()>ViperArmConfig.droneDelay){
            drone.setPower(MainConfig.DroneSpeed);
        }
        else{
            drone.setPower(0);
        }
        TelemetryPacket packet=new TelemetryPacket();
        packet.put("Claw Position",claw.getPosition());
        packet.put("armj1",armj1.getCurrentPosition());
        packet.put("armj2",-armj2.getCurrentPosition());
        packet.put("Arm Pos",randommotor.getCurrentPosition());
        if(gamepad2.a){
            claw.setPosition(ViperArmConfig.clawOpenPosition);
        }

        else claw.setPosition(ViperArmConfig.clawClosedPosition);

        if(Math.abs(randommotor.getVelocity())>300){
            randommotor.setPower(randommotor.getPower()+((randommotor.getVelocity()/Math.abs(randommotor.getVelocity()))/16));
        }
        else {
            if(gamepad2.left_stick_y!=0) {
                randommotor.setPower(gamepad2.left_stick_y);
            }
            else{
                randommotor.setPower((randommotor.getVelocity()/Math.abs(randommotor.getVelocity()))/-16);
            }
        }

        //randommotor.setPower(ViperArmConfig.getArmSegment1Power);
        armj3.setPosition(gamepad2.right_trigger-gamepad2.left_trigger);
        armj4.setPosition((gamepad2.right_trigger-gamepad2.left_trigger));
        armj1.setVelocity(gamepad2.right_stick_y * MurderConfig.speed);
        armj2.setVelocity(gamepad2.right_stick_y * MurderConfig.speed2);

        packet.put("ARM POS",randommotor.getVelocity());

        dashboard.sendTelemetryPacket(packet);
    }
}
