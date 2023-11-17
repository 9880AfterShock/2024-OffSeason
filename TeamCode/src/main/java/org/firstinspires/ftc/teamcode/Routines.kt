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

import org.atomicrobotics3805.cflib.Command
import org.atomicrobotics3805.cflib.Constants
import org.atomicrobotics3805.cflib.parallel
import org.atomicrobotics3805.cflib.sequential
import org.atomicrobotics3805.cflib.utilCommands.CustomCommand
import org.atomicrobotics3805.cflib.utilCommands.Delay
import org.atomicrobotics3805.cflib.utilCommands.OptionCommand
import org.atomicrobotics3805.cflib.utilCommands.TelemetryCommand

/**
 * This class is an example of how to create routines. Routines are essentially just groups of
 * commands that can be run either one at a time (sequentially) or all at once (in parallel).
 */
object PracticeRoutines {
    val Outside2:Command
        get()= sequential {
            //make claw touch ground
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.startToOutside2)
            //drop Pixel
            //back up
            //grab yellow pixel
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.outside2ToScore)
            //drop pixel
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.scoreOutsideToPark)
        }
    val Center2:Command
        get()= sequential {
            //make claw touch ground
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.startToCenter2)
            //Claw.open
            //back up
            //Claw.close
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.center2ToScore)
            //drop pixel
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.scoreCenterToPark)
        }
    val Inside2:Command
        get()= sequential {
            //make claw touch ground
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.startToInside2)
            //drop pixel
            //Back up
            //grab yellow pixel
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.inside2ToScore)
            //drop pixel
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.scoreInsideToPark)
        }
    val BasicScoreRoutine1:Command
        get()=sequential {
            +CustomCommand(_start = {Claw.clawServo.servo.position = Claw.CLOSE_POSITION})
            +Trigger.MostlyDown
            +Delay(1.0)
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.startToStart1)
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.startToMiddle1)
            +Trigger.Up
            +Delay(1.0)
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.middleToScore)
            +Claw.Open
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.middleToEnd1)
        }
    val BasicScoreRoutine2:Command
        get()=sequential {
            +CustomCommand(_start = {Claw.clawServo.servo.position = Claw.CLOSE_POSITION})
            +Trigger.MostlyDown
            +Delay(1.0)
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.startTostart2)
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.startToMiddle2)
            +Trigger.Up
            +Delay(1.0)
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.middleToScore)
            +Claw.Open
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.middleToEnd1)
        }
    val ParkRoutine1:Command
        get()= sequential {
            +Trigger.MostlyDown
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.startToPark1)
        }

    val ParkRoutine2:Command
        get()= sequential {
            +Trigger.MostlyDown
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.startToPark2)
        }
    val OptionRoutine: Command
        get()= parallel {
            +OptionCommand(
                "Detection Name",
                { Detection.selectedPosition},
                Pair(PropProcessor.Selected.LEFT, leftPath),
                Pair(PropProcessor.Selected.MIDDLE, middleCommand),
                Pair(PropProcessor.Selected.RIGHT, rightPath)
            )
            +TelemetryCommand(100.0, Detection.selectedPosition.toString())
        }





//OptionCommand(Detection.selectedPosition,
//                Pair(PropProcessor.Selected.LEFT, LeftPath))
//            OptionCommand(Detection.selectedPosition,
//                Pair(PropProcessor.Selected.RIGHT, RightPath))
//            OptionCommand(Detection.selectedPosition,
//                Pair(PropProcessor.Selected.MIDDLE, Center2))

    val leftPath: Command
        get() = parallel{
            if (Constants.color == Constants.Color.BLUE) +Outside2
            else +Inside2
        }
      /*  get() = OptionCommand(
                "leftCommand",
            {Constants.color},
                Pair(Constants.Color.BLUE, Outside2),
                Pair(Constants.Color.RED, Inside2)) */
    val middleCommand: Command
        get() = Center2

    val rightPath: Command
        get() = parallel {
            if (Constants.color == Constants.Color.BLUE) +Inside2
            else +Outside2
          /*  +OptionCommand(
                { Constants.color },
                Pair(Constants.Color.BLUE) { CommandScheduler.scheduleCommand(Inside2) }, // does not run
                Pair(Constants.Color.RED) { CommandScheduler.scheduleCommand(Outside2) }  // does not run
            )
            +TelemetryCommand(100.0, Constants.color.toString())*/

        }
}
