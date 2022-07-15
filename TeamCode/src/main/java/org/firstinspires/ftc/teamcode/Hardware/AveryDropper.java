package org.firstinspires.ftc.teamcode.Hardware;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Utilities.OpModeUtils;

public class AveryDropper implements Dropper {

    private Servo servoLeft;
    private Servo servoRight;

    private final double DROP_POSITION_LEFT = 0.3;
    private final double HOLD_POSITION_LEFT = 0;

    private final double DROP_POSITION_RIGHT = 0.85;
    private final double HOLD_POSITION_RIGHT = 0.98;

    public AveryDropper(String left, String right){
        servoLeft = OpModeUtils.hardwareMap.get(Servo.class, left);
        servoRight = OpModeUtils.hardwareMap.get(Servo.class, right);
        hold();
    }

    public String getState(){
        return "";
    }

    public void shutdown(){
        hold();
    }

    public void release(){
        servoLeft.setPosition(DROP_POSITION_LEFT);
        servoRight.setPosition(DROP_POSITION_RIGHT);
    }
    public void hold(){
        servoLeft.setPosition(HOLD_POSITION_LEFT);
        servoRight.setPosition(HOLD_POSITION_RIGHT);
    }
    public void update(boolean grip, boolean drop){

    }

}
