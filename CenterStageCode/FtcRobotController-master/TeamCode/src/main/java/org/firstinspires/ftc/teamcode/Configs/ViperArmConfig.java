package org.firstinspires.ftc.teamcode.Configs;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotX;

@Config()
@TeleOp(name = "Arm reset")
public class ViperArmConfig extends RobotX {
    public static double targetPosition,armj3TargetPosition;
    public static double clawOpenPosition=0.02,clawClosedPosition=0.2;
    public static int armj1offset=0,armj2offset=0;
    @Override
    public void initialise() {
        armj1offset=-armj1.getCurrentPosition();
        armj2offset=armj2.getCurrentPosition();

    }

    @Override
    public void Start() {

    }

    @Override
    public void Loop() {

    }
}
