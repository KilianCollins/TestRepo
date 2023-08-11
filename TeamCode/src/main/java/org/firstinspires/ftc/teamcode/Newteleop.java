//gamepad 1 left stick y = forward
//gamepad 1 left stick x = strafe
package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.FLOAT;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
/**
 * Created by Alyssa on 12-05-19
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name = "New Tele" , group = "testPrograms")
public class Newteleop extends LinearOpMode{
    //dt wheels
    private DcMotor frontRightMotor;
    private DcMotor frontLeftMotor;
    private DcMotor backRightMotor;
    private DcMotor backLeftMotor;

    //motor power
    // double motor_power = 0.5;

    //arm
    private DcMotor armMotor;
    int top = 2500;
    int bottom = 100;
    //intake
    private CRServo rightIntakeServo;
    private CRServo leftIntakeServo;


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
        //armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //arm motor(s)


        //armlift = (DcMotor) hardwareMap.get(DcMotor.class, "armlift");
        //armlift2 = (DcMotor) hardwareMap.get(DcMotor.class,"armlift2");

        // hopefull it makes it hover, it does not stop it but it slower its decent
        // armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //intake
        rightIntakeServo.setDirection(DcMotorSimple.Direction.FORWARD);
        leftIntakeServo.setDirection(DcMotorSimple.Direction.FORWARD);


        //Drive Variables
        double drive;
        double strafe;
        double rotate;
        //arm variables


        //drive variables
        double front_left_speed;
        double rear_left_speed;
        double front_right_speed;
        double rear_right_speed;

        //arm motor speed// 60 rpm motor is the one that works
        double arm_speed = 0.65;

        //reseting encoders so the ptog can find its home
        // resetArmEncoder();
        // int arm_added_position = 100;
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
            front_left_speed = ((drive - strafe) + rotate)*0.5;
            rear_left_speed = ((drive + strafe) + rotate)*0.5;
            front_right_speed = ((drive + strafe) - rotate)*0.5;
            rear_right_speed = ((drive - strafe) - rotate)*0.5;

//-----------------------------------Gamepad 1 Start------------------------------------------------
            //Drive
            frontRightMotor.setPower(limit(front_right_speed));
            frontLeftMotor.setPower(limit(front_left_speed));
            backRightMotor.setPower(limit(rear_right_speed));
            backLeftMotor.setPower(limit(rear_left_speed));
            //arm

//------------------------------------Gamepad 1 End-------------------------------------------------


//             i have to reset encoders during init
            //armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            // IT WOOOOOOOOOOOOOOOOOOOORKSSSSSSSSS YSDFSJKRF,SJKBFG;SKJDNLFS!!!!!!!!

            // armMotor.setPower(0.65);
            if(gamepad1.right_bumper){
                armMotor.setPower(0.65);
                armMotor.setTargetPosition(100);
                armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            }
            if (gamepad1.left_bumper){
                armMotor.setPower(0.65);
                armMotor.setTargetPosition(2500);
                armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            else{
                armMotor.setPower(0);
                //armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                //armMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            }



       /*
            if(gamepad1.left_bumper){
                got_to_top_pos();
            }

            else{
                armMotor.setZeroPowerBehavior(FLOAT);
            }
                 */
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
    ////////
    public void resetArmEncoder() {
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    ////////
    public void killarmpower() {
        armMotor.setZeroPowerBehavior(FLOAT);
    }
    //////////
   /* public  void  got_to_bottom_pos(){
        //armMotor.setZeroPowerBehavior(BRAKE);
       // int bottom = 100;
        armMotor.setTargetPosition(250);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor.setPower(0.65);
        while(armMotor.isBusy() && opModeIsActive()) {
        }
        ///might hAVE to remove\/
        armMotor.setPower(0);
    }

    public  void  got_to_top_pos(){
        //armMotor.setZeroPowerBehavior(BRAKE);
        armMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        armMotor.setTargetPosition(100);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor.setPower(0.65);
        while(armMotor.isBusy() && opModeIsActive()) {
        }
        ///might hAVE to remove\/
        armMotor.setPower(0);
    }*/


}

