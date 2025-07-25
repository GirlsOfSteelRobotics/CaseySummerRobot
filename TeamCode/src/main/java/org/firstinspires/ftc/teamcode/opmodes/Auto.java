package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Chassis;
import org.firstinspires.ftc.teamcode.subsystems.Vincent;

@Autonomous
public class Auto extends LinearOpMode {
    @Override
    public void runOpMode() {
        Chassis chassis = new Chassis(hardwareMap);
        Vincent vincent = new Vincent(hardwareMap);
        waitForStart();

        chassis.turnTo(270, telemetry);
        chassis.goForward(500);
        chassis.turnTo(243, telemetry);
    }
}