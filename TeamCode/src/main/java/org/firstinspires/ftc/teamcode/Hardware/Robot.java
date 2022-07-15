package org.firstinspires.ftc.teamcode.Hardware;

public class Robot {
    public Mecanum drivetrain;
    public DuckSpinner duckSpinner;
    public Dropper dropper;
    public AveryClaw claw;
    public Sweeper sweeper;
    public DylanArm arm;

    public Robot(String person){
        drivetrain = new Mecanum();
        switch (person) {
            case "luca":
                dropper = new LucaDropper("dropper");
                duckSpinner = new LucaDuckSpinner("duck", "duck2");
                sweeper = new LucaSweeper("sweeper");
                break;
            case "avery":
                dropper = new AveryDropper("leftDrop", "rightDrop");
                duckSpinner = new GenericDuckSpinner("duck");
                claw = new AveryClaw("leftClaw", "rightClaw");
                sweeper = new AverySweeper("sweeper");
                break;
            case "dylan":
                duckSpinner = new GenericDuckSpinner("duck");
                dropper = new DylanDropper("spatula");
                arm = new DylanArm("arm");
                break;
            case "forrester":
                dropper = new ForresterDropper("leftdropper", "rightdropper");
                duckSpinner = new GenericDuckSpinner("duck");
                break;
        }
    }
}
