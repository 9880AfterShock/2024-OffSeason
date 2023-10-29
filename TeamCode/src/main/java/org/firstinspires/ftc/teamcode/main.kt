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
import com.noahbres.meepmeep.MeepMeep
import org.atomicrobotics3805.cflib.Constants
import org.atomicrobotics3805.cflib.driving.drivers.MecanumDrive
import org.atomicrobotics3805.cflib.driving.localizers.TwoWheelOdometryLocalizer
import org.atomicrobotics3805.cflib.sequential
import org.atomicrobotics3805.cflib.visualization.MeepMeepRobot
import org.atomicrobotics3805.cflib.visualization.MeepMeepVisualizer

fun main() {
    MeepMeepVisualizer.addRobot(MeepMeepRobot(
        MecanumDrive(
            PracticeMecanumDriveConstants,
            TwoWheelOdometryLocalizer(PracticeOdometryConstants)
        ) { Pose2d() },
        15.0, 17.033333333,
        {
            sequential {
                +Constants.drive.followTrajectory(PracticeTrajectoryFactory.startToMiddleRed1)
               // +Constants.drive.followTrajectory(PracticeTrajectoryFactory.startToMiddleRed2)
                //+Constants.drive.followTrajectory(PracticeTrajectoryFactory.startToMiddleBlue1)
                //+Constants.drive.followTrajectory(PracticeTrajectoryFactory.startToMiddleBlue2)


            } },
        Constants.Color.RED
    ))
    MeepMeepVisualizer.run(PracticeTrajectoryFactory, background = MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
}