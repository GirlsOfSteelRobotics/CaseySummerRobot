package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.JavaUtil;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import com.qualcomm.robotcore.hardware.IMU;

public final class Chassis {
    private DcMotor leftFrontDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightBackDrive = null;
    private IMU imu;

    public Chassis(HardwareMap hardwareMap) {
        leftFrontDrive = hardwareMap.get(DcMotor.class, "Front Left");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "Front Right");
        leftBackDrive = hardwareMap.get(DcMotor.class, "Back Left");
        rightBackDrive = hardwareMap.get(DcMotor.class, "Back Right");
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.LEFT;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.UP;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        imu.initialize(new IMU.Parameters(orientationOnRobot));
        setAll(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        imu.resetYaw();
    }

    public void teleOpDrive(double x, double y, double rx) {
        setAll(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        double denominator = JavaUtil.maxOfList(JavaUtil.createListWith(JavaUtil.sumOfList(JavaUtil.createListWith(Math.abs(y), Math.abs(x), Math.abs(rx))), 1));
        leftFrontDrive.setPower((y + x + rx) / denominator);
        leftBackDrive.setPower(((y - x) + rx) / denominator);
        rightFrontDrive.setPower(((y - x) - rx) / denominator);
        rightBackDrive.setPower(((y + x) - rx) / denominator);
    }

    public void goForward(int distance) {
        setAll(DcMotor.RunMode.RUN_TO_POSITION);
        leftFrontDrive.setTargetPosition(leftFrontDrive.getCurrentPosition() + distance);
        leftFrontDrive.setPower(1);
        rightFrontDrive.setTargetPosition(rightFrontDrive.getCurrentPosition() + distance);
        rightFrontDrive.setPower(1);
        leftBackDrive.setTargetPosition(leftBackDrive.getCurrentPosition() + distance);
        leftBackDrive.setPower(1);
        rightBackDrive.setTargetPosition(rightBackDrive.getCurrentPosition() + distance);
        rightBackDrive.setPower(1);

        while (leftFrontDrive.isBusy()) {

        }
    }

    public void turnTo(double angle, Telemetry telemetry) {
        double currentAngle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES) + 180;
        setAll(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        double power = .3;
        double diff = currentAngle - angle;
        if ((0 < diff && diff < 180) || diff < -180) {
            while (Math.abs(currentAngle - angle) > 2) {
                leftFrontDrive.setPower(power);
                leftBackDrive.setPower(power);
                rightFrontDrive.setPower(-power);
                rightBackDrive.setPower(-power);
                currentAngle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES) + 180;
                telemetry.addData("angle right NEYWO are you", currentAngle);
                telemetry.update();
            }
        } else {
            while (Math.abs(currentAngle - angle) > 2) {
                leftFrontDrive.setPower(-power);
                leftBackDrive.setPower(-power);
                rightFrontDrive.setPower(power);
                rightBackDrive.setPower(power);
                currentAngle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES) + 180;
                telemetry.addData("angle left NYEOW", currentAngle);
                telemetry.update();
            }
        }
        motorPower(0);
    }

    public void setAll(DcMotor.RunMode mode) {
        leftFrontDrive.setMode(mode);
        leftBackDrive.setMode(mode);
        rightFrontDrive.setMode(mode);
        rightBackDrive.setMode(mode);
    }

    public void motorPower(double power) {
        leftFrontDrive.setPower(power);
        rightFrontDrive.setPower(power);
        leftBackDrive.setPower(power);
        rightBackDrive.setPower(power);
    }

}
