
package org.firstinspires.ftc.teampurplefire.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Timer;

/**
 * Created by Team Purple Fire on 9/11/16.
 * This subsystem controls both the intake and the shooter
 */
public class BallManagementSystem {
    public static final double KI1 = 0.02;
    public static final double KI2 = 0.001;
    public static final double OPEN_STATE = 0.25;
    public static final double CLOSE_STATE = 0.25;
    public static final double SHOOTER_LOW_SPEED = 0.35;
    public static final double SHOOTER_MED_SPEED = 0.65;
    public static final double SHOOTER_HIGH_SPEED= 1;
    public DcMotor intake;
    boolean shooterActive;
    DcMotor shooter1;
    DcMotor shooter2;
    Timer timer;
//    Servo shooterDoor;
    double shooterTarget = 1;
    Telemetry telemetry;

    long lastRun = 0;

    public BallManagementSystem(HardwareMap hardwareMap, Telemetry telemetry){
        intake = hardwareMap.dcMotor.get("intake");
        shooter1 = hardwareMap.dcMotor.get("shooter1");
        shooter2 = hardwareMap.dcMotor.get("shooter2");
        shooter1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooter2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        this.telemetry = telemetry;
//        shooterDoor = hardwareMap.servo.get("door");

    }

    /**
     * This method manages
     */
    public void update(){

        if(System.currentTimeMillis() - lastRun > 50) {
            double power = shooter1.getPower();
            lastRun = System.currentTimeMillis();
            if(shooterActive && power < shooterTarget){
                power += KI1;
            }else if(!shooterActive){
                power -= KI2;
            }
            if (power > shooterTarget) {
                power = shooterTarget;
            } else if (power < 0) {
                power = 0;
            }
            shooter1.setPower(power);
            shooter2.setPower(-power);
            telemetry.addData("power", power);
            telemetry.addData("target", shooterTarget);
        }
    }


    public void setShooterActive(boolean shooterActive) {
        this.shooterActive = shooterActive;
    }

    public void setShooterTarget(double shooterTarget) {
        this.shooterTarget = shooterTarget;
    }

    public double getShooterTarget() {
        return shooterTarget;
    }

/*    public void openDoor(){
//        shooterDoor.setPosition(OPEN_STATE);
    }

    public void closeDoor(){
//        shooterDoor.setPosition(CLOSE_STATE);
    }*/

}
