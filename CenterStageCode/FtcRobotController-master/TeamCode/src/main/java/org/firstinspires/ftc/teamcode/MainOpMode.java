package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotX;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

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
        super.hookmotor.setPower(gamepad2.right_trigger-gamepad2.left_trigger);
        isBraked=!isBraked;


        if(gamepad2.right_bumper){
            randommotor.setPower(MainConfig.DroneSpeed);
        }
        else{
            randommotor.setPower(0);
        }
        if(gamepad2.a) armj1.setPower((gamepad2.right_trigger-gamepad2.left_trigger)*2*MainConfig.LiftPower);


        armj1.setPower((gamepad2.left_stick_y)*3);

        armj2.setPower((gamepad2.right_stick_y)*3);

    }
}