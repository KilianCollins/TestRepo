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
@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name = "Arm Tele" , group = "testPrograms")
public class Armop extends LinearOpMode{
    //dt wheels
    private DcMotor frontRightMotor;
    private DcMotor frontLeftMotor;
    private DcMotor backRightMotor;
    private DcMotor backLeftMotor;

    private CRServo rightIntakeServo;
    private CRServo leftIntakeServo;

    //arm
    private DcMotor armMotor;
    int height = 0;
    int cone_pos = 390;
    int pole_pos = 1810;
    @Override
    public void runOpMode () throws InterruptedException {
        frontRightMotor = hardwareMap.dcMotor.get("frontRight");
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        backRightMotor = hardwareMap.dcMotor.get("rearRight");
        backLeftMotor = hardwareMap.dcMotor.get("rearLeft");
        armMotor = hardwareMap.dcMotor.get("armMotor");
        rightIntakeServo = hardwareMap.crservo.get("rightIntake");
        leftIntakeServo = hardwareMap.crservo.get("leftIntake");

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        rightIntakeServo.setDirection(DcMotorSimple.Direction.FORWARD);
        leftIntakeServo.setDirection(DcMotorSimple.Direction.FORWARD);
        //arm
        armMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //arm motor(s)




        //arm motor speed// 60 rpm motor is the one that works
        double arm_speed = 0.75;
        double drive;
        double strafe;
        double rotate;

        double front_left_speed;
        double rear_left_speed;
        double front_right_speed;
        double rear_right_speed;
        // int arm_added_position = 100;
        waitForStart();

        //armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        while (opModeIsActive()){ //haddons requests, Stick drive
            drive = gamepad1.left_stick_y;
            strafe = gamepad1.right_stick_x;
            rotate = -gamepad1.left_stick_x;
            front_left_speed = ((drive - strafe) + rotate)*0.5;
            rear_left_speed = ((drive + strafe) + rotate)*0.5;
            front_right_speed = ((drive + strafe) - rotate)*0.5;
            rear_right_speed = ((drive - strafe) - rotate)*0.5;


            frontRightMotor.setPower(limit(front_right_speed));
            frontLeftMotor.setPower(limit(front_left_speed));
            backRightMotor.setPower(limit(rear_right_speed));
            backLeftMotor.setPower(limit(rear_left_speed));

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

            if(gamepad1.left_trigger > 0){
                height += gamepad1.left_trigger * 10;
            }
            else if(gamepad1.right_trigger > 0){
                height -= gamepad1.right_trigger * 10;
                if (height < 0){
                    height = 0;
                }
            }

            else if(gamepad1.right_bumper){
                height = cone_pos;
            }
            else if (gamepad1.left_bumper){
                height = pole_pos;

            }
            armMotor.setTargetPosition(height);
            armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            armMotor.setPower(arm_speed);


            telemetry.addData("arm pos: ", armMotor.getCurrentPosition());
            telemetry.addData("height", height);
            telemetry.update();



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