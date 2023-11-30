/*
    Copyright (c) 2022 Atomic Robotics (https://atomicrobotics3805.org)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see https://www.gnu.org/licenses/.
*/
package org.firstinspires.ftc.teamcode

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.util.ElapsedTime
import com.qualcomm.robotcore.util.Range
import com.qualcomm.robotcore.util.RobotLog
import org.atomicrobotics3805.cflib.Command
import org.atomicrobotics3805.cflib.CommandScheduler
import org.atomicrobotics3805.cflib.TelemetryController
import org.atomicrobotics3805.cflib.hardware.MotorEx
import org.atomicrobotics3805.cflib.sequential
import org.atomicrobotics3805.cflib.subsystems.PowerMotor
import org.atomicrobotics3805.cflib.subsystems.Subsystem
import org.atomicrobotics3805.cflib.utilCommands.CustomCommand
import org.atomicrobotics3805.cflib.utilCommands.TelemetryCommand
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.math.sign

//19.2:1 IS MOTOR RATIO
//20:100 IS GEAR RATIO (1:5 doi)
//1:360 ROUNDS:DEGREES RATIO
//x is input y is output
//    [(19.2x)5]/360=y

/**
 * This class is an example of a lift controlled by a single motor. Unlike the Intake example object, it can use
 * encoders to go to a set position. Its first two commands, toLow and toHigh, do just that. The start command turns
 * the motor on and lets it spin freely, and the reverse command does the same but in the opposite direction. The stop
 * command stops the motor. These last three are meant for use during the TeleOp period to control the lift manually.
 * To use this class, copy it into the proper package and change the first eight constants (COUNTS_PER_INCH is fine as
 * is).
 */
//THIS IS THE 2 ARMED THING THAT LIFTS UP THE CLAW
@Config
@Suppress("Unused", "MemberVisibilityCanBePrivate")
object Lift : Subsystem {

    var NAME_1 = "LeftArm"
    var NAME_2 = "RightArm"
    var LeftArm = DcMotorSimple.Direction.FORWARD
    var RightArm = DcMotorSimple.Direction.REVERSE
    @JvmField
    var SPEED = 0.35
    var UP = 60.0
    var FARUP = 120.0
    var DOWN = 0.0
    var GearRatioMotor = 50.9
    var GearRatioArm = 5
    var encoderTicks = 28
//    @JvmField
    var ChangeAmount = 0.0 //unused but needed for clarity in code =]
//    @JvmField
//    var UpAmount = 0.1
//    @JvmField //DownAmount is -UpAmount
//    var DownAmount = -0.1

    var targetPosition = 0 //unused but needed for clarity in code =]


    val Up: Command
        get() = sequential {
            +CustomCommand(_start={targetPosition = (encoderTicks * GearRatioMotor * UP * GearRatioArm / 360.0).toInt()})
        }
           // CustomCommand(_start={targetPosition = (encoderTicks * GearRatioMotor * UP * GearRatioArm / 360.0).toInt()})
            //MotorToPosition(
            //    ArmMotor,
            //    (encoderTicks * GearRatioMotor * UP * GearRatioArm / 360.0).toInt(),
            //    SPEED
            //)
    val Down: Command
        get() = sequential {
            +CustomCommand(_start={targetPosition = (encoderTicks * GearRatioMotor * DOWN * GearRatioArm / 360.0).toInt()})
          //  +MotorToPosition(
          //      ArmMotor,
           //     (encoderTicks * GearRatioMotor * DOWN * GearRatioArm / 360.0).toInt(),
            //    SPEED
         //   )
            //+PowerMotor(ArmMotor, 0.0)
        }
    val FarUp: Command
        get() =
            CustomCommand(_start={targetPosition = (encoderTicks * GearRatioMotor * FARUP * GearRatioArm / 360.0).toInt()})
           // MotorToPosition(
              //  ArmMotor,
              //  (encoderTicks * GearRatioMotor * FARUP * GearRatioArm / 360.0).toInt(),
              //  SPEED
           // )


   val StartUp: Command
       get() =
           PowerMotor(LiftMotor, SPEED)

    val StartDown: Command
        get() =
            PowerMotor(LiftMotor, -SPEED)
    val StopMove: Command
        get() =
            PowerMotor(LiftMotor, 0.0)





