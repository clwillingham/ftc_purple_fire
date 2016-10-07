
package org.firstinspires.ftc.teampurplefire.opmodes;

import android.util.StateSet;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teampurplefire.subsystems.Drivetrain;

/**
 * Created by Team Purple Fire on 9/8/16.
 * This is the main Autonomous Mode
 */

enum State {
    INIT, STEP1, STEP2, STEP3, STEP4, STEP5
}

@Autonomous(name = "AutoOPMode", group = "Purple Fire Bot")
public class AutoOPMode extends OpMode {
    Drivetrain drivetrain;
    State state_s;

    long lastRun = 0;

    @Override
    public void init() {
        drivetrain = new Drivetrain(hardwareMap);
        state_s = State.INIT;
    }

    @Override
    public void loop() {
        switch(state_s){
            case INIT:
                lastRun = System.currentTimeMillis();
                state_s = State.STEP1;
                break;
            case STEP1:
                driveForward(50);
                if(System.currentTimeMillis() - lastRun > 1000) {
                    lastRun = System.currentTimeMillis();
                    state_s = State.STEP2;
                }
                break;
            case STEP2:
                driveStop();
                break;
            case STEP3:
                break;
            case STEP4:
                break;
            case STEP5:
                break;
            default:
                driveStop();
                break;
        }

  }

    public void driveForward(double power){
        drivetrain.drive(-power, -power);
    }

    public void driveBackwards(double power){
        drivetrain.drive(power, power);
    }

    public void driveRight(double power){
        drivetrain.drive(-power, power);
    }

    public void driveLeft(double power){
        drivetrain.drive(power, -power);
    }


    public void driveStop(){
        drivetrain.drive(0, -0);
    }
}
