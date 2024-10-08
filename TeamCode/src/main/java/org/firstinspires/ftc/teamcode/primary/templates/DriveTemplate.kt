package org.firstinspires.ftc.teamcode


import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1
import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap
import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry
import com.qualcomm.robotcore.util.Range

@Config
object DriveTemplate { //Prefix for commands
    private lateinit var leftRear: DcMotor //init motor vars
    private lateinit var leftFront: DcMotor
    private lateinit var rightRear: DcMotor
    private lateinit var rightFront: DcMotor
    lateinit var opmode: OpMode //opmode var innit
    fun initDrive(opmode: OpMode){ //init motors
      leftRear = opmode.hardwareMap.get(DcMotor::class.java, "leftRear") //motor config names
      leftFront = opmode.hardwareMap.get(DcMotor::class.java,"leftFront")
      rightRear = opmode.hardwareMap.get(DcMotor::class.java, "rightRear")
      rightFront = opmode.hardwareMap.get(DcMotor::class.java, "rightFront")

      leftRear.direction = DcMotorSimple.Direction.REVERSE //motor directions
      leftFront.direction = DcMotorSimple.Direction.REVERSE
      rightRear.direction = (DcMotorSimple.Direction.FORWARD)
      rightFront.direction = (DcMotorSimple.Direction.FORWARD)
      
      this.opmode = opmode
    }
    fun updateDrive(){
            var leftBackPower: Double
            var leftFrontPower: Double
            var rightBackPower: Double
            var rightFrontPower: Double

            val strafe = opmode.gamepad1.left_stick_x.toDouble() //can change controls
            val drive = -opmode.gamepad1.left_stick_y.toDouble()
            val turn = opmode.gamepad1.right_stick_x.toDouble()
            leftBackPower = Range.clip(drive + turn - strafe, -1.0, 1.0)
            leftFrontPower = Range.clip(drive + turn + strafe, -1.0, 1.0)
            rightBackPower = Range.clip(drive - turn + strafe, -1.0, 1.0)
            rightFrontPower = Range.clip(drive - turn - strafe, -1.0, 1.0)


            leftRear.power = leftBackPower
            leftFront.power = leftFrontPower
            rightRear.power = rightBackPower
            rightFront.power = rightFrontPower

            opmode.telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftBackPower, rightBackPower)
    }

}
