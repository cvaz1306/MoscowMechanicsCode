package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.RobotX;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

// ...


@Autonomous(name="AutonomousV1")
public class AutonomousV1 extends RobotX{
    private boolean isBraked;


    @Override public void initialise(){}
    // todo: write your code here
    @Override public void Start(){
        Segment s0=new Segment(0,-4,0,400);

        Segment s1=new Segment(0,0,-2,270);
        Segment s2=new Segment(0,-4,0,260);









      followSegmentArray(new Segment[]{
                s0,
                s1,
                s2,
        });
    }
    private void followSegmentArray(Segment[] segments){
        for(Segment segment : segments){
            moveWithDirection((float) segment.xS, (float) segment.yS, (float) segment.rotS);
            sleep(segment.time);
            moveWithDirection(0,0,0);
            sleep(300);
        }
    }
    @Override public void Loop(){
        //if(! MainConfig.IsDrivingDisabled) super.moveRobot();
        //super.randommotor.setPower(gamepad2.right_trigger-gamepad2.left_trigger);
        isBraked=!isBraked;


        if(isBraked){
            backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
        else{
            backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

    }
}