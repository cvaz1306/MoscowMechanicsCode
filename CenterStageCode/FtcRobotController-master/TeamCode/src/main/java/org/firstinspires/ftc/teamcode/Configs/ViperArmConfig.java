package org.firstinspires.ftc.teamcode.Configs;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotX;

@Config()
public class ViperArmConfig {
    public static double targetPosition, armj3TargetPosition;
    public static double clawOpenPosition = 0.5, clawClosedPosition = 0.1;
    public static int armj1offset = 0, armj2offset = 0;
    public static double armSegment1Speed=300,getArmSegment1Power;
    public static int droneDelay=130000;
    public static double speed=.01;
}
