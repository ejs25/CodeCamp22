package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Hardware.Controls.Controller;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import org.firstinspires.ftc.teamcode.Hardware.Robot;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.Color_Sensor;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.IMU;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

//@Disabled
@TeleOp(name="luca's spin :)", group="Iterative Opmode")
public class lucaSpin extends OpMode {

    // Declare OpMode members.

    private ElapsedTime boostCooldown = new ElapsedTime();
    private ElapsedTime boostTime = new ElapsedTime();
    public Robot robot;
    Controller controller;
    Controller controller2;
    IMU imu;
    public CRServo duck;
    public CRServo duck2;
    public double boost = 0;
    double red;
    boolean rumbled = false;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        setOpMode(this);

        duck = hardwareMap.get(CRServo.class, "duck");
        duck2 = hardwareMap.get(CRServo.class, "duck2");
        robot = new Robot();
        controller = new Controller(gamepad1);
        controller2 = new Controller(gamepad2);


        multTelemetry.addData("Status", "Initialized");
        multTelemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {

        /*
                    Y O U R   C O D E   H E R E
                                                   */


        multTelemetry.addData("Status", "InitLoop");
        multTelemetry.update();
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        boostCooldown.reset();



        /*
                    Y O U R   C O D E   H E R E
                                                   */

        multTelemetry.addData("Status", "Started");
        multTelemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        controller.controllerUpdate();

        duck.setPower(-controller.leftStick().y);
        duck2.setPower(-controller.leftStick().y);
    }

    @Override
    public void stop() {

        /*
                    Y O U R   C O D E   H E R E
                                                   */

    }
}