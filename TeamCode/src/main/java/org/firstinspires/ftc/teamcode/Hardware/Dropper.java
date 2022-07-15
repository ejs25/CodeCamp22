package org.firstinspires.ftc.teamcode.Hardware;

public interface Dropper {
    public void release();
    public void hold();
    public void update(boolean grip, boolean drop);
    public String getState();

}
