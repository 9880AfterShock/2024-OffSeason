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
import com.acmerobotics.roadrunner.geometry.Vector2d
import org.atomicrobotics3805.cflib.trajectories.*
import org.atomicrobotics3805.cflib.Constants
import org.atomicrobotics3805.cflib.Constants.drive
import org.atomicrobotics3805.cflib.trajectories.TrajectoryFactory

/**
 * This class contains all of the RoadRunner trajectories and start positions in the project. It's
 * used by the ExampleRoutines class. You can find how to use each of the possible trajectory
 * segments (like back and splineToSplineHeading) here:
 * https://learnroadrunner.com/trajectorybuilder-functions.html
 */
object PracticeTrajectoryFactory : TrajectoryFactory() {

    var myPose = Pose2d()
    var mySecondPose = Pose2d()
    var myThirdPose = Pose2d()
    var myFourthPose = Pose2d()
    /**
     * Initializes the robot's start positions and trajectories. This is where the trajectories are
     * actually created.
     */
    lateinit var startToMiddle : ParallelTrajectory
    override fun initialize() {
        super.initialize()
        // start positions
myPose = Pose2d(-35.0,-58.0.switchColor, 45.0)
        mySecondPose = Pose2d(55.0,53.0.switchColor,50.0.toRadians)
        myThirdPose = Pose2d(-45.0,35.0.switchColor,50.0.toRadians)
        myFourthPose = Pose2d(0.0,0.0.switchColor,180.0.toRadians)
        // trajectories
    startToMiddle = drive.trajectoryBuilder(myPose, 45.0.toRadians)
        .splineToSplineHeading(mySecondPose, (180.0.toRadians))
        .splineToSplineHeading(myThirdPose, (200.0.toRadians))
        .splineToSplineHeading(myFourthPose,0.0.toRadians)
        .build()
    }
}