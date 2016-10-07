
package org.firstinspires.ftc.teampurplefire.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teampurplefire.subsystems.BallManagementSystem;
import org.firstinspires.ftc.teampurplefire.subsystems.Drivetrain;

/**
 * Created by Team Purple Fire on 9/8/16.
 * This is the main Teleoperation Mode
 */
@TeleOp(name = "TeleOpMode", group = "Purple Fire Bot")
public class TeleOpMode extends OpMode {
    Drivetrain drivetrain;
    BallManagementSystem bms;

    @Override
    public void init() {
        drivetrain = new Drivetrain(hardwareMap);
        bms = new BallManagementSystem(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        drivetrain.drive(gamepad1.left_stick_y, gamepad1.right_stick_y);
        bms.update();

        if((gamepad1.right_trigger>0.1)&&(gamepad1.right_trigger<0.75)){
            if(bms.getShooterTarget()!=BallManagementSystem.SHOOTER_LOW_SPEED){
                bms.setShooterTarget(BallManagementSystem.SHOOTER_LOW_SPEED);
            }
            bms.setShooterActive(true);

        }
        else if(gamepad1.right_trigger>=0.75){
            if(bms.getShooterTarget()!=BallManagementSystem.SHOOTER_MED_SPEED){
                bms.setShooterTarget(BallManagementSystem.SHOOTER_MED_SPEED);
            }
            bms.setShooterActive(true);

        }
        else{
            bms.setShooterActive(false);

        }
        //Original design was going to have a servo door for holding balls until they where ready to be released
//        if(gamepad1.a){
//            bms.openDoor();
//        }else{
//            bms.closeDoor();
//        }

        if(gamepad1.left_trigger>=0.1) {
            bms.intake.setPower(1);
        }else{
            bms.intake.setPower(0);
        }
    }
}
