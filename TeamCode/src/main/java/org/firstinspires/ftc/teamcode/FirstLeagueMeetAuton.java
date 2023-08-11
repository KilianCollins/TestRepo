package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

//@Config
@Autonomous(name = "First League Meet Auton", group = "Red")
public class FirstLeagueMeetAuton extends LinearOpMode {
    // Motors
   DcMotor frontRightMotor, frontLeftMotor, backRightMotor, backLeftMotor;

    public void runOpMode() {

        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeft");
        backRightMotor = hardwareMap.get(DcMotor.class, "rearRight");
        backLeftMotor = hardwareMap.get(DcMotor.class, "rearLeft");

        FRED detector = new FRED(hardwareMap, "webcam");
        FRED.DuckPos pos = FRED.DuckPos.ONE;

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);//correct
        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        while(!opModeIsActive()){
            pos = detector.getPosition();

        telemetry.addData("cv prediction", pos);
         telemetry.addLine("wating for stater");
        telemetry.update();

        }

        waitForStart();

        frontRightMotor.setPower(.25);
        frontLeftMotor.setPower(.25);
        backRightMotor.setPower(.25);
        backLeftMotor.setPower(.25);

        sleep(1900);

        frontRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        backLeftMotor.setPower(0);






    }



}
