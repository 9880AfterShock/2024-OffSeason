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
import com.qualcomm.robotcore.hardware.DcMotorSimple
import org.atomicrobotics3805.cflib.Command
import org.atomicrobotics3805.cflib.CommandScheduler
import org.atomicrobotics3805.cflib.hardware.MotorEx
import org.atomicrobotics3805.cflib.subsystems.Subsystem
import org.atomicrobotics3805.cflib.subsystems.MotorToPosition
import org.atomicrobotics3805.cflib.utilCommands.TelemetryCommand

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
    var SPEED = 0.5
    var UP = 60
    var FARUP = 120
    var DOWN = 0
    var GearRatioMotor = 19.2
    var GearRatioArm = 5
    var encoderTicks = 28
    val Up: Command
        get() =
            MotorToPosition(
                ArmMotor,
                (encoderTicks * GearRatioMotor * UP * GearRatioArm / 360).toInt(),
                SPEED
            )
    val Down: Command
        get() =
            MotorToPosition(
                ArmMotor,
                (encoderTicks * GearRatioMotor * DOWN * GearRatioArm / 360).toInt(),
                SPEED
            )

    val FarUp: Command
        get() =
            MotorToPosition(
                ArmMotor,
                (encoderTicks * GearRatioMotor * FARUP * GearRatioArm / 360).toInt(),
                SPEED
            )


    val ArmMotor: MotorEx = CustomMotorExGroup(
        MotorEx(NAME_1, MotorEx.MotorType.GOBILDA_YELLOWJACKET, 19.2, LeftArm),
        MotorEx(NAME_2, MotorEx.MotorType.GOBILDA_YELLOWJACKET, 19.2, RightArm)
    )


    override fun initialize() {
        ArmMotor.initialize()
    }

}


//SAM WAS HERE :D