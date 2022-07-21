package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.wolfpackmachina.bettersensors.Sensors.Gyro;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;

import org.firstinspires.ftc.teamcode.Hardware.Sensors.IMU;

/**
 * A class for containing an FTC Mecanum robot
 */
public class Robot {

   public static ElapsedTime time = new ElapsedTime();

   public Mecanum tijani;

   public IMU gyro;

   public DuckSpinner nas;


   public Robot(){
      initRobot();
   }

   public void initRobot() {

      /*
            I N I T   M O T O R S
       */

      //initialized Mecanum
      tijani = new Mecanum();

      gyro = new IMU( "imu");

      nas = new DuckSpinner("nasreddine");


      multTelemetry.addData("Status", "Initialized");
      multTelemetry.update();
   }

}