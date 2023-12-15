package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.backend.Getter;
import org.firstinspires.ftc.teamcode.backend.ExtObj;

import java.util.ArrayList;

import com.qualcomm.robotcore.hardware.CRServo;
public abstract class RobotX extends LinearOpMode{



    public  DcMotor backleft;
    public DcMotor backright;
    public CRServo camservo;
    public DcMotor frontleft;
    public DcMotor frontright;
    public DcMotor randommotor;
    public DcMotor hookmotor;
    public DcMotor armj1;
    public DcMotor armj2;

    private int changes;

    ArrayList<ExtObj> trackedDels = new ArrayList<ExtObj>();
    ArrayList<ExtObj> trackedDels1 = new ArrayList<ExtObj>();

    FtcDashboard dashboard = FtcDashboard.getInstance();

    // todo: write your code here'

    public void Init(){
        //init motors
        frontright=hardwareMap.get(DcMotor.class, "front right");
        backright=hardwareMap.get(DcMotor.class, "back right");
        frontleft=hardwareMap.get(DcMotor.class, "front left");
        backleft=hardwareMap.get(DcMotor.class, "back left");
        randommotor=hardwareMap.get(DcMotor.class, "random motor");
        hookmotor=hardwareMap.get(DcMotor.class, "hook");
        armj1=hardwareMap.get(DcMotor.class, "arm j1");
        armj2=hardwareMap.get(DcMotor.class, "arm j2");

        camservo = hardwareMap.get(CRServo.class, "cam servo");

        //set directions for motors
        frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        backleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        initialise();
    }
    void moveRobot(){
        move(-((-(gamepad1.left_stick_x))*Math.abs((gamepad1.left_stick_x))*MainConfig.XSpeed), (gamepad1.left_stick_y)*Math.abs((gamepad1.left_stick_y))*MainConfig.YSpeed, -((gamepad1.right_stick_x))*2.6f);
    }
    void moveWithDirection(float x, float y, float rot){
        move(x, y, rot);
    }
    private void move(double speedX, double speedY, float steeringAngle) {
        if (gamepad1.right_bumper) {
            // move slowly
            frontright.setPower(((speedY + -speedX) / 2 + steeringAngle) * (1.2));
            backright.setPower(((speedY + speedX) / 2 + steeringAngle) * (1.2));
            frontleft.setPower((-((-speedY + -speedX) / 2) - steeringAngle) * (1.2));
            backleft.setPower(((-speedY + speedX) / 2 + steeringAngle) * (1.2));
        } else {
            frontright.setPower(((speedY + -speedX) / 2 + steeringAngle) / 5);
            backright.setPower(((speedY + speedX) / 2 + steeringAngle) / 5);
            frontleft.setPower((-((-speedY + -speedX) / 2) - steeringAngle) / 5);
            backleft.setPower(((-speedY + speedX) / 2 + steeringAngle) / 5);

        }
    }
    @Override
    public void runOpMode(){
        Init();
        waitForStart();
        if (opModeIsActive()) {
            Start();
            while (opModeIsActive()) {


                Loop();
                changeTracker();
                trackedDels.clear();
            }
        }
    }
    public abstract void initialise();
    public abstract void Start();
    public abstract void Loop();
    void changeTracker(){
        for (ExtObj e: trackedDels) {

        }
    }
    void track(Getter delegate){
        ExtObj extObj=new ExtObj() {
            @Override
            public String name() {
                return delegate.name();
            }

            @Override
            public Object value() {
                return delegate.value();
            }
        };
        trackedDels.add(extObj);

    }

}
