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
import org.atomicrobotics3805.cflib.sequential
import org.atomicrobotics3805.cflib.subsystems.Subsystem
import org.atomicrobotics3805.cflib.subsystems.MoveServo
import org.atomicrobotics3805.cflib.utilCommands.CustomCommand
import org.atomicrobotics3805.cflib.utilCommands.Delay
import org.atomicrobotics3805.cflib.utilCommands.TelemetryCommand

/**
 * This class is an example of a lift controlled by a single motor. Unlike the Intake example object, it can use
 * encoders to go to a set position. Its first two commands, toLow and toHigh, do just that. The start command turns
 * the motor on and lets it spin freely, and the reverse command does the same but in the opposite direction. The stop
 * command stops the motor. These last three are meant for use during the TeleOp period to control the lift manually.
 * To use this class, copy it into the proper package and change the first eight constants (COUNTS_PER_INCH is fine as
 * is).
 */
//THIS IS THE paper airplane pew pew pew

private var TIME = 1.0 //tbd
var Launched = "Not Launched"



@Config
@Suppress("Unused", "MemberVisibilityCanBePrivate")
object Drone : Subsystem {

    val droneServo = ServoEx("Drone")
    @JvmField
    var LOADED_POSITION = 1.0 //tbd
    @JvmField
    var LAUNCHED_POSITION = 0.3 //tbd
    val Switch: Command
        get() = parallel {
            if (Launched == "Not Launched") {
                +sequential {
                    +Delay(0.5)
                    +Lift.DroneUp
                    +Launch
                }
                +CustomCommand(_start={Launched = "Launched"})
                +TelemetryCommand(10.0) { Launched }
            }else{
                +Land
                +Lift.Down
                +CustomCommand(_start={Launched = "Not Launched"})
                +TelemetryCommand(10.0) { Launched }
                +TelemetryCommand(3.0, "Compose yourself! Do not lose inspire because of a snide comment.")
            }
        }

    val Launch: Command
        get() = MoveServo(droneServo, LAUNCHED_POSITION, TIME)
    val Land: Command
        get() = MoveServo(droneServo, LOADED_POSITION, TIME)

    override fun initialize() {
        droneServo.initialize()
    }



}