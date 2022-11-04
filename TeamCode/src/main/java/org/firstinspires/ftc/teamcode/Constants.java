/*

package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;


import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class Constants extends Subsystems  {


    public class ReallyReallySlowDriveCommand extends DefaultDriveCommand {
        public ReallyReallySlowDriveCommand(Drivetrain drive, GamepadEx driverGamepad) {
            super(drive, driverGamepad);
            this.multiplier = 0.25;
            this.rotMultiplier = 0.25;
        }
    }







}

    public class SubsystemConstants {
        public static int DEGREES_PER_ROTATION = 360;

        public static class Arm {
            /*arm variables position for specifyed positon


            //pos to hold while bumpers not pressed
            public double front_hover_pos = 100;
            public double back_hover_pos = -100;

            //pick up
            public double front_pick_up_cone_pos =100 ; //from ground, hover 1 inch above cone top then have button comand that had arm press down on to arm
            public double back_pick_up_cone_pos = 100 ; //from ground, same as above /\

            //drop off, might also duble as hover pos idk
            public double front_mid_pole_drop_pos = 100; // set it to have cone base hover 1 inch obove pole
            public double back_mid_pole_drop_pos = 100; // same as above /\

            // this holds the distance val that when pressed the arm will "press down" from the hover position
            // and then when button is not pressed arm will return to hover pos
            public double front_temp_press_down = 100;
            public double back_temp_press_down= 100;


            /*

            example code

            public static int LIFT_RESTING_POSITION = 0;
            public static int LIFT_LOW_POSITION_AUTON = 200;
            public static int LIFT_MID_POSITION = 465;
            public static int LIFT_MID_AUTON = 650;
            public static int LIFT_MID_POSITION_TELE = 800;
            public static int LIFT_HIGH_POSITION = 1300;

            public static double LIFT_UP_SPEED = 0.6;
            public static double LIFT_DOWN_SPEED = -0.3;

            public static double LIFT_TICKS_PER_ROTATION = 384.5; //383.6



            public static PIDFCoefficients LIFT_PID_COEFFICIENTS = new PIDFCoefficients(0.011, 0.00008, 0, 0);
            //              KILIAN SLOWLY MAKE THIS SMALLER UNTIL IT DOESN'T OSCILLATE ANYMORE ^ (it was at 0.02 at your scrimmage yesterday)
            //0.15 og, ocillations stopped, motor is locking, tried reversing the direction the motor, motor still
            public static int LIFT_TOLERANCE = 3;

            public static String ARM_MOTOR_ID = "arm";
            public static String DELIVERY_MOTOR_ID = "delivery";
            //public static String CAP_SERVO_ID = "capServo";
        }

        public static class Intake {
            public static double INTAKE_SPEED = 1;
            public static double OUTTAKE_SPEED = -1;

            public static String INTAKE_MOTOR_ID = "intake";
            public static String DISTANCE_SENSOR_ID = "distance";
        }

        public static class Wheel {
            public static double MECHANUM_WHEEL_RAIDOUS = 10;
            public static double GEAR_RATIO = 1;
            public static double TRACK_WIDTH = 1;



        }



}


*/