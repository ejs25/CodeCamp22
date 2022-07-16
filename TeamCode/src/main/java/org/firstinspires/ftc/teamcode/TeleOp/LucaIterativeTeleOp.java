package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Controls.ButtonControls;
import org.firstinspires.ftc.teamcode.Controls.JoystickControls;

import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.ButtonState.DOWN;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.ButtonState.TOGGLE;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.CIRCLE;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.CROSS;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.RB1;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.TOUCHPAD;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.TRIANGLE;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Input.LEFT;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Input.RIGHT;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Value.INVERT_SHIFTED_Y;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Value.SHIFTED_X;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Value.X;
import static org.firstinspires.ftc.teamcode.DashConstants.Side.blue;
import static org.firstinspires.ftc.teamcode.DashConstants.Side.red;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import org.firstinspires.ftc.teamcode.Hardware.Robot;

//@Disabled
@TeleOp(name="Luca's Iterative TeleOp", group="Iterative Opmode")
public class LucaIterativeTeleOp extends OpMode {

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
        robot = new Robot("luca");


        multTelemetry.addData("Status", "Initialized");

        multTelemetry.addLine(" -------- Subsystem Controls --------");
        multTelemetry.addData("Dropper", "[CROSS]");
        multTelemetry.addData("Duck Spinner", "[CIRCLE]");
        multTelemetry.addData("Duck Direction", "[RB1]");

        multTelemetry.addLine(" -------- Drive Controls --------");
        multTelemetry.addData("Drive", "[LEFT_STICK]");
        multTelemetry.addData("Turn", "[RIGHT_STICK]");
        multTelemetry.addData("Slow Down", "[LB2]");
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

        //                  RING DROPPER                    //
        if (BC.get(CROSS, TOGGLE))  robot.dropper.release();
        else                        robot.dropper.hold();

        //                  SWEEPER                         //
        if (BC.get(TRIANGLE, TOGGLE))   robot.sweeper.down();
        else                            robot.sweeper.up();


        //                  DRIVE CONTROLS                  //
        JC.setShifted(LEFT, robot.drivetrain.imu.getAngle());
        double drive =  JC.get(LEFT, INVERT_SHIFTED_Y);
        double strafe = JC.get(LEFT, SHIFTED_X);
        double turn = JC.get(RIGHT, X);

        //                  SPEED CONTROLS                  //
        double power = Range.clip(1 - gamepad1.left_trigger, 0.15, 0.7);


        robot.drivetrain.setDrivePower(power, strafe, turn, drive);


        /*
        if(){
            blue = !blue;
            red = !red;
        }
         */

        multTelemetry.addData("Drive", drive);
        multTelemetry.addData("Strafe", strafe);
        multTelemetry.addData("Turn", turn);
        multTelemetry.addData("DuckSpinner", BC.get(CIRCLE, DOWN));
        multTelemetry.addData("DuckSpinner Direction", BC.get(RB1, TOGGLE));
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