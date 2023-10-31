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
var PlateState = "Up"
val plateServo = ServoEx("PushbotPlate")



@Config
@Suppress("Unused", "MemberVisibilityCanBePrivate")
object PushbotPlate : Subsystem {

    @JvmField
    var DOWN_POSITION = 0.0 //tbd, prob 45° -ish
    @JvmField
    var UP_POSITION = 0.2 //tbd, prob 0
    val Switch: Command
        get() = parallel {
            if (PlateState == "Up") {
                +Down
                +CustomCommand(_start={PlateState = "Down"})
                +TelemetryCommand(10.0, PlateState)
            }else{
                +Up
                +CustomCommand(_start={PlateState = "Up"})
                +TelemetryCommand(10.0, PlateState)
            }
        }

    val Down: Command
        get() = MoveServo(plateServo, DOWN_POSITION, TIME)
    val Up: Command
        get() = MoveServo(plateServo, UP_POSITION, TIME)

    override fun initialize() {
        plateServo.initialize()
    }



}