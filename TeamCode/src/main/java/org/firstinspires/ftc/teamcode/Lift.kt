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
import com.qualcomm.robotcore.hardware.DcMotorEx
import org.atomicrobotics3805.cflib.Command
import org.atomicrobotics3805.cflib.hardware.MotorEx
import org.atomicrobotics3805.cflib.parallel
import org.atomicrobotics3805.cflib.subsystems.Subsystem
import org.atomicrobotics3805.cflib.subsystems.MotorToPosition

var SPEED = 0.5
var UP = 60
var FARUP = 120
var DOWN = 0
var GearRatioMotor = 19.2
var GearRatioArm = 5
var encoderTicks = 28
//19.2:1 IS MOTOR RATIO
//20:100 IS GEAR RATIO (1:5 doi)
//1:360 ROUNDS:DEGREES RATIO
//DANG THAT'S A LOT OF RATIOS
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

    val Up: Command
        get() = parallel {
            +MotorToPosition(LeftArm, (encoderTicks * GearRatioMotor * UP * GearRatioArm / 360).toInt(), SPEED)
            +MotorToPosition(RightArm, -(encoderTicks * GearRatioMotor * UP * GearRatioArm / 360).toInt(), SPEED)
        }
    val Down: Command
        get() = parallel {
            +MotorToPosition(LeftArm, (encoderTicks * GearRatioMotor * DOWN * GearRatioArm / 360).toInt(), SPEED)
            +MotorToPosition(RightArm, -(encoderTicks * GearRatioMotor * DOWN * GearRatioArm / 360).toInt(), SPEED)
        }
    val FarUp: Command
        get() = parallel {
            +MotorToPosition(LeftArm, (encoderTicks * GearRatioMotor * FARUP * GearRatioArm / 360).toInt(), SPEED)
            +MotorToPosition(RightArm, -(encoderTicks * GearRatioMotor * FARUP * GearRatioArm / 360).toInt(), SPEED)
        }


lateinit var LeftArm: MotorEx
//claw this way (from driver view) CLAW HERE
lateinit var RightArm: MotorEx


}



//SAM WAS HERE :D