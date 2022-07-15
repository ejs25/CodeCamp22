package org.firstinspires.ftc.teamcode.TeleOp;

import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.ButtonState.DOWN;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.ButtonState.TAP;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.ButtonState.TOGGLE;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.CIRCLE;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.DPAD_DN;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.DPAD_L;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.DPAD_R;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.DPAD_UP;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.LB2;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.RB1;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.SQUARE;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.TOUCHPAD;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.TRIANGLE;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Input.LEFT;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Input.RIGHT;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Value.INVERT_SHIFTED_X;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Value.SHIFTED_Y;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Value.X;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Controls.ButtonControls;
import org.firstinspires.ftc.teamcode.Controls.JoystickControls;
import org.firstinspires.ftc.teamcode.Hardware.Robot;

//@Disabled
@TeleOp(name="Dylan's Iterative TeleOp", group="Iterative Opmode")
public class DylanIterativeTeleOp extends OpMode {

    // Declare OpMode members.


    ButtonControls BC;
    JoystickControls JC;
    Robot robot;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        setOpMode(this);


        BC = new ButtonControls(gamepad1);
        JC = new JoystickControls(gamepad1);
        robot = new Robot("dylan");


        multTelemetry.addData("Status", "Initialized");

//        multTelemetry.addLine(" -------- Subsystem Controls --------");
//        multTelemetry.addData("Dropper", "[CROSS]");
//        multTelemetry.addData("Duck Spinner", "[CIRCLE]");
//        multTelemetry.addData("Duck Direction", "[RB1]");
//
//        multTelemetry.addLine(" -------- Drive Controls --------");
//        multTelemetry.addData("Drive", "[LEFT_STICK]");
//        multTelemetry.addData("Turn", "[RIGHT_STICK]");
//        multTelemetry.addData("Slow Down", "[LB2]");
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

        ButtonControls.update();
        JoystickControls.update();


        //                  DUCK SPINNER                    //
        // CROSS toggles direction
        // CIRCLE controls duck spinner
        double duckPower = (BC.get(RB1, TOGGLE)) ? 0.6 : -0.6;
        if (BC.get(CIRCLE, DOWN))   robot.duckSpinner.spin(duckPower);
        else                        robot.duckSpinner.spin(0);

        //                  DROPPER / GRIPPER               //
        robot.dropper.update(BC.get(SQUARE, TAP), BC.get(TRIANGLE, TAP));

        //                  ARM                             //
        if (BC.get(DPAD_DN, TAP))           robot.arm.down();
        else if (BC.get(DPAD_R, TAP))      robot.arm.mid();
        else if (BC.get(DPAD_UP, TAP))      robot.arm.up();

        //                  DRIVE CONTROLS                  //
        JC.setShifted(LEFT, robot.drivetrain.imu.getAngle());
        double drive =  JC.get(LEFT, SHIFTED_Y);
        double strafe = JC.get(LEFT, INVERT_SHIFTED_X);
        double turn = JC.get(RIGHT, X);

        //                  SPEED CONTROLS                  //
        double power = (BC.get(LB2, TOGGLE)) ? 0.2 : 0.6;


        robot.drivetrain.setDrivePower(power, strafe, turn, drive);

        multTelemetry.addData("Drive", drive);
        multTelemetry.addData("Strafe", strafe);
        multTelemetry.addData("Turn", turn);
        multTelemetry.addData("DuckSpinner", BC.get(CIRCLE, DOWN));
        multTelemetry.addData("Dropper", robot.dropper.getState());
        multTelemetry.update();

        /*
             ----------- S H U T D O W N -----------
                                                  */

        if (BC.get(TOUCHPAD, DOWN)){
            return;
        }
    }

    @Override
    public void stop() {

        /*
                    Y O U R   C O D E   H E R E
                                                   */

    }
}