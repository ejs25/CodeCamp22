package org.firstinspires.ftc.teamcode.Hardware;
import static org.firstinspires.ftc.teamcode.Hardware.ForresterDropper.DropperState.DOWN;
import static org.firstinspires.ftc.teamcode.Hardware.ForresterDropper.DropperState.HOLD;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Utilities.OpModeUtils;

public class ForresterDropper implements Dropper {


    private Servo servoLeft;
    private Servo servoRight;

    private final double DROP_POSITION_LEFT = 0.51;
    private final double HOLD_POSITION_LEFT = 0.05;
    private final double GRIP_POSITION_LEFT = 0;

    private final double DROP_POSITION_RIGHT = 0.49;
    private final double HOLD_POSITION_RIGHT = 0.94;
    private final double GRIP_POSITION_RIGHT = 1;

    public enum DropperState {
        DOWN, HOLD, GRIP
    }
    private DropperState state = HOLD;

    private ElapsedTime timer = new ElapsedTime();

    public ForresterDropper(String left, String right){
        servoLeft = OpModeUtils.hardwareMap.get(Servo.class, left);
        servoRight = OpModeUtils.hardwareMap.get(Servo.class, right);
        hold();
    }

    public String getState(){
        return state.toString();
    }

    private void drop(){
        servoLeft.setPosition(DROP_POSITION_LEFT);
        servoRight.setPosition(DROP_POSITION_RIGHT);
    }
    private void holdRing(){
        servoLeft.setPosition(HOLD_POSITION_LEFT);
        servoRight.setPosition(HOLD_POSITION_RIGHT);
    }
    private void grip(){
        servoLeft.setPosition(GRIP_POSITION_LEFT);
        servoRight.setPosition(GRIP_POSITION_RIGHT);
    }

    public void release(){
        state = DOWN;
    }

    /**
     * Grip the wobble goal
     */
    public void hold() {
        state = HOLD;
    }

    public void update(boolean grip, boolean drop){
        switch (state){
            case HOLD:
                timer.reset();
                holdRing();

                if (grip) state = DropperState.GRIP;
                else if (drop) state = DOWN;

                break;

            case DOWN:
                // Put down for 2 seconds
                drop();
                if (timer.seconds() > 2) state = HOLD;

                break;

            case GRIP:
                timer.reset();
                grip();
                if (grip) state = HOLD;
                else if (drop) state = DOWN;

                break;
        }
    }
}

