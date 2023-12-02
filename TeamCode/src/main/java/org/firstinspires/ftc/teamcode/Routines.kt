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
        get()= parallel{
            +Lift.MotorToPosition(Lift.LiftMotor, Lift.SPEED)
            +sequential {
                +Constants.drive.followTrajectory(PracticeTrajectoryFactory.startToOutside2)
                +Claw.Open
                +parallel{
                    +Constants.drive.followTrajectory(PracticeTrajectoryFactory.outsideToBackup2)
                    +sequential {
                       // +Delay(0.05)
                        +Claw.Close
                    }
                }
                +Delay(0.2)
                +parallel {// this parallel should end after the robot reaches the backboard, then the claw can open
                    +Constants.drive.followTrajectory(PracticeTrajectoryFactory.outside2ToScore)
                    +sequential {
                        +Lift.Up
                        +Trigger.Down
                        +Trigger.Up
                    }
                }

                +Delay(0.2) //should be for safety
                +Claw.Open
                +Delay(1.5)
                +parallel {// start backing up while I lower the arm.
                    +Constants.drive.followTrajectory(PracticeTrajectoryFactory.outsideScoreToBackup)
                    +sequential {
                        +Claw.Close
                        +Trigger.Down
                        +Delay(0.7)
                        +parallel{
                            +Lift.Down
                            +sequential {
                                +Delay(0.375)
                                +Claw.Open
                            }
                        } }
                }
                +Constants.drive.followTrajectory(PracticeTrajectoryFactory.scoreOutsideToPark)
        }
        }
    val Outside1:Command
        get()= parallel{
            +Lift.MotorToPosition(Lift.LiftMotor, Lift.SPEED)
            +sequential  {
                +Constants.drive.followTrajectory(PracticeTrajectoryFactory.startToOutside1)
                +Claw.Open
                +parallel{
                    +Constants.drive.followTrajectory(PracticeTrajectoryFactory.outsideToBackup1)
                    +Claw.Close
                }
                +Delay(0.2)
                +parallel {// this parallel should end after the robot reaches the backboard, then the claw can open
                    +Constants.drive.followTrajectory(PracticeTrajectoryFactory.outside1ToScore)
                    +sequential {
                        +Delay(1.4)
                        +Lift.Up
                        +Trigger.Down //for reset
                        +Trigger.Up //score
                    }
                }

                +Delay(0.2) //should be for safety
                +Claw.Open
                +Delay(1.5)
                +parallel {// start backing up while I lower the arm.
                    +Constants.drive.followTrajectory(PracticeTrajectoryFactory.outsideScoreToBackup)
                    +sequential {
                        +Claw.Close
                        +Trigger.Down
                        +Delay(0.7)
                        +parallel{
                            +Lift.Down
                            +sequential {
                                +Delay(0.375)
                                +Claw.Open
                            }
                        } }
                }
                +Constants.drive.followTrajectory(PracticeTrajectoryFactory.scoreOutsideToPark)
        }}
    val Center2:Command
        get()= parallel{
            +Lift.MotorToPosition(Lift.LiftMotor, Lift.SPEED)
            +sequential  {
            //make claw touch ground
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.startToCenter2)
                +Claw.Open
                +parallel{
                    +Constants.drive.followTrajectory(PracticeTrajectoryFactory.centerToBackup2)
                    +Claw.Close
                }
                +Delay(0.2)
                +parallel {// this parallel should end after the robot reaches the backboard, then the claw can open
                    +Constants.drive.followTrajectory(PracticeTrajectoryFactory.center2ToScore)
                    +sequential {
                        +Lift.Up
                        +Trigger.Down
                        +Trigger.Up
                    }
                }

                +Delay(0.2) //should be for safety
                +Claw.Open
                +Delay(1.5)
                +parallel {// start backing up while I lower the arm.
                    +Constants.drive.followTrajectory(PracticeTrajectoryFactory.centerScoreToBackup)
                    +sequential {
                        +Claw.Close
                        +Trigger.Down
                        +Delay(0.7)
                        +parallel{
                            +Lift.Down
                            +sequential {
                                +Delay(0.375)
                                +Claw.Open
                            }
                        } }
                }
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.scoreCenterToPark)
        }}
    val Center1:Command
        get()= parallel{
            +Lift.MotorToPosition(Lift.LiftMotor, Lift.SPEED)
            +sequential  {
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.startToCenter1)
                +Claw.Open
                +parallel{
                    +Constants.drive.followTrajectory(PracticeTrajectoryFactory.centerToBackup1)
                    +Claw.Close
                }
                +Delay(0.2)
                +parallel {// this parallel should end after the robot reaches the backboard, then the claw can open
                    +Constants.drive.followTrajectory(PracticeTrajectoryFactory.center1ToScore)
                    +sequential {
                        +Delay(1.4)
                        +Lift.Up
                        +Trigger.Down
                        +Trigger.Up
                    }
                }

                +Delay(0.2) //should be for safety
                +Claw.Open
                +Delay(1.5)
                +parallel {// start backing up while I lower the arm.
                    +Constants.drive.followTrajectory(PracticeTrajectoryFactory.centerScoreToBackup)
                    +sequential {
                        +Claw.Close
                        +Trigger.Down
                        +Delay(0.7)
                        +parallel{
                            +Lift.Down
                            +sequential {
                                +Delay(0.375)
                                +Claw.Open
                            }
                        } }
                }
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.scoreCenterToPark)
        }}
    val Inside2:Command
        get()= parallel{
            +Lift.MotorToPosition(Lift.LiftMotor, Lift.SPEED)
            +sequential  {
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.startToInside2)
                +Claw.Open
                +parallel{
                    +Constants.drive.followTrajectory(PracticeTrajectoryFactory.insideToBackup2)
                    +sequential {
                        +Delay(0.015)
                        +Claw.Close
                    }
                }
                +Delay(0.2)
                +parallel {// this parallel should end after the robot reaches the backboard, then the claw can open
                    +Constants.drive.followTrajectory(PracticeTrajectoryFactory.inside2ToScore)
                    +sequential {
                        +Lift.Up
                        +Trigger.Down
                        +Trigger.Up
                    }
                }
                +Delay(0.2) //should be for safety
                +Claw.Open
                +Delay(1.5)
                +parallel {// start backing up while I lower the arm.
                    +Constants.drive.followTrajectory(PracticeTrajectoryFactory.insideScoreToBackup)
                    +sequential {
                        +Claw.Close
                        +Trigger.Down
                        +Delay(0.7)
                        +parallel{
                            +Lift.Down
                            +sequential {
                                +Delay(0.375)
                                +Claw.Open
                            }
                        } }
                }
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.scoreInsideToPark)
        }}
    val Inside1:Command
        get()= parallel{
            +Lift.MotorToPosition(Lift.LiftMotor, Lift.SPEED)
            +sequential  {
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.startToInside1)
                +Claw.Open
                +parallel{
                    +Constants.drive.followTrajectory(PracticeTrajectoryFactory.insideToBackup1_1)
                    +Claw.Close
                }
                +Constants.drive.followTrajectory(PracticeTrajectoryFactory.insideToBackup1)
                +Delay(0.2)
                +parallel {// this parallel should end after the robot reaches the backboard, then the claw can open
                    +Constants.drive.followTrajectory(PracticeTrajectoryFactory.inside1ToScore)
                    +sequential {
                        +Delay(1.4)
                        +Lift.Up
                        +Trigger.Down
                        +Trigger.Up
                    }
                }
                +Delay(0.2) //should be for safety
                +Claw.Open
                +Delay(1.5)
                +parallel {// start backing up while I lower the arm.
                    +Constants.drive.followTrajectory(PracticeTrajectoryFactory.insideScoreToBackup)
                    +sequential {
                        +Claw.Close
                        +Trigger.Down
                        +Delay(0.7)
                        +parallel{
                            +Lift.Down
                            +sequential {
                                +Delay(0.375)
                                +Claw.Open
                            }
                        } }
                }
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.scoreInsideToPark)
        }}
    val BasicScoreRoutine1:Command
        get() = parallel{
            +Lift.MotorToPosition(Lift.LiftMotor, Lift.SPEED)
            +sequential  {
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
        }}
    val BasicScoreRoutine2:Command
        get() = parallel{
            +Lift.MotorToPosition(Lift.LiftMotor, Lift.SPEED)
            +sequential  {
            +CustomCommand(_start = {Claw.clawServo.servo.position = Claw.CLOSE_POSITION})
            +Trigger.MostlyDown
            +Delay(1.0)
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.startToStart2)
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.startToMiddle2)
            +Trigger.Up
            +Delay(1.0)
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.middleToScore)
            +Claw.Open
            +Constants.drive.followTrajectory(PracticeTrajectoryFactory.middleToEnd1)
        }}
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
    val OptionRoutine2: Command
        get()= parallel {
            +OptionCommand(
                "Detection Name2",
                { Detection.selectedPosition},
                Pair(PropProcessor.Selected.LEFT, leftPath2),
                Pair(PropProcessor.Selected.MIDDLE, middleCommand2),
                Pair(PropProcessor.Selected.RIGHT, rightPath2)
            )
            +TelemetryCommand(100.0, Detection.selectedPosition.toString())
        }
    val OptionRoutine1: Command
        get()= parallel {
            +OptionCommand(
                "Detection Name1",
                { Detection.selectedPosition},
                Pair(PropProcessor.Selected.LEFT, leftPath1),
                Pair(PropProcessor.Selected.MIDDLE, middleCommand1),
                Pair(PropProcessor.Selected.RIGHT, rightPath1)
            )
            +TelemetryCommand(100.0, Detection.selectedPosition.toString())
        }



    val leftPath2: Command
        get() = parallel{
            if (Constants.color == Constants.Color.BLUE) +Outside2
            else +Inside2
        }
    val leftPath1: Command
        get() = parallel{
            if (Constants.color == Constants.Color.BLUE) +Outside1
            else +Inside1
        }
    val middleCommand2: Command
        get() = Center2
    val middleCommand1: Command
        get() = Center1

    val rightPath2: Command
        get() = parallel {
            if (Constants.color == Constants.Color.BLUE) +Inside2
            else +Outside2
        }
    val rightPath1: Command
        get() = parallel {
            if (Constants.color == Constants.Color.BLUE) +Inside1
            else +Outside1
        }
}
