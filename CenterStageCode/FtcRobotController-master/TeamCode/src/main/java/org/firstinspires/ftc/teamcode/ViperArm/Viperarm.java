package org.firstinspires.ftc.teamcode.ViperArm;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Configs.ArmConfig;
import org.firstinspires.ftc.teamcode.Configs.MainConfig;
import org.firstinspires.ftc.teamcode.Configs.MurderConfig;
import org.firstinspires.ftc.teamcode.Configs.ViperArmConfig;
import org.firstinspires.ftc.teamcode.RobotX;
@TeleOp(name = "ViperArm")
public class Viperarm extends RobotX {

    int armj1Offset=0,armj2Offset=0;
    float cp=0f;

    @Override
    public void initialise() {
        armj1Offset=-ViperArmConfig.armj1offset;
        armj2Offset=armj2.getCurrentPosition();
        armj4.setDirection(Servo.Direction.REVERSE);
        randommotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
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
        packet.put("Claw pos",claw.getPosition());


        double deg=((double)randommotor.getCurrentPosition())/((double)ViperArmConfig.TicksPerRevolution)*(double)360;
        packet.put("aPD",deg);
        double px=Math.cos(Math.toRadians(deg))*25;
        double py=Math.sin(Math.toRadians(deg))*25;
        double px2=Math.cos(armj4.getPosition())*25;
        double py2=Math.sin(armj4.getPosition())*25;

        packet.fieldOverlay().setStroke("green")
                .strokeLine(0,0,px,py)
                .setStroke("blue")
                .strokeLine(px,py,px+px2,py+py2);

        if(gamepad2.a){
            claw.setPosition(ViperArmConfig.clawOpenPosition);
        }
        else claw.setPosition(ViperArmConfig.clawClosedPosition);

        if(Math.abs(randommotor.getVelocity())> ArmConfig.SpeedLimit){
            randommotor.setPower(0);
        }
        else {
            if(gamepad2.left_stick_y!=0) {
                randommotor.setPower(gamepad2.left_stick_y);
            }
            else{
                randommotor.setPower(0);
            }
        }
        packet.put("delta",gamepad2.right_trigger-gamepad2.left_trigger);
        packet.put("d1",gamepad2.left_trigger);
        packet.put("d2",gamepad2.right_trigger);
        //randommotor.setPower(ViperArmConfig.getArmSegment1Power);
        if(true){
            cp+= ActivationFunction((gamepad2.right_trigger-gamepad2.left_trigger))*ViperArmConfig.speed;
            armj3.setPosition(cp);
            armj4.setPosition(cp);
        }
        armj1.setVelocity(gamepad2.right_stick_y * MurderConfig.speed);
        armj2.setVelocity(gamepad2.right_stick_y * MurderConfig.speed2);

        packet.put("ARM POS",randommotor.getVelocity());

        dashboard.sendTelemetryPacket(packet);
    }
}
