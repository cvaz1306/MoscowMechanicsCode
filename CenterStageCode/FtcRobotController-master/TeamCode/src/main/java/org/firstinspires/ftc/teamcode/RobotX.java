package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Configs.MainConfig;
import org.firstinspires.ftc.teamcode.backend.Getter;
import org.firstinspires.ftc.teamcode.backend.ExtObj;

import java.util.ArrayList;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;

public abstract class RobotX extends LinearOpMode{



    public  DcMotor backleft;
    public DcMotor backright;
    public CRServo camservo;
    public Servo claw;
    public DcMotor frontleft;
    public DcMotor frontright;
    public DcMotorEx randommotor;
    public DcMotorEx drone;
    public DcMotorEx armj1;
    public DcMotorEx armj2;
    public Servo armj3;
    public Servo armj4;

    public FtcDashboard dashboard = FtcDashboard.getInstance();

    public void Init(){
        //init motors
        frontright=hardwareMap.get(DcMotor.class, "front right");
        backright=hardwareMap.get(DcMotor.class, "back right");
        frontleft=hardwareMap.get(DcMotor.class, "front left");
        backleft=hardwareMap.get(DcMotor.class, "back left");
        randommotor=hardwareMap.get(DcMotorEx.class, "notso motor");
        drone =hardwareMap.get(DcMotorEx.class, "drone");
        armj1=hardwareMap.get(DcMotorEx.class, "arm j1");
        armj2=hardwareMap.get(DcMotorEx.class, "arm j2");
        armj1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armj2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armj3=hardwareMap.get(Servo.class,"armj3");
        armj4=hardwareMap.get(Servo.class,"armj4");
        camservo = hardwareMap.get(CRServo.class, "cam servo");
        claw=hardwareMap.get(Servo.class,"claw");
        //set directions for motors
        frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        backleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        randommotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        initialise();
    }
    public void moveRobot(){
        move(gamepad1.left_stick_x* MainConfig.XSpeed,gamepad1.left_stick_y*MainConfig.YSpeed,gamepad1.right_stick_x);
    }
    public void moveWithDirection(double x, double y, double rot){
        move(x,y,(float)rot);
    }
    private void move(double speedX, double speedY, float steeringAngle) {
        frontright.setPower((speedY+speedX+steeringAngle)/3);
        backright.setPower((speedY-speedX+steeringAngle)/3);
        frontleft.setPower((speedY-speedX-steeringAngle)/3);
        backleft.setPower((speedY+speedX-steeringAngle)/3);
    }
    @Override
    public void runOpMode(){
        Init();
        waitForStart();
        if (opModeIsActive()) {
            Start();
            while (opModeIsActive()) {
                Loop();
            }
        }
    }
    public abstract void initialise();
    public abstract void Start();
    public abstract void Loop();
    public double getArm1Angle(){
        return armj1.getCurrentPosition();
    }
    public double getArm2Angle(){
        return armj2.getCurrentPosition();
    }
}
