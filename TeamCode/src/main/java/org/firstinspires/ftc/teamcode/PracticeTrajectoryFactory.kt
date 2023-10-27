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

    var redPose1 = Pose2d()
    var redSecondPose1 = Pose2d()
    var redThirdPose1 = Pose2d()
    var redFourthPose1 = Pose2d()

    var redPose2 = Pose2d()
    var redSecondPose2 = Pose2d()
    var redThirdPose2 = Pose2d()

  //  var bluePose1 = Pose2d()
    //var blueSecondPose1 = Pose2d()
    //var blueThirdPose1 = Pose2d()
    //var blueFourthPose1 = Pose2d()

    /**
     * Initializes the robot's start positions and trajectories. This is where the trajectories are
     * actually created.
     */
    lateinit var startToMiddleRed1 : ParallelTrajectory
    lateinit var startToMiddleRed2 : ParallelTrajectory
    lateinit var startToMiddleBlue1 : ParallelTrajectory
    override fun initialize() {
        super.initialize()
        // start positions
redPose1 = Pose2d(-35.0,-58.0.switchColor, 0.0)
        redSecondPose1 = Pose2d(0.0,-59.0.switchColor,0.0.toRadians)
        redThirdPose1 = Pose2d(42.0,-33.0.switchColor,0.0.toRadians)
redPose2 = Pose2d(10.0,-58.0.switchColor, 0.0)
        redSecondPose2 = Pose2d(42.0,-33.0.switchColor,0.0.toRadians)
//bluePose1 = Pose2d(-35.0,+58.0.switchColor, 0.0)
       // blueSecondPose1 = Pose2d(0.0,59.0.switchColor,0.0.toRadians)
       // blueThirdPose1 = Pose2d(42.0,33.0.switchColor,0.0.toRadians)

        // trajectories
    startToMiddleRed1 = drive.trajectoryBuilder(redPose1, 0.0.toRadians)
        .splineToSplineHeading(redSecondPose1, (0.0.toRadians))
        .splineToSplineHeading(redThirdPose1, (0.0.toRadians))
        .build()
    startToMiddleRed2 = drive.trajectoryBuilder(redPose2, 0.0.toRadians)
        .splineToSplineHeading(redSecondPose2, (0.0.toRadians))
        .build()
  //  startToMiddleBlue1 = drive.trajectoryBuilder(bluePose1,0.0.toRadians)
    //    .splineToSplineHeading(blueSecondPose1,0.0.toRadians)
      //  .splineToSplineHeading(blueThirdPose1, 0.0.toRadians)
        //.build()
    }
}