package org.firstinspires.ftc.teamcode


import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor

@Config
object DriveTemplate { //Prefix for commands
    private lateinit var leftRear: DcMotor //init motor vars
    private lateinit var leftFront: DcMotor
    private lateinit var rightRear: DcMotor
    private lateinit var rightFront: DcMotor
  
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
      leftRear = hardwareMap.get(DcMotor::class.java, "leftRear") //motor config names
      leftFront = hardwareMap.get(DcMotor::class.java,"leftFront")
      rightRear = hardwareMap.get(DcMotor::class.java, "rightRear")
      rightFront = hardwareMap.get(DcMotor::class.java, "rightFront")

      leftRear.direction = DcMotorSimple.Direction.REVERSE //motor directions
      leftFront.direction = DcMotorSimple.Direction.REVERSE
      rightRear.direction = (DcMotorSimple.Direction.FORWARD)
      rightFront.direction = (DcMotorSimple.Direction.FORWARD)
      
      this.opmode = opmode
    }
    fun updateLift(){
            var leftBackPower: Double
            var leftFrontPower: Double
            var rightBackPower: Double
            var rightFrontPower: Double

            val strafe = gamepad1.left_stick_x.toDouble() //can change controls
            val drive = -gamepad1.left_stick_y.toDouble()
            val turn = gamepad1.right_stick_x.toDouble()
            leftBackPower = Range.clip(drive + turn - strafe, -1.0, 1.0)
            leftFrontPower = Range.clip(drive + turn + strafe, -1.0, 1.0)
            rightBackPower = Range.clip(drive - turn + strafe, -1.0, 1.0)
            rightFrontPower = Range.clip(drive - turn - strafe, -1.0, 1.0)


            leftRear.power = leftBackPower
            leftFront.power = leftFrontPower
            rightRear.power = rightBackPower
            rightFront.power = rightFrontPower

            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftBackPower, rightBackPower)
    }

}
