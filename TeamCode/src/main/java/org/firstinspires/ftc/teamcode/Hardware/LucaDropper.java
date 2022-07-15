package org.firstinspires.ftc.teamcode.Hardware;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Utilities.OpModeUtils;

public class LucaDropper implements Dropper {
    
    private String id;
    private Servo servo;

    private final double DROP_POSITION = 0.5;
    private final double HOLD_POSITION = 0.0;

    public LucaDropper(String id){
        this.id = id;
        this.servo = OpModeUtils.hardwareMap.get(Servo.class, id);
        hold();
    }
    public String getState(){
        return "";
    }

    public void shutdown(){
        hold();
    }

    public void release(){
        servo.setPosition(DROP_POSITION);
    }
    public void hold(){
        servo.setPosition(HOLD_POSITION);
    }
    public void update(boolean grip, boolean drop){

    }
}
