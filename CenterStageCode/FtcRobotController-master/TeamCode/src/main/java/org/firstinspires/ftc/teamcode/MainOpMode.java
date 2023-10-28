package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotX;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

// ...


@TeleOp(name="TO-Compact")
public class MainOpMode extends RobotX{
    private boolean isBraked;



    // todo: write your code here
    @Override public void initialise(){

    }
    @Override public void Loop(){
        if(! MainConfig.IsDrivingDisabled) super.moveRobot();
        super.randommotor.setPower(gamepad2.right_trigger-gamepad2.left_trigger);
        isBraked=!isBraked;


        if(isBraked){
            backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
        else{
            backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        if(gamepad2.left_trigger!=(float)MainConfig.LTTG2X){
            hookmotor.setPower(MainConfig.LiftPower*2*(gamepad2.left_trigger-.5));
        }
        else{
            hookmotor.setPower(0);
        }

        if(gamepad2.right_bumper){
            randommotor.setPower(MainConfig.DroneSpeed);
        }
        else{
            randommotor.setPower(0);
        }
    }
}