package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Chassis;
import org.firstinspires.ftc.teamcode.subsystems.Vincent;

@TeleOp
public class FirstAndroidTeleOp extends LinearOpMode {
    @Override
    public void runOpMode(){
        Chassis chassis = new Chassis(hardwareMap);
        Vincent vincent = new Vincent(hardwareMap);
        waitForStart();
        while(opModeIsActive()){
            chassis.teleOpDrive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);
            if(gamepad1.y){
                vincent.goToUpPos();
            } else if (gamepad1.a) {
                vincent.goToDownPos();
            }
        }
    }
}