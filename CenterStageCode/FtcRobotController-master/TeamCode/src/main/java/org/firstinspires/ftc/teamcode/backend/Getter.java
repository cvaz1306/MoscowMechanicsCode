package org.firstinspires.ftc.teamcode.backend;

public interface Getter {
    Object value();
    String name();
    void onChanged(Object value);
}