    val LiftMotor: MotorEx = CustomMotorExGroup(
        MotorEx(NAME_1, MotorEx.MotorType.GOBILDA_YELLOWJACKET, 50.9, LeftArm),
        MotorEx(NAME_2, MotorEx.MotorType.GOBILDA_YELLOWJACKET, 50.9, RightArm)
    )


    override fun initialize() {
        targetPosition = 0
        LiftMotor.initialize()
        LiftMotor.mode= DcMotor.RunMode.STOP_AND_RESET_ENCODER
        LiftMotor.mode= DcMotor.RunMode.RUN_USING_ENCODER
        LiftMotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
       // CommandScheduler.scheduleCommand(MotorToPosition(ArmMotor, SPEED))
    }
    @Suppress("MemberVisibilityCanBePrivate")
    open class MotorToPosition(
        protected val motor: MotorEx,
        protected var speed: Double,
        override val requirements: List<Subsystem> = arrayListOf(),
        override val interruptible: Boolean = true,
        protected val minError: Int = 70,
        protected val kP: Double = 0.005,
        protected val logData: Boolean = false
    ) : Command() {

        protected val timer = ElapsedTime()
        protected val positions: MutableList<Int> = mutableListOf()
        protected val savesPerSecond = 10.0
        protected var saveTimes: MutableList<Double> = mutableListOf()
        protected val minimumChangeForStall = 20.0
        protected var error: Int = 0
        protected var direction: Double = 0.0
        override val _isDone: Boolean
            get() = false //abs(error) < minError



        /**
         * Sets the motor's mode to RUN_USING_ENCODER, sets the error to the difference between the target and current
         * positions, and sets the direction to the sign of the error
         */
        override fun start() {
            motor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
            error = targetPosition + motor.currentPosition
            direction = sign(error.toDouble())
        }

        /**
         * Updates the error and direction, then calculates and sets the motor power
         */
        override fun execute() {
            //targetPosition += ChangeAmount.toInt()
            error = targetPosition - motor.currentPosition
            direction = sign(error.toDouble())
            var power = kP * abs(error) * speed * direction
            if (targetPosition == 0 && abs(error) < minError) power = 0.0
            motor.power = Range.clip(power, -min(speed, 1.0), min(speed, 1.0))
            TelemetryController.telemetry.addData("error:", error)
            TelemetryController.telemetry.addData("mot pos", motor.currentPosition)
            TelemetryController.telemetry.addData("targ mot pos", targetPosition)
            cancelIfStalled()
            if(logData) {
                val data = "Power: " + Range.clip(power, -min(speed, 1.0), min(speed, 1.0)) + ", direction: " + direction + ", error: " + error
                RobotLog.i("MotorToPosition %s", data)
            }
        }

        /**
         * Stops the motor
         */
        override fun end(interrupted: Boolean) {
            //motor.targetPosition = motor.currentPosition
            motor.power = 0.0
        }

        /**
         * Starts by determining whether a stall check has been performed in the past 1 / savesPerSecond seconds. If not,
         * It then compares the speed from the previous check to the current speed. If there's a change of at least
         * minimumChangeForStall times, then the motor is stalled. It sends out a telemetry message and cancels the command.
         */
        fun cancelIfStalled() {
            val lastTime = if (saveTimes.size == 0) 0.0 else saveTimes.last()
            val roundedLastTime = (lastTime * savesPerSecond).roundToInt() / savesPerSecond
            if (timer.seconds() - roundedLastTime < 1 / savesPerSecond) {
                if (positions.size > 1) {
                    val lastSpeed = abs(positions[positions.size - 2] - positions[positions.size - 1])
                    val currentSpeed = abs(positions[positions.size - 1] - motor.currentPosition)
                    if (currentSpeed == 0 || lastSpeed / currentSpeed >= minimumChangeForStall) {
                        CommandScheduler.scheduleCommand(
                            TelemetryCommand(3.0, "Motor " + motor.name + " Stalled!")
                        )
                        //isDone = true
                    }
                }
                saveTimes.add(timer.seconds())
                positions.add(motor.currentPosition)
            }

        }
    }
}


//SAM WAS HERE :D