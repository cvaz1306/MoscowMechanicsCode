package org.firstinspires.ftc.teamcode.clarm;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ArmConfig;
import org.firstinspires.ftc.teamcode.CustomGeometry;
import org.firstinspires.ftc.teamcode.RobotX;

import java.util.Locale;

@TeleOp(name="Encoder test")
public class EncoderTest extends RobotX {
    public double ArmS1Power=0,ArmS2Power=0;
    @Override
    public void initialise() {

    }

    @Override
    public void Start() {

    }

    @Override

    public void Loop() {

        telemetry.addData("Encoder 1 pos", getArm1Angle());
        telemetry.addData("Encoder 2 pos", getArm2Angle());
        telemetry.update();
        TelemetryPacket packet = new TelemetryPacket();
        packet.put("Encoder 1 Pos", getArm1Angle());
        packet.put("Encoder 2 Pos", getArm2Angle());
        double arm1AngleRad=Math.toRadians(getArm1Angle());
        double arm2AngleRad=Math.toRadians(getArm2Angle());

        double x=Math.cos(arm1AngleRad)* ArmConfig.Segment1Length*1;
        double y=Math.sin(arm1AngleRad)*ArmConfig.Segment1Length*1;
        double x1=Math.cos(arm2AngleRad)*ArmConfig.Segment2Length*1;
        double y1=Math.sin(arm2AngleRad)*ArmConfig.Segment2Length*1;

        packet.fieldOverlay()
                .setStroke("green")
                .strokeLine(0,0,x,y)
                .strokeLine(x,y,x+x1,y+y1)
                .setStroke("green");
        dashboard.sendTelemetryPacket(packet);
        if(Math.abs(ArmConfig.targetPosition-getArm1Angle())<ArmConfig.ArmS1Accuracy){
            ArmS1Power=0;
            packet.addLine("Resetting");
        }
        if(gamepad1.a){
            if(getArm1Angle()<ArmConfig.targetPosition){
                ArmS1Power+=ArmConfig.ArmPowerInterval;
            }
            else {
                ArmS1Power-=ArmConfig.ArmPowerInterval;
            }
            armj1.setPower(ArmS1Power);
        }
        else {
            armj1.setPower(0);
            ArmS1Power=0;
        }
        if(Math.abs(ArmConfig.targetPosition-getArm2Angle())<ArmConfig.ArmS2Accuracy){
            ArmS2Power=0;
            packet.addLine("Resetting");
        }
        if(gamepad1.b){
            if(getArm2Angle()<ArmConfig.targetPosition2){
                ArmS2Power+=ArmConfig.ArmPowerInterval2;
            }
            else {
                ArmS2Power-=ArmConfig.ArmPowerInterval2;
            }
            armj2.setPower(ArmS2Power);
        }
        else {
            armj2.setPower(0);
            ArmS2Power=0;
        }
        ArmS1Power= CustomGeometry.clamp(ArmS1Power,1f,-1f);
        ArmS2Power=CustomGeometry.clamp(ArmS2Power,1f,-1f);
    }
}
