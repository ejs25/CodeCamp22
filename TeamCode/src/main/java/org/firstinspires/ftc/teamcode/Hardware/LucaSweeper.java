package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import com.qualcomm.robotcore.hardware.Servo;


public class LucaSweeper implements Sweeper {
    private Servo servo;
    private String id;

    private final double SWEEPER_UP = 1;
    private final double SWEEPER_DOWN = 0.75;


    public LucaSweeper(String id){
        this.id = id;
        servo = hardwareMap.get(Servo.class, id);
    }

    public void down(){
        servo.setPosition(SWEEPER_DOWN);
    }
    public void up(){
        servo.setPosition(SWEEPER_UP);
    }
}
