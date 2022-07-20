package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.ButtonState.TAP;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.CIRCLE;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Input.LEFT;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Input.RIGHT;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Value.INVERT_SHIFTED_Y;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Value.INVERT_Y;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Value.SHIFTED_X;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Value.X;
import static org.firstinspires.ftc.teamcode.Controls.JoystickControls.Value.Y;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import org.firstinspires.ftc.teamcode.Controls.ButtonControls;
import org.firstinspires.ftc.teamcode.Controls.Controller;
import org.firstinspires.ftc.teamcode.Controls.JoystickControls;
import org.firstinspires.ftc.teamcode.Hardware.Robot;
import org.firstinspires.ftc.teamcode.Utilities.PID;

//@Disabled
@TeleOp(name="Iterative TeleOp", group="Iterative Opmode")
public class IterativeTeleOp extends OpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    Robot iretomide;
    Controller controller;
    boolean speed_toggle = false;
    boolean last = true;
    boolean toggle = false;
    private PID pid;
    private double setPoint = 0;
    private boolean wasTurning;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        setOpMode(this);

        pid = new PID(0.05, 0, 0);

        iretomide = new Robot();

        controller = new Controller(gamepad1);

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
        runtime.reset();


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
        Controller.update();

        //controller.joyticks.setShifted(LEFT, iretomide.gyro.angle());

        double correction = pid.update(iretomide.gyro.getAngle() - setPoint, true);
        double rotation = controller.get(RIGHT, X);
        if (!(controller.get(RIGHT, X) == 0)){
            wasTurning = true;
        } else {
            if(wasTurning){
                setPoint = iretomide.gyro.getAngle();
                wasTurning = false;
            }
            rotation = correction;
        }

        if(controller.get(CIRCLE, TAP)){
            setPoint += 90;
        }



        controller.setJoystickShift(LEFT, iretomide.gyro.getAngle());

        double drive = controller.get(LEFT, INVERT_SHIFTED_Y);
        double strafe = controller.get(LEFT, SHIFTED_X);
        double turn = controller.get(RIGHT, X);
        double power = 0.5;


        if (gamepad1.triangle && last == false){
            speed_toggle = !speed_toggle;
            last = true;
        } else if (!gamepad1.triangle){
            last = false;
        }

        if (speed_toggle){
            power = 0.3;
        }


        iretomide.tijani.setDrivePower(drive, strafe, rotation, power);




        /*
        //forward and backwards movement:
        if (gamepad1.triangle){
            motor1.setPower(0.5); //forward strafe
            motor2.setPower(-0.5);
            motor3.setPower(0.5);
            motor4.setPower(-0.5);
        } else if (gamepad1.cross){
            motor1.setPower(-0.5); //backward strafe
            motor2.setPower(0.5);
            motor3.setPower(-0.5);
            motor4.setPower(0.5);
        } else {
            motor1.setPower(0); //stop / do nothing
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);
        }

        //left and right strafe movement:
        if (gamepad1.circle){
            motor1.setPower(0.5); //right strafe
            motor2.setPower(0.5);
            motor3.setPower(-0.5);
            motor4.setPower(-0.5);
        } else if (gamepad1.square){
            motor1.setPower(-0.5); //left strafe
            motor2.setPower(-0.5);
            motor3.setPower(0.5);
            motor4.setPower(0.5);
        } else {
            motor1.setPower(0); //stop / do nothing
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);
        }

        //turning movement
        if (gamepad1.dpad_left){
            motor1.setPower(-0.5); //left turn
            motor2.setPower(0.5);
            motor3.setPower(-0.5);
            motor4.setPower(0.5);
        } else if (gamepad1.dpad_right){
            motor1.setPower(0.5); //right turn
            motor2.setPower(-0.5);
            motor3.setPower(0.5);
            motor4.setPower(-0.5);
        } else {
            motor1.setPower(0); //stop / do nothing
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);
        }
    */
        /*
             ----------- L O G G I N G -----------
                                                */
        multTelemetry.addData("Status", "TeleOp Running");
        multTelemetry.addData("Angle", iretomide.gyro.getAngle());
        multTelemetry.addData("drive", drive);
        multTelemetry.addData("strafe", strafe);
        multTelemetry.addData("turn", turn);
        multTelemetry.update();
    }

    @Override
    public void stop() {

        /*
                    Y O U R   C O D E   H E R E
                                                   */

    }
}