package org.firstinspires.ftc.teamcode.Diagnostics;

import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.ButtonState.DOWN;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.DPAD_DN;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.DPAD_UP;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.TOUCHPAD;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_CRServoDiagnostic.CRSERVO_POWER;
import static org.firstinspires.ftc.teamcode.DashConstants.Dash_CRServoDiagnostic.SERVO_ID;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Controls.ButtonControls;
import org.firstinspires.ftc.teamcode.Utilities.OpModeUtils;


@TeleOp(name = "CRServoDiagnostic TeleOp", group="Linear TeleOp")
public class CRServoDiagnostic extends LinearOpMode {

    private ButtonControls BC;

    private CRServo servo;
    private String status;
    private String servo_id = SERVO_ID;


    public void initialize() {
        OpModeUtils.setOpMode(this);
        BC = new ButtonControls(gamepad1);


        servo = OpModeUtils.hardwareMap.get(CRServo.class, servo_id);
        servo.setDirection(DcMotorSimple.Direction.FORWARD);

        OpModeUtils.multTelemetry.addData("Status", "Initialized");
        OpModeUtils.multTelemetry.addData("Start Keys", "Press [>] to begin");
        OpModeUtils.multTelemetry.addData("Shutdown Keys", "Press [TOUCHPAD]");
        OpModeUtils.multTelemetry.update();
    }

    public void shutdown(){
        OpModeUtils.multTelemetry.addData("Status", "Shutting Down");
        OpModeUtils.multTelemetry.update();
        sleep(3000);
    }


    @Override
    public void runOpMode() {


        initialize();
        waitForStart();

        while (opModeIsActive()) {

            ButtonControls.update();

            if (BC.get(DPAD_UP, DOWN)) {
                servo.setPower(CRSERVO_POWER);
                status = "FORWARDS";
            }
            if (BC.get(DPAD_DN, DOWN)) {
                servo.setPower(-CRSERVO_POWER);
                status = "BACKWARDS";
            }

            OpModeUtils.multTelemetry.addData("Servo ID", servo_id);
            OpModeUtils.multTelemetry.addData("Servo Status", status);
            OpModeUtils.multTelemetry.addData("Servo Power", servo.getPower());
            OpModeUtils.multTelemetry.update();



            // S H U T D O W N     S E Q U E N C E

            if (BC.get(TOUCHPAD, DOWN)){
                shutdown();
                break;
            }
        }
    }
}


