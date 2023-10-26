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
import org.atomicrobotics3805.cflib.Command
import org.atomicrobotics3805.cflib.hardware.ServoEx
import org.atomicrobotics3805.cflib.parallel
import org.atomicrobotics3805.cflib.subsystems.Subsystem
import org.atomicrobotics3805.cflib.subsystems.MoveServo
import org.atomicrobotics3805.cflib.utilCommands.CustomCommand
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

private var TIME = 1.0 //tbd
var TriggerState = "Open"
val triggerServo = ServoEx("Trigger")



@Config
@Suppress("Unused", "MemberVisibilityCanBePrivate")
object Trigger : Subsystem {

    @JvmField
    var TriggeredPOSITION = 0.4 //tbd, prob 90° -ish
    @JvmField
    var LoadedPOSITION = 0.0 //tbd, prob 0 or 1
    val Switch: Command
        get() = parallel {
            if (TriggerState == "Closed") {
                +Open
                +CustomCommand(_start={TriggerState = "Open"})
                +TelemetryCommand(10.0, ClawState)
            }else{
                +Close
                +CustomCommand(_start={TriggerState = "Closed"})
                +TelemetryCommand(10.0, TriggerState)
            }
        }

    val Open: Command
        get() = MoveServo(triggerServo, TriggeredPOSITION, TIME)
    val Close: Command
        get() = MoveServo(triggerServo, LoadedPOSITION, TIME)

    override fun initialize() {
        triggerServo.initialize()
    }



}