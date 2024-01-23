package org.firstinspires.ftc.teamcode.Configs;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotX;

@Config()
public class ViperArmConfig {
    public static double targetPosition, armj3TargetPosition;
    public static double clawOpenPosition = 0.02, clawClosedPosition = 0.2;
    public static int armj1offset = 0, armj2offset = 0;
    public static double armSegment1Speed=500,getArmSegment1Power;
}
