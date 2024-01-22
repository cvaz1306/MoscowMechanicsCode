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



    public  DcMotorEx backleft;
    public DcMotorEx backright;
    public CRServo camservo;
    public Servo claw;
    public DcMotorEx frontleft;
    public DcMotorEx frontright;
    public DcMotorEx randommotor;
    public DcMotorEx drone;
    public DcMotorEx armj1;
    public DcMotorEx armj2;
    public Servo armj3;



    ArrayList<ExtObj> trackedDels = new ArrayList<>();
    ArrayList<ExtObj> trackedDels1 = new ArrayList<>();

    public FtcDashboard dashboard = FtcDashboard.getInstance();

    // todo: write your code here'

    public void Init(){
        //init motors
        frontright=hardwareMap.get(DcMotorEx.class, "front right");
        backright=hardwareMap.get(DcMotorEx.class, "back right");
        frontleft=hardwareMap.get(DcMotorEx.class, "front left");
        backleft=hardwareMap.get(DcMotorEx.class, "back left");
        randommotor=hardwareMap.get(DcMotorEx.class, "notso motor");
        drone =hardwareMap.get(DcMotorEx.class, "drone");
        armj1=hardwareMap.get(DcMotorEx.class, "arm j1");
        armj2=hardwareMap.get(DcMotorEx.class, "arm j2");
        armj3=hardwareMap.get(Servo.class,"armj3");
        camservo = hardwareMap.get(CRServo.class, "cam servo");
        claw=hardwareMap.get(Servo.class,"claw");
        //set directions for motors
        frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        backleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        initialise();
    }
    public void moveRobot(){
        move(gamepad1.left_stick_x* MainConfig.XSpeed,gamepad1.left_stick_y*MainConfig.YSpeed,gamepad1.right_stick_x);
    }
    public void moveWithDirection(double x, double y, double rot){
        move(x,y,(float)rot);
    }
    private void move(double speedX, double speedY, float steeringAngle) {
        frontright.setVelocity((speedY+speedX+steeringAngle)/3);
        backright.setVelocity((speedY-speedX+steeringAngle)/3);
        frontleft.setVelocity((speedY-speedX-steeringAngle)/3);
        backleft.setVelocity((speedY+speedX-steeringAngle)/3);
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
                //trackedDels.clear();
            }
        }
    }
    public abstract void initialise();
    public abstract void Start();
    public abstract void Loop();
    void changeTracker(){
        int i=0;
        for (ExtObj e: trackedDels) {
            ExtObj f;
            try {
                f = trackedDels1.get(i);
                if (!e.equals(f)) {
                    f.onChanged();

                }
            } catch (Exception g) {
                RobotLog.a("exception: "+g.getMessage());
            }
            i++;
        }
        trackedDels1=new ArrayList<>(trackedDels);
        trackedDels.clear();

    }
    public void track(Getter delegate){
        ExtObj extObj=new ExtObj() {
            @Override
            public String name() {
                return delegate.name();
            }

            @Override
            public Object value() {
                return delegate.value();
            }

            @Override
            public boolean onChanged() {
                delegate.onChanged(value());
                return true;
            }
        };
        trackedDels.add(extObj);

    }
    public double getArm1Angle(){
        return armj1.getCurrentPosition();
    }
    public double getArm2Angle(){
        return armj2.getCurrentPosition();
    }
}
