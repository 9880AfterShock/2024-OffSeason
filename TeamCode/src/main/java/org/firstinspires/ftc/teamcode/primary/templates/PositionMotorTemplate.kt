package org.firstinspires.ftc.teamcode

//changing from lift code

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor

@Config
object PositionMotorTemplate { //Prefix for commands
    private lateinit var motor: DcMotor //Init Motor Var
    var pos = 0.0 //starting Position
    @JvmField
    var positions = doubleArrayOf(0.1, 0.2, 0.3, 0.4, 0.5, 0.6) //change your own //only first 2 ([0][1]) work rn
    var currentPos = 0 //position in array. you can change the starting one
    val encoderTicks = 384.5 //calculate your own ratio
    private var leftBumperCurrentlyPressed = false
    private var leftBumperPreviouslyPressed = false
    private var rightBumperCurrentlyPressed = false
    private var rightBumperPreviouslyPressed = false
    lateinit var opmode: OpMode //opmode var innit
    var motorMode: DcMotor.RunMode = DcMotor.RunMode.RUN_TO_POSITION //set motor mode
    fun initMotor(opmode: OpMode){ //init motors
        motor = opmode.hardwareMap.get(DcMotor::class.java, "motorTemplate") //config name
        motor.targetPosition = (pos*encoderTicks).toInt()
        motor.mode = motorMode
        this.opmode = opmode
    }
    fun updateMotor() {
//can change controls
        leftBumperCurrentlyPressed = opmode.gamepad1.left_bumper
        rightBumperCurrentlyPressed = opmode.gamepad1.right_bumper

        // If the button state is different than what it was, then act
        if (!(leftBumperCurrentlyPressed && rightBumperCurrentlyPressed)) {
            if (leftBumperCurrentlyPressed && !leftBumperPreviouslyPressed) {
                currentPos =- 1
                if (currentPos < 0) {
                    currentPos = 0
                }
            } else {
                if (rightBumperCurrentlyPressed && !rightBumperPreviouslyPressed) {
                    currentPos =+ 1
                    if (currentPos > positions.size + 1) {
                        currentPos = positions.size + 1
                    }
                }
            }
        }
        leftBumperPreviouslyPressed = leftBumperCurrentlyPressed
        rightBumperPreviouslyPressed = rightBumperCurrentlyPressed


        pos = positions[currentPos]

        motor.setPower(1.0) //turn motor on
        motor.targetPosition = (pos*encoderTicks).toInt()
        opmode.telemetry.addData("Motor list position", currentPos) //Set telemetry
        opmode.telemetry.addData("Motor target position", pos) //Set telemetry
        opmode.telemetry.addData("left currently press", leftBumperCurrentlyPressed) //Set telemetry
        opmode.telemetry.addData("left past press", leftBumperPreviouslyPressed) //Set telemetry
        opmode.telemetry.addData("right currently press", rightBumperCurrentlyPressed) //Set telemetry
        opmode.telemetry.addData("right past press", rightBumperPreviouslyPressed) //Set telemetry
    }

}