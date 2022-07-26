package org.firstinspires.ftc.teamcode.Hardware;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.isActive;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Mecanum {
    /**
     * Initializes the robot's necessary subsystems and motors
     */
    //constructor
    public Mecanum(){
        initMecanum();
    }

    private ElapsedTime runtime = new ElapsedTime();
    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;

    public void initMecanum(){

        fl = hardwareMap.get(DcMotor.class, "fl");
        fl.setDirection(DcMotorSimple.Direction.REVERSE);

        fr = hardwareMap.get(DcMotor.class, "fr");
        fr.setDirection(DcMotorSimple.Direction.FORWARD);

        bl = hardwareMap.get(DcMotor.class, "bl");
        bl.setDirection(DcMotorSimple.Direction.REVERSE);

        br = hardwareMap.get(DcMotor.class, "br");
        br.setDirection(DcMotorSimple.Direction.FORWARD);

        multTelemetry.addData("Status", "Initialized");
        multTelemetry.update();
    }

    /**
     * Sets the same power to all four motors
     * @param power
     */
    public void setAllPower(double power){
        /*

                Y O U R   C O D E   H E R E

         */
        fl.setPower(power);
        fr.setPower(power);
        bl.setPower(power);
        br.setPower(power);
    }




    /**
     * Sets the power to the motors for driving with a mecanum drivetrain
     * @param power
     */
    public void setDrivePower(double drive, double strafe, double turn, double power){
        double frPower = (drive - strafe - turn) * power;
        double flPower = (drive + strafe + turn) * power;
        double brPower = (drive + strafe - turn) * power;
        double blPower = (drive - strafe + turn) * power;

        multTelemetry.addData("flPower", flPower);
        multTelemetry.addData("frPower", frPower);
        multTelemetry.addData("blPower", blPower);
        multTelemetry.addData("brPower", brPower);

        fl.setPower(flPower);
        fr.setPower(frPower);
        bl.setPower(blPower);
        br.setPower(brPower);
    }



    /**
     * resets the motors before every strafe
     */
    public void resetMotors(){
        fl.setMode(STOP_AND_RESET_ENCODER);
        fr.setMode(STOP_AND_RESET_ENCODER);
        bl.setMode(STOP_AND_RESET_ENCODER);
        br.setMode(STOP_AND_RESET_ENCODER);
    }


    /**
     *
     * @return avg of all motors' current position (each is absolute valued)
     */
    public double getPosition(){
        double flPosition = fl.getCurrentPosition();
        double frPosition = fr.getCurrentPosition();
        double blPosition = bl.getCurrentPosition();
        double brPosition = br.getCurrentPosition();
        return (flPosition + frPosition + blPosition + brPosition) / 4.0;
    }


    /**
     * Translates the robot autonomously a certain distance known as ticks
     * @param ticks
     */
    public void strafe(double ticks){
        resetMotors();
        double current_distance = 0.0;
        double motor_power = 0.5;

        if (ticks < 0){
            motor_power = -0.5;
        }

        while (abs(current_distance) < abs(ticks) && isActive()){
             current_distance = getPosition();
            setAllPower(motor_power);
            multTelemetry.addData("current distance", current_distance);
            multTelemetry.update();
        }

        setAllPower(0.0);
    }

    /**
     * Rotates the robot autonomously a certain number of degrees with a margin of error
     * @param degrees
     * @param moe
     */
    public void turn(double degrees, double moe){
        /*

                Y O U R   C O D E   H E R E

         */

    }


    /**
     * A mathematical function that optimizes the ramping of power to the motors during autonomous
     * strafes.
     * @param position
     * @param distance
     * @param acceleration
     * @return the coefficient [0, 1] of our power
     */
    public static double powerRamp(double position, double distance, double acceleration){
        /*

                Y O U R   C O D E   H E R E

         */
        return 0;
    }
}
