package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.ButtonState.DOWN;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.CROSS;
import static org.firstinspires.ftc.teamcode.Hardware.Dropper.CurrentState.OPEN;
import static org.firstinspires.ftc.teamcode.Hardware.Dropper.CurrentState.UP;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import com.qualcomm.robotcore.hardware.Servo;

public class Dropper {

    String left_id;
    String right_id;
    Servo leftDropper;
    Servo rightDropper;


    CurrentState currentState = CurrentState.UP;

    public enum CurrentState {
        OPEN, UP
    }

    double RIGHT_UP_POS = 0.06;
    double RIGHT_DOWN_POS = 0.5;
    double LEFT_UP_POS = 0.94;
    double LEFT_DOWN_POS = 0.5;

    public Dropper(String left_id, String right_id){
        this.left_id = left_id;
        this.right_id = right_id;
        leftDropper = hardwareMap.get(Servo.class, left_id);
        rightDropper = hardwareMap.get(Servo.class, right_id);
    }




    public void update(boolean LB2, boolean TRIANGLE, boolean CROSS){

        switch (currentState){

            case UP:
                rightDropper.setPosition(0.31);
                leftDropper.setPosition(0.94);
                if (LB2 && CROSS){
                    currentState = OPEN;
                }
                break;

            case OPEN:
                rightDropper.setPosition(0.7);
                leftDropper.setPosition(0.5);
                if (LB2 && TRIANGLE){
                    currentState = UP;
                }
                break;

        }

    }
}
