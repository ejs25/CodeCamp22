package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Hardware.Controls.Controller;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import org.firstinspires.ftc.teamcode.Hardware.Mecanum;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.Color_Sensor;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.IMU;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;

//@Disabled
@TeleOp(name="Rocket League", group="Iterative Opmode")
public class RocketLeague extends OpMode {

    // Declare OpMode members.

    private ElapsedTime boostCooldown = new ElapsedTime();
    private ElapsedTime boostTime = new ElapsedTime();
    public Mecanum robot;
    Controller controller;
    Controller controller2;
    public CRServo duck;
    IMU imu;
    Color_Sensor color;
    public double boost = 0;
    double red;
    boolean rumbled = false;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        setOpMode(this);

        robot = new Mecanum();
        controller = new Controller(gamepad1);
        controller2 = new Controller(gamepad2);
        color = new Color_Sensor();
        imu = new IMU("imu");
        color.init("color");
        red = color.updateRed();


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

        //Boost Stuff

        if(color.updateRed()>red+1000 && boostCooldown.seconds() > 2.0 && boost < 3){
            boost = boost + 1;
            boostCooldown.reset();
        }



        if(boost > 2 && controller.RB.press()){
            boostTime.reset();
            boost = 0;
            rumbled = false;
        }

        if(!rumbled && boost > 2){
            rumbled = true;
            controller.gamepad.rumble(1000);
        }

        double power = .4;
        double drive = MathUtils.shift(controller.leftStick(), imu.getAngle()).y;
        double strafe = -MathUtils.shift(controller.leftStick(), imu.getAngle()).x;
        double turn = -controller.rightStick().x;

        if(boostTime.seconds()<1){
            power = 1;
        }

        robot.setDrivePower(power, strafe, turn, drive);

        controller.controllerUpdate();

        /*
             ----------- L O G G I N G -----------
                                                */
        multTelemetry.addData("Status", "TeleOp Running");
        multTelemetry.addData("Boost", boost);
        multTelemetry.addData("red", color.getRedCacheValue());
        multTelemetry.update();
    }

    @Override
    public void stop() {

        /*
                    Y O U R   C O D E   H E R E
                                                   */

    }
}