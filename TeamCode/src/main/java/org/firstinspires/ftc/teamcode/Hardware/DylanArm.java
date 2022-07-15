package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import com.qualcomm.robotcore.hardware.Servo;

public class DylanArm {
    private double ARM_DOWN_POSITION = 0.15;
    private double ARM_MID_POSITION = 0.32;
    private double ARM_UP_POSITION = 0.75;

    private String id;
    private Servo servo;

    public DylanArm(String id){
        this.id = id;
        servo = hardwareMap.get(Servo.class, this.id);
    }

    public void up(){
        this.servo.setPosition(ARM_UP_POSITION);
    }
    public void mid(){
        this.servo.setPosition(ARM_MID_POSITION);
    }
    public void down(){
        this.servo.setPosition(ARM_DOWN_POSITION);
    }
}