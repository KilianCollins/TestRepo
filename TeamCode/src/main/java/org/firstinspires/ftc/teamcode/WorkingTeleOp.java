//gamepad 1 left stick y = forward
//gamepad 1 left stick x = strafe
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
/**
 * Created by Alyssa on 12-05-19
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name = "Working Tele" , group = "testPrograms")
public class WorkingTeleOp extends LinearOpMode{
    //dt wheels
    private DcMotor frontRightMotor;
    private DcMotor frontLeftMotor;
    private DcMotor backRightMotor;
    private DcMotor backLeftMotor;

    //arm
    private DcMotor armMotor;
    //intake
    private CRServo rightIntakeServo;
    private CRServo leftIntakeServo;

    //idk what this is
   //private DcMotor armlift, armlift2;





    @Override
    public void runOpMode () throws InterruptedException {
        frontRightMotor = hardwareMap.dcMotor.get("frontRight");
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        backRightMotor = hardwareMap.dcMotor.get("rearRight");
        backLeftMotor = hardwareMap.dcMotor.get("rearLeft");

        armMotor = hardwareMap.dcMotor.get("armMotor");

        rightIntakeServo = hardwareMap.crservo.get("rightIntake");
        leftIntakeServo = hardwareMap.crservo.get("leftIntake");


        //directions --motor
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        //arm
        armMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //arm motor(s)


        //armlift = (DcMotor) hardwareMap.get(DcMotor.class, "armlift");
        //armlift2 = (DcMotor) hardwareMap.get(DcMotor.class,"armlift2");

            // hopefull it makes it hover, it does not stop it but it slower its decent
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //intake
        rightIntakeServo.setDirection(DcMotorSimple.Direction.FORWARD);
        leftIntakeServo.setDirection(DcMotorSimple.Direction.FORWARD);










        //Drive Variables
        double drive;
        double strafe;
        double rotate;

        /*arm variables position for specifyed positon
            //pick up
        double front_pick_up_cone_pos; //from ground, hover 1 inch above cone top then have button comand that had arm press down on to arm
        double back_pick_up_cone_pos; //from ground, same as above /\

            //drop off, & zero power behavior AKA set pos
        double front_mid_pole_drop_pos; // set it to have cone base hover 1 inch obove pole
        double back_mid_pole_drop_pos; // same as above /\

            // this holds the distance val that when pressed the arm will "press down" from the hover position
            // and then when button is not pressed arm will return to hover pos
        double front_temp_press_down;
        double back_temp_press_down;*/

        //drive variables
        double front_left_speed;
        double rear_left_speed;
        double front_right_speed;
        double rear_right_speed;

        //arm motor speed// 60 rpm motor is the one that works
        double arm_speed = 1;

        int f = 100;
        waitForStart();
        while (opModeIsActive()){ //haddons requests, Stick drive
            drive = gamepad1.left_stick_y;
            strafe = gamepad1.right_stick_x;
            rotate = -gamepad1.left_stick_x;

            // set postions
            //armMotor (1120,2);
            /*
             maybe set arm pos needs this
             //arm vars initialized
            bottom_pos = gamepad1.right_bumper;
            mid_pos = gamepad1.right_bumper;
            top_pos = gamepad1.right_bumper; */

            //mightneed clarifying parenthaseise
            front_left_speed = (drive - strafe) + rotate;
            rear_left_speed = (drive + strafe) + rotate;
            front_right_speed = (drive + strafe) - rotate;
            rear_right_speed = (drive - strafe) - rotate;

//-----------------------------------Gamepad 1 Start------------------------------------------------
            //Drive
            frontRightMotor.setPower(limit(front_right_speed));
            frontLeftMotor.setPower(limit(front_left_speed));
            backRightMotor.setPower(limit(rear_right_speed));
            backLeftMotor.setPower(limit(rear_left_speed));
            //arm

//------------------------------------Gamepad 1 End-------------------------------------------------
            //arm motor set specified pos, ineed to
            /*if(gamepad1.right_bumper){
                armMotor.setPower(bottom_pos);// bottom poss needs to be set to a specific amount of tics, idk how to get ticks form motor ask boen or alyssa
                armMotor.getMode(armMotor);//needs to get the current positon of the motor and then
                //how do i get ticks? and use them

            }*/
//             i have to reset encoders during init
            //armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            armMotor.setTargetPosition(100);// set the 100
            armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            f += gamepad1.right_trigger * 0.25;

            if(gamepad1.right_bumper){
                armMotor.setTargetPosition(100);
                armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armMotor.setPower(0.5);


               //double e2 += arm_speed * 0.25
              /*  armMotor.setTargetPosition(100);

                int instant_arm_pos = armMotor.getCurrentPosition();
                // get motor resolution
                armlift2.setTargetPosition(armlift.getTargetPosition());
                //ask boen why there needs to be 2 arm/lift variables

                //armMotor.setTicks*/


            }



           /*  else {
                //armMotor.setPower(0);
                armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); */


             if(gamepad1.left_bumper) {
                 armMotor.setPower(-arm_speed);
            }


            ////////////////////////////
            if (gamepad1.y){
                rightIntakeServo.setPower(1);
                leftIntakeServo.setPower(1);

            }
            ////////////////////////////////
            else if (gamepad1.x) {
                rightIntakeServo.setPower(-1);
                leftIntakeServo.setPower(-1);
            }
            ////////////////////////////////////
            else{
                rightIntakeServo.setPower(0);
                leftIntakeServo.setPower(0);
            }


            idle();
        }
    }
    public double limit(double number){
        if(number < -1.0){
            return -1.0;
        }
        else if(number > 1){
            return 1;
        }
        else{
            return number;
        }
    }
}