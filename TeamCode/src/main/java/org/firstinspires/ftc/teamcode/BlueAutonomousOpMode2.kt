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

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.atomicrobotics3805.cflib.Constants
import org.atomicrobotics3805.cflib.driving.drivers.MecanumDrive
import org.atomicrobotics3805.cflib.driving.localizers.TwoWheelOdometryLocalizer
import org.atomicrobotics3805.cflib.opmodes.AutonomousOpMode
import org.atomicrobotics3805.cflib.sequential

/**
 * This class is an example of how you can create an Autonomous OpMode. Everything is handled by
 * the AutonomousOpMode parent class, so all you have to do is pass in the constructor parameters.
 */

@Autonomous(name = "9880 Autonomous OpMode blue 2")
class AutonomousOpModeBlue2 : AutonomousOpMode(
    Constants.Color.RED,
    PracticeTrajectoryFactory,
    { sequential {
        +Trigger.Close
        +Constants.drive.followTrajectory(PracticeTrajectoryFactory.startToMiddleRed2)
        +Trigger.Switch
        +Claw.Open

    } },
    null,
    MecanumDrive(
        PracticeMecanumDriveConstants,
        TwoWheelOdometryLocalizer(PracticeOdometryConstants)
    ) { PracticeTrajectoryFactory.redPose2 },
    Claw, Trigger
)