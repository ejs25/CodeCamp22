package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Hardware.Controls.Controller;

import static org.firstinspires.ftc.teamcode.DashConstants.Side.blue;
import static org.firstinspires.ftc.teamcode.DashConstants.Side.red;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import org.firstinspires.ftc.teamcode.Hardware.Robot;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;

//@Disabled
@TeleOp(name="Avery's Iterative TeleOp", group="Iterative Opmode")
public class AveryIterativeTeleOp extends OpMode {

    // Declare OpMode members.


    public Robot robot;
    Controller controller;
    Controller controller2;



    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        setOpMode(this);

        robot = new Robot("avery");
        controller = new Controller(gamepad1);


        multTelemetry.addData("Status", "Initialized");

        multTelemetry.addLine(" -------- Subsystem Controls --------");
        multTelemetry.addData("Dropper", "[CROSS]");
        multTelemetry.addData("Duck Spinner", "[CIRCLE]");
        multTelemetry.addData("Duck Direction", "[RB1]");
        multTelemetry.addData("Claw Toggle", "[TRIANGLE]");

        multTelemetry.addLine(" -------- Drive Controls --------");
        multTelemetry.addData("Drive", "[LEFT_STICK]");
        multTelemetry.addData("Turn", "[RIGHT_STICK]");
        multTelemetry.addData("Slow Down", "[LB2]");
        multTelemetry.update();


    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    }
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

        //              RING DROPPER                //
        if(controller.RTrigger.toggle())    robot.dropper.release();
        else                                robot.dropper.hold();

        //              CLAW MECHANISM              //
        if(controller.triangle.toggle())    robot.claw.close();
        else                                robot.claw.open();

        //                  SWEEPER                         //
        if (controller.square.toggle())     robot.sweeper.down();
        else                                robot.sweeper.up();

        //                  DUCK SPINNER                    //
        // CROSS toggles direction
        // CIRCLE controls duck spinner
        double duckPower = (blue) ? 0.6 : -0.6;
        if(controller.circle.press())   robot.duckSpinner.spin(duckPower);
        else                            robot.duckSpinner.spin(0);

        //                  DRIVE CONTROLS                  //
        double drive = MathUtils.shift(controller.leftStick(), robot.drivetrain.imu.getAngle()).y;
        double strafe = -MathUtils.shift(controller.leftStick(), robot.drivetrain.imu.getAngle()).x;
        double turn = -controller.rightStick().x;

        double power = Range.clip(1 - controller.LTrigger.getValue(), 0.15, 0.8);

        robot.drivetrain.setDrivePower(power, strafe, turn, drive);




        if(controller.RB.tap()){
            blue = !blue;
            red = !red;
        }

        multTelemetry.addData("Drive", drive);
        multTelemetry.addData("Strafe", strafe);
        multTelemetry.addData("Turn", turn);
        multTelemetry.addData("DuckSpinner", controller.circle.press());
        multTelemetry.update();


    }

    @Override
    public void stop() {

        /*
                    Y O U R   C O D E   H E R E
                                                   */

    }
}