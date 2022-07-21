package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.ButtonState.DOWN;
import static org.firstinspires.ftc.teamcode.Controls.ButtonControls.Input.TRIANGLE;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import com.qualcomm.robotcore.hardware.CRServo;

public class DuckSpinner {

    /** TODO: Declare your attributes up here
     * - a String named id
     * - a CRServo named crservo
     */
    private String id;
    private CRServo nasreddine;


    /** TODO: Write your Constructor here!
     * PARAMETERS: a String named id
     * FUNCTIONALITY: Should instantiate the CRServo using the OpModeUtils.hardwareMap
     *                Specifically, it should use the hardwareMaps get() method, if you forget
     *                what the the parameters are just check out the FTC documentation
     */
    public DuckSpinner(String id){
        nasreddine = hardwareMap.get(CRServo.class, id);
    }


    /** TODO: Write a method called spin()
     * FUNCTIONALITY: spin() is a public method that can be used to spin the CRServo forwards
     *                or backwards depending on a power.
     *                HINT: Check out FTC documentation on how to set the power to a CRServo
     * PARAMETERS: a double named power
     * RETURN TYPE: none
     */

    public void spin(double power){
        nasreddine.setPower(power);
    }

}
