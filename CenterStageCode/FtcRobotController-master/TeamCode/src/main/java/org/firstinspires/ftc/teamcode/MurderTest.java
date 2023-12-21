package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotX;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;
@TeleOp(name="Murder Test")
public class MurderTest extends RobotX{

    @Override
    public void initialise() {

    }

    @Override
    public void Start() {

    }

    @Override
    public void Loop() {
        if(! MainConfig.IsDrivingDisabled) super.moveRobot();
        //
        // super.hookmotor.setPower(gamepad2.right_stick_y);

        if(gamepad2.right_bumper){
            randommotor.setPower(MainConfig.DroneSpeed);
        }
         else{
            randommotor.setPower(0);
        }
        TelemetryPacket packet = new TelemetryPacket();
        armj1.setPower(gamepad2.right_stick_y*MurderConfig.speed);
        armj2.setPower(gamepad2.right_stick_y*MurderConfig.speed*MurderConfig.speed2);
        packet.put("Arm Power",gamepad2.left_trigger-gamepad2.right_trigger*MurderConfig.speed);


        packet.put("Front Left", frontleft.getPower());
        packet.put("Back Left", backleft.getPower());
        packet.put("Front Right", frontright.getPower());
        packet.put("Back Right", backright.getPower());
        double x=Math.abs(frontleft.getPower());
        double y=Math.abs(backleft.getPower());
        double z=Math.abs(frontright.getPower());
        double t=Math.abs(backright.getPower());
        packet.fieldOverlay()
                        .setFill("rgb(255,255,255")
                                .fillCircle(12.5,0,x)
                                        .fillCircle(-12.5,0,y)
                                                .fillCircle(-12.5,15,z)
                                                    .fillCircle(12.5,15,t)
                                                            .setFill("rgb(100,100,100)")
                                                                    .fillRect(-12.f,15,25,15);


        dashboard.sendTelemetryPacket(packet);
    }



}
