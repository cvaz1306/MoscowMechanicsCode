package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Configs.MainConfig;

import com.qualcomm.robotcore.hardware.DcMotor;

// ...


@TeleOp(name="TO-Compact")
public class MainOpMode extends RobotX{
    private boolean isBraked;


    @Override public void Start(){
        armj1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armj2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    // todo: write your code here
    @Override public void initialise(){

    }
    @Override public void Loop(){
        if(! MainConfig.IsDrivingDisabled) super.moveRobot();

        super.drone.setPower(gamepad2.right_trigger-gamepad2.left_trigger);
        isBraked=!isBraked;


        if(gamepad2.right_bumper){
            randommotor.setPower(MainConfig.DroneSpeed);
        }
        else{
            randommotor.setPower(0);
        }
        TelemetryPacket packet = new TelemetryPacket();


        dashboard.sendTelemetryPacket(packet);
    }
}