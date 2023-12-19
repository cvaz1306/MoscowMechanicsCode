package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotX;
import org.firstinspires.ftc.teamcode.backend.Getter;
@TeleOp(name = "ChangeTracker")
public class ChangeTracker extends RobotX {
    int i=0;
    @Override
    public void initialise() {

    }

    @Override
    public void Start() {

    }

    @Override
    public void Loop() {
        track(new Getter() {
            @Override
            public Object value() {
                return gamepad1.a;
            }

            @Override
            public String name() {
                return "gamepad a";
            }

            @Override
            public void onChanged(Object value) {
                i=i+1;
                telemetry.addData("A",i);
                telemetry.update();
            }
        });
    }
}
