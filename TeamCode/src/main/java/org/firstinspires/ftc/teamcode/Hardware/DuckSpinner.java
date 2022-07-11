package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

public class DuckSpinner {
    public CRServo duck;
    public CRServo duck2;
    boolean luca;

    public DuckSpinner(boolean luca){
        this.luca = luca;
        duck = hardwareMap.get(CRServo.class, "duck");
        duck2 = hardwareMap.get(CRServo.class, "duck2");
    }


    public void spin(double speed){
        if(luca) {
            duck.setPower(speed);
            duck2.setPower(-speed);
        }else{
            duck.setPower(speed);

        }
    }

}
