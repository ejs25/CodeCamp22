package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import com.qualcomm.robotcore.hardware.CRServo;

public class GenericDuckSpinner implements DuckSpinner {
    private CRServo duck;
    private String id;

    public GenericDuckSpinner(String id){
        this.id = id;
        duck = hardwareMap.get(CRServo.class, id);
    }

    public void spin(double speed){
        duck.setPower(speed);
    }

}
