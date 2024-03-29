package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Configs.MainConfig;
import org.firstinspires.ftc.teamcode.Configs.MurderConfig;
import org.firstinspires.ftc.teamcode.Configs.ViperArmConfig;

@TeleOp(name="Murder Test")
public class MurderTest extends RobotX{

    int armj1Offset=0,armj2Offset=0;
    @Override
    public void initialise() {
        armj1Offset=-ViperArmConfig.armj1offset;
        armj2Offset=armj2.getCurrentPosition();
    }

    @Override
    public void Start() {

    }

    @Override
    public void Loop() {
        super.moveRobot();
        //
        // super.hookmotor.setPower(gamepad2.right_stick_y);

        if(gamepad2.right_bumper){
            randommotor.setPower(MainConfig.DroneSpeed);
        }
         else{
            randommotor.setPower(0);
        }
        TelemetryPacket packet = new TelemetryPacket();
        armj1.setPower(gamepad2.right_stick_y* MurderConfig.speed);
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



        packet.put("Claw Position",claw.getPosition());
        packet.put("armj1",armj1.getCurrentPosition());
        packet.put("armj2",-armj2.getCurrentPosition());
//        if(gamepad2.a){
//            claw.setPosition(ViperArmConfig.clawOpenPosition);
//        }
//        else claw.setPosition(ViperArmConfig.clawClosedPosition);

//        if(gamepad2.b) armj3.setPosition(ViperArmConfig.armj3TargetPosition);
        dashboard.sendTelemetryPacket(packet);

    }



}
