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
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.ServoControllerEx
import com.qualcomm.robotcore.util.ElapsedTime
import org.atomicrobotics3805.cflib.Command
import org.atomicrobotics3805.cflib.TelemetryController
import org.atomicrobotics3805.cflib.hardware.ServoEx
import org.atomicrobotics3805.cflib.parallel
import org.atomicrobotics3805.cflib.sequential
import org.atomicrobotics3805.cflib.subsystems.Subsystem
//import org.atomicrobotics3805.cflib.subsystems.MoveServo
import org.atomicrobotics3805.cflib.utilCommands.CustomCommand
import org.atomicrobotics3805.cflib.utilCommands.Delay
import org.atomicrobotics3805.cflib.utilCommands.TelemetryCommand
import kotlin.math.abs
import kotlin.math.sign

/**
 * This class is an example of a lift controlled by a single motor. Unlike the Intake example object, it can use
 * encoders to go to a set position. Its first two commands, toLow and toHigh, do just that. The start command turns
 * the motor on and lets it spin freely, and the reverse command does the same but in the opposite direction. The stop
 * command stops the motor. These last three are meant for use during the TeleOp period to control the lift manually.
 * To use this class, copy it into the proper package and change the first eight constants (COUNTS_PER_INCH is fine as
 * is).
 */

//trigger 2 is left, trigger(1) is right

private var TIME = 1.0 //tbd
var TriggerState = "Up"
val triggerServo = ServoEx("Trigger")
val triggerServo2 = ServoEx("Trigger2")


@Config
@Suppress("Unused", "MemberVisibilityCanBePrivate")
object Trigger : Subsystem {

    @JvmField
    var downPosition = 0.0 //was 0.012
    @JvmField
    var downPosition2 = 0.0 //was 1.0
    @JvmField
    var MostlydownPosition = 0.062 //was 0.062
    @JvmField
    var MostlydownPosition2 = 0.95 //was 0.95
    @JvmField
    var upPosition = 1.0 //was 0.212
    @JvmField
    var upPosition2 = 1.0 //was 0.8
    @JvmField
    var TestingPosition = 0.5
//    val Depower : Command
//        get() = CustomCommand(_start={
//            val controller: ServoControllerEx = triggerServo.servo.controller as ServoControllerEx
//            val controller2: ServoControllerEx = triggerServo2.servo.controller as ServoControllerEx
//            controller.setServoPwmDisable(triggerServo.portNumber)
//            controller2.setServoPwmDisable(triggerServo2.portNumber)
//        })
    val Switch: Command
        get() = parallel {
            if (TriggerState == "Up") {
                +Down
                +CustomCommand(_start={TriggerState = "Down"})
                +TelemetryCommand(10.0, "Trigger is",TriggerState)
            }else{
                +Up
                +CustomCommand(_start={TriggerState = "Up"})
                +TelemetryCommand(10.0, "Trigger is",TriggerState)
            }
        }

    val Down: Command

        get() = sequential {
            +parallel {
                +MoveServo(triggerServo, downPosition, TIME)
                +MoveServo(triggerServo2, downPosition2, TIME)
            }
            +Delay(0.5)
            //+Depower
        }

    val Up: Command
        get() = parallel {
            +MoveServo(triggerServo, upPosition, TIME)
            +MoveServo(triggerServo2, upPosition2, TIME)
        }
    val MostlyDown: Command
        get() = parallel {
            +MoveServo(triggerServo, downPosition, TIME)
            +MoveServo(triggerServo2, downPosition2, TIME)
        }
    val UpStart: Command
        get() = parallel {
            +MoveServo(triggerServo, upPosition, TIME, 0.08)
            +MoveServo(triggerServo2, upPosition2, TIME,0.08)
            +CustomCommand(_start={TriggerState = "Up"})
        }

    val ResetServos: Command
        get() = parallel {
            +MoveServo(triggerServo, TestingPosition, TIME)
            +MoveServo(triggerServo2, TestingPosition, TIME)
        }

    override fun initialize() {
        triggerServo.initialize()
        triggerServo2.initialize()
        triggerServo2.direction = Servo.Direction.REVERSE

    }

    class MoveServo(private val servo: ServoEx,
                    private val position: Double,
                    private val maxTime: Double,
                    private val servoSpeed: Double = 0.5,
                    override val requirements: List<Subsystem> = arrayListOf(),
                    override val interruptible: Boolean = true) : Command() {

        private var curPosition = 0.0
        private var lastTime = 0.0
        private val timer = ElapsedTime()
        override val _isDone: Boolean
            get() = curPosition == position
        /**
         * Calculates the difference in position, moves the servo, and resets the timer
         */
        override fun start() {
            curPosition = servo.position
            servo.position = position
            lastTime = 0.0
            timer.reset()
        }

        override fun execute() {
            val error = position - curPosition
            val direction = sign(error)
            val deltaTime = timer.seconds() - lastTime
            val servoAmountChanged = deltaTime * servoSpeed
            if (abs(error) < servoAmountChanged) {
                curPosition = position
            }
            else {
                curPosition += servoAmountChanged * direction
            }
            servo.position = curPosition
            lastTime = timer.seconds()
            TelemetryController.telemetry.addData("Cur Position", curPosition)
            TelemetryController.telemetry.addData("Changed Position", servoAmountChanged)
        }
    }


}