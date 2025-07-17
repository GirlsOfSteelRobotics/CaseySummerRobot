package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Vincent {
    private final DcMotor vincent;
    public Vincent(HardwareMap hardwareMap){
        vincent = hardwareMap.get(DcMotor.class, "Vincent Motor");
        vincent.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void goToDownPos(){
        vincent.setPower(1);
        vincent.setTargetPosition(0);
        vincent.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void goToUpPos(){
        vincent.setPower(-1);
        vincent.setTargetPosition(-4000);
        vincent.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
