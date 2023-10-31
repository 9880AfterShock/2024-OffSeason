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
import org.apache.commons.math3.analysis.function.Power
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
var TriggerState = "Closed"
val triggerServo = ServoEx("Trigger")
val triggerServo2 = ServoEx("Trigger2")


@Config
@Suppress("Unused", "MemberVisibilityCanBePrivate")
object Trigger : Subsystem {

    @JvmField
    var TriggeredPOSITION = 0.6 //tbd, prob 90° -ish down
    @JvmField
    var TriggeredPOSITION2 = 0.4 // inverse of 1
    @JvmField
    var LoadedPOSITION = 0.1 //tbd, prob 0 or 1 up
    @JvmField
    var LoadedPOSITION2 = 0.9 // inverse of 1
    val Switch: Command
        get() = parallel {
            if (TriggerState == "Closed") {
                +Open
                +CustomCommand(_start={TriggerState = "Open"})
                +TelemetryCommand(10.0, "Trigger is",TriggerState)
            }else{
                +Close
                +CustomCommand(_start={TriggerState = "Closed"})
                +TelemetryCommand(10.0, "Trigger is",TriggerState)
            }
        }

    val Open: Command
        get() = parallel {
            +MoveServo(triggerServo, TriggeredPOSITION, TIME)
            +MoveServo(triggerServo2, TriggeredPOSITION2, TIME)



        }

    val Close: Command
        get() = parallel {
            +MoveServo(triggerServo, LoadedPOSITION, TIME)
            +MoveServo(triggerServo2, LoadedPOSITION2, TIME)
        }
    override fun initialize() {
        triggerServo.initialize()
        triggerServo2.initialize()
    }



}