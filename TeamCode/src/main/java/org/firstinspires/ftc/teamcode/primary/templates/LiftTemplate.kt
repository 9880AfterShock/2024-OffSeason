package org.firstinspires.ftc.teamcode


import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor

@Config
object LiftTemplate { //Prefix for commands
    private lateinit var lift: DcMotor //Init Motor Var
    var pos = 0.0 //starting Position
    var currentSpeed = 0.0 //Starting speed, WHY ARE YOU MAKING A FALLING LIFT???
    @JvmField
    var speed = 0.01 //update speed
    val encoderTicks = 384.5 //calculate your own ratio
    @JvmField
    var minPos = 0.0 //bottom position
    @JvmField
    var maxPos = 2.0 //top position
    lateinit var opmode: OpMode //opmode var innit
    var motorMode: DcMotor.RunMode = DcMotor.RunMode.RUN_TO_POSITION //set motor mode
    fun initLift(opmode: OpMode){ //init motors
        lift = opmode.hardwareMap.get(DcMotor::class.java, "liftTemplate") //config name
        lift.targetPosition = (pos*encoderTicks).toInt()
        lift.mode = motorMode
        this.opmode = opmode
    }
    fun updateLift(){
//can change controls
        if (!(opmode.gamepad1.dpad_up && opmode.gamepad1.dpad_down)) {
            if (opmode.gamepad1.dpad_up) {
                currentSpeed = speed
            }
            else
                if (opmode.gamepad1.dpad_down) {
                    currentSpeed = speed
                } else {
                    currentSpeed = 0.0
                }
        }
        pos = (pos + currentSpeed)
        if (pos<maxPos) {
            pos = maxPos
        }
        if (pos>minPos) {
            pos = minPos
        }
        lift.setPower(1.0) //turn motor on
        lift.targetPosition = (pos*encoderTicks).toInt()
        opmode.telemetry.addData("Lift target position", pos) //Set telemetry
    }

}