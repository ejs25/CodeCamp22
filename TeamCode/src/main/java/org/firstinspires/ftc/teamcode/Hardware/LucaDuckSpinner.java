package org.firstinspires.ftc.teamcode.Hardware;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import com.qualcomm.robotcore.hardware.CRServo;

public class LucaDuckSpinner implements DuckSpinner {
    private CRServo duck;
    private CRServo duck2;
    private String id1, id2;

    public LucaDuckSpinner(String id1, String id2){
        this.id1 = id1;
        this.id2 = id2;

        duck = hardwareMap.get(CRServo.class, id1);
        duck2 = hardwareMap.get(CRServo.class, id2);

        duck.setDirection(FORWARD);
        duck2.setDirection(FORWARD);
    }

    public void spin(double speed){
        duck.setPower(speed);
        duck2.setPower(speed);
    }

}
