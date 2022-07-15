package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import com.qualcomm.robotcore.hardware.Servo;

public class AveryClaw {

    private double RIGHT_SERVO_OPEN = 0.3;
    private double RIGHT_SERVO_CLOSE = 0.88;

    private double LEFT_SERVO_OPEN = 0.72;
    private double LEFT_SERVO_CLOSE = 0.25;

    private String left_id, right_id;

    private Servo left_servo, right_servo;

    public AveryClaw(String left_id, String right_id){
        this.left_id = left_id;
        this.right_id = right_id;

        this.left_servo = hardwareMap.get(Servo.class, left_id);
        this.right_servo = hardwareMap.get(Servo.class, right_id);

        init();
    }

    public void init(){
        close();
    }

    public void close(){
        this.left_servo.setPosition(LEFT_SERVO_CLOSE);
        this.right_servo.setPosition(RIGHT_SERVO_CLOSE);
    }

    public void open(){
        this.left_servo.setPosition(LEFT_SERVO_OPEN);
        this.right_servo.setPosition(RIGHT_SERVO_OPEN);
    }
}
