//gamepad 1 left stick y = forward
//gamepad 1 left stick x = strafe
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Alyssa on 12-05-19
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name = "2 Wheel Tele" , group = "testPrograms")
public class TwoWheelTeleOp extends LinearOpMode{
    private DcMotor backRightMotor;
    private DcMotor backLeftMotor;

    //arm
    private DcMotor armMotor;

    private CRServo rightIntakeServo;
    private CRServo leftIntakeServo;


    @Override
    public void runOpMode () throws InterruptedException {

        backRightMotor = hardwareMap.dcMotor.get("rearRight");
        backLeftMotor = hardwareMap.dcMotor.get("rearLeft");

        armMotor = hardwareMap.dcMotor.get("armMotor");

        rightIntakeServo = hardwareMap.crservo.get("rightIntake");
        leftIntakeServo = hardwareMap.crservo.get("leftIntake");


        //directions
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        //arm motor(s)
        armMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        rightIntakeServo.setDirection(DcMotorSimple.Direction.FORWARD);
        leftIntakeServo.setDirection(DcMotorSimple.Direction.FORWARD);







        //Drive Variables
        double drive;
        double rotate;

        double rear_left_speed;
        double rear_right_speed;

        //arm motor speed// 60 is the one that works
        double arm_speed = 0.75;

        waitForStart();
        while (opModeIsActive()){

            drive = gamepad1.right_stick_y;
            rotate = -gamepad1.left_stick_x;

            rear_right_speed = rotate  + drive;
            rear_left_speed = rotate - drive;

//-----------------------------------Gamepad 1 Start------------------------------------------------
            //Drive
            backRightMotor.setPower(limit(rear_right_speed));
            backLeftMotor.setPower(limit(rear_left_speed));
//------------------------------------Gamepad 1 End-------------------------------------------------

            if(gamepad1.right_bumper){
                armMotor.setPower(arm_speed);
            } else if(gamepad1.left_bumper){
                armMotor.setPower(-arm_speed);
            } else{
                armMotor.setPower(0);
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