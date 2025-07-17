package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.JavaUtil;

public final class Chassis {
    private DcMotor leftFrontDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightBackDrive = null;
    public Chassis(HardwareMap hardwareMap){
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "Front Left");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "Front Right");
        leftBackDrive = hardwareMap.get(DcMotor.class, "Back Left");
        rightBackDrive = hardwareMap.get(DcMotor.class, "Back Right");
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
    }
    public void teleOpDrive(double x, double y, double rx){
       double denominator = JavaUtil.maxOfList(JavaUtil.createListWith(JavaUtil.sumOfList(JavaUtil.createListWith(Math.abs(y), Math.abs(x), Math.abs(rx))), 1));
        leftFrontDrive.setPower((y + x + rx) / denominator);
        leftBackDrive.setPower(((y - x) + rx) / denominator);
        rightFrontDrive.setPower(((y - x) - rx) / denominator);
        rightBackDrive.setPower(((y + x) - rx) / denominator);
    }
}