package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Sensors.IMU;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;


public class Robot {

   public IMU imu;
   public DcMotor fr,fl,br,bl;

   public static ElapsedTime time = new ElapsedTime();

   public Robot(){
      initRobot();
   }

   public void initRobot() {

      fr = hardwareMap.get(DcMotor.class, "fr");
      fl = hardwareMap.get(DcMotor.class, "fl");
      br = hardwareMap.get(DcMotor.class, "br");
      bl = hardwareMap.get(DcMotor.class, "bl");


      fr.setDirection(DcMotorSimple.Direction.FORWARD);
      fl.setDirection(DcMotorSimple.Direction.REVERSE);
      br.setDirection(DcMotorSimple.Direction.FORWARD);
      bl.setDirection(DcMotorSimple.Direction.REVERSE);

      fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


      fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


      imu      = new IMU("imu");

      multTelemetry.addData("Status", "Initialized");
      multTelemetry.update();
   }

   /**
    * @param power
    */
   public void setAllPower(double power){
        /*

                Y O U R   C O D E   H E R E

         */
   }

   /**
    * @param power
    */
      public void setDrivePower(double power, double strafe, double turn, double drive){

         double frPower = (drive - strafe - turn) * power;
         double flPower = (drive + strafe + turn) * power;
         double brPower = (drive + strafe - turn) * power;
         double blPower = (drive - strafe + turn) * power;

         fr.setPower(frPower);
         fl.setPower(flPower);
         br.setPower(brPower);
         bl.setPower(blPower);

   }


   /**
    * IMPLEMENT ME
    * @param ticks
    */
   public void strafe(double ticks){
        /*

                Y O U R   C O D E   H E R E

         */
   }

   /**
    * IMPLEMENT ME
    * @param degrees
    * @param moe
    */
   public void turn(double degrees, double moe){
        /*

                Y O U R   C O D E   H E R E

         */
   }


   /**
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