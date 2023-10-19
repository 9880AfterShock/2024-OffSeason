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
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.util.RobotLog
import org.atomicrobotics3805.cflib.Command
import org.atomicrobotics3805.cflib.hardware.MotorEx
import org.atomicrobotics3805.cflib.parallel
import org.atomicrobotics3805.cflib.subsystems.Subsystem
import org.atomicrobotics3805.cflib.subsystems.MotorToPosition
import org.atomicrobotics3805.cflib.subsystems.PowerMotor


/**
 * This class is an example of a lift controlled by a single motor. Unlike the Intake example object, it can use
 * encoders to go to a set position. Its first two commands, toLow and toHigh, do just that. The start command turns
 * the motor on and lets it spin freely, and the reverse command does the same but in the opposite direction. The stop
 * command stops the motor. These last three are meant for use during the TeleOp period to control the lift manually.
 * To use this class, copy it into the proper package and change the first eight constants (COUNTS_PER_INCH is fine as
 * is).
 */
//THESE ARE THE SLIDES THAT PUSH THE CLAW
var min = 0.0
var max = 8.7
@Config
@Suppress("Unused", "MemberVisibilityCanBePrivate")
object Arms : Subsystem {

    var NAME_1 = "LeftSlide"
    var NAME_2 = "RightSlide"
    var LeftSlide = DcMotorSimple.Direction.FORWARD
    var RightSlide = DcMotorSimple.Direction.REVERSE
    var SPEED = 1.0
    val StartExtend: Command
        get() =
            ControlledPowerMotor(ArmMotor, SPEED, (28 * 19.2 * max).toInt())

    val StartRetract: Command
        get() =
            ControlledPowerMotor(ArmMotor, -SPEED, (28 * 19.2 * min).toInt())

    val Stop: Command
        get() =
            PowerMotor(ArmMotor, 0.0)


    val ArmMotor: MotorEx = CustomMotorExGroup(
        MotorEx(NAME_1, MotorEx.MotorType.GOBILDA_YELLOWJACKET, 19.2, LeftSlide),
        MotorEx(NAME_2, MotorEx.MotorType.GOBILDA_YELLOWJACKET, 19.2, RightSlide)
    )

    override fun initialize() {
        ArmMotor.initialize()
    }

//copy of power motor for slides cap
    class ControlledPowerMotor(
        private val motor: MotorEx,
        private val power: Double,
        private val stopPosition: Int,
        private val mode: DcMotor.RunMode? = null,
        override val requirements: List<Subsystem> = arrayListOf(),
        override val interruptible: Boolean = true,
        private val logData: Boolean = false
    ) : Command() {
    override val _isDone: Boolean
        get() = (motor.currentPosition > stopPosition && power > 0) ||
                (motor.currentPosition < stopPosition && power < 0)
        override fun start() {
            if (mode != null) {
                motor.mode = mode
            }
            motor.power = power
            if(logData) {
                RobotLog.i("PowerMotor", power)
            }
        }

    override fun end(interrupted: Boolean) {
        motor.power = 0.0
    }
    }
    //end of port
}