package org.firstinspires.ftc.teamcode.Utilities;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.PIDWeights.DASH_DERIVATIVE_WEIGHT;
import static org.firstinspires.ftc.teamcode.Utilities.PIDWeights.DASH_INTEGRAL_WEIGHT;
import static org.firstinspires.ftc.teamcode.Utilities.PIDWeights.DASH_PROPORTIONAL_WEIGHT;

public class PID {

        private double proportionalWeight;
        private double integralWeight;
        private double derivativeWeight;

        private boolean isTuning = false; //?

        private double integralSum = 0;

        private double previousError = 0;
        private long previousTime = System.currentTimeMillis();

        public PID(double proportional, double integral, double derivative){
            this.proportionalWeight = proportional;
            this.integralWeight = integral;
            this.derivativeWeight = derivative;
        }

        public double update(double error, boolean isTuning){

            integralSum += error;


            double deltaTime = (System.currentTimeMillis() - previousTime) / 1000.0;
            double deltaError = error - previousError;
            double rateOfChange = deltaError/deltaTime;

            previousError = error;
            previousTime = System.currentTimeMillis();


            double pComponent = error;
            double iComponent = integralSum;
            double dComponent = rateOfChange;

            if (isTuning){

                pComponent *= DASH_PROPORTIONAL_WEIGHT;
                iComponent *= DASH_INTEGRAL_WEIGHT;
                dComponent *= DASH_DERIVATIVE_WEIGHT;

                multTelemetry.addData("P", pComponent);
                multTelemetry.addData("I", iComponent);
                multTelemetry.addData("D", dComponent);
            }

            else {
                pComponent *= this.proportionalWeight;
                iComponent *= this.integralWeight;
                dComponent *= this.derivativeWeight;
            }

            return pComponent + iComponent + dComponent;


        }



}
