
package org.firstinspires.ftc.teampurplefire.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Team Purple Fire on 9/11/16.
 */
public class Drivetrain {
    DcMotor leftDrive;
    DcMotor rightDrive;
    public Drivetrain(HardwareMap hardwareMap){
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
    }

    public void drive(double left, double right){
        leftDrive.setPower(-left);
        rightDrive.setPower(-right);
    }

}
