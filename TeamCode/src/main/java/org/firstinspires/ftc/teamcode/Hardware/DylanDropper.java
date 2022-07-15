package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Hardware.DylanDropper.DropperState.DROP_RING;
import static org.firstinspires.ftc.teamcode.Hardware.DylanDropper.DropperState.HOLD_RING;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class DylanDropper implements Dropper {

   // [TRIANGLE] = DROP
   // [SQUARE] = GRIP

    private String spatula_id;
    private Servo spatula_servo;
    private DropperState state = HOLD_RING;
    private ElapsedTime timer = new ElapsedTime();

    private double SPATULA_DROP_POSITION = 0.7;
    private double SPATULA_HOLD_POSITION = 0.92;
    private double SPATULA_GRIP_POSITION = 1;


    public static enum DropperState {
        DROP_RING, HOLD_RING, GRIP
    }

    public DylanDropper(String spatula_id){
        this.spatula_id = spatula_id;

        this.spatula_servo = hardwareMap.get(Servo.class, this.spatula_id);
    }

    public String getState(){
        return this.state.toString();
    }

    public void release(){
        state = DROP_RING;
    }
    public void hold(){
        state = HOLD_RING;
    }

    public void update(boolean grip, boolean drop){
        switch (state){
            case HOLD_RING:
                spatula_servo.setPosition(SPATULA_HOLD_POSITION);
                if (drop){
                    timer.reset();
                    state = DROP_RING;
                }
                else if (grip) state = DropperState.GRIP;

                break;

            case DROP_RING:
                if (timer.seconds() < 1) spatula_servo.setPosition(SPATULA_DROP_POSITION);
                else state = HOLD_RING;

                break;
            case GRIP:
                spatula_servo.setPosition(SPATULA_GRIP_POSITION);

                // Toggle the grip if pressed again, may change in the future
                if (grip) state = HOLD_RING;
                if (drop) state = DROP_RING;

                break;

        }
    }

}
