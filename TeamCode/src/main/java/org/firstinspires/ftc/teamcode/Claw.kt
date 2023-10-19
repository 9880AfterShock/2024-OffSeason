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
import org.atomicrobotics3805.cflib.hardware.ServoEx
import org.atomicrobotics3805.cflib.parallel
import org.atomicrobotics3805.cflib.subsystems.Subsystem
import org.atomicrobotics3805.cflib.subsystems.MotorToPosition
import org.atomicrobotics3805.cflib.subsystems.MoveServo
import org.atomicrobotics3805.cflib.subsystems.PowerMotor
import org.atomicrobotics3805.cflib.utilCommands.TelemetryCommand

/**
 * This class is an example of a lift controlled by a single motor. Unlike the Intake example object, it can use
 * encoders to go to a set position. Its first two commands, toLow and toHigh, do just that. The start command turns
 * the motor on and lets it spin freely, and the reverse command does the same but in the opposite direction. The stop
 * command stops the motor. These last three are meant for use during the TeleOp period to control the lift manually.
 * To use this class, copy it into the proper package and change the first eight constants (COUNTS_PER_INCH is fine as
 * is).
 */
//THIS IS THE CLAW

var NAME = "Claw" //tbd
var OPEN_POSITION = 0.2 //tbd, prob 45° -ish
var CLOSE_POSITION = 0.0 //tbd, prob 0
var TIME = 1.0 //tbd
var ClawState = "Closed"
val clawServo = ServoEx("Claw")



@Config
@Suppress("Unused", "MemberVisibilityCanBePrivate")
object Claw : Subsystem {

    val Switch: Command
        get() = parallel {
            if (ClawState == "Closed") {
                Open
                ClawState = "Open"
            }else{
                Close
                ClawState = "Closed"}
        }

    val Open: Command
        get() = MoveServo(clawServo, OPEN_POSITION, TIME)
    val Close: Command
        get() = MoveServo(clawServo, CLOSE_POSITION, TIME)

    override fun initialize() {
        clawServo.initialize()
    }



}