/*
    Copyright (c) 2022 Atomic Robotics (https://atomicrobotics3805.org)

    This program is free software: you can blueistribute it and/or modify
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
import org.atomicrobotics3805.cflib.trajectories.*
import org.atomicrobotics3805.cflib.Constants.drive
import org.atomicrobotics3805.cflib.trajectories.TrajectoryFactory

/**
 * This class contains all of the RoadRunner trajectories and start positions in the project. It's
 * used by the ExampleRoutines class. You can find how to use each of the possible trajectory
 * segments (like back and splineToSplineHeading) here:
 * https://learnroadrunner.com/trajectorybuilder-functions.html
 */
object PracticeTrajectoryFactory : TrajectoryFactory() {

    var Pose1 = Pose2d()
    var SecondPose1 = Pose2d()
    var ThirdPose1 = Pose2d()
    var FourthPose1 = Pose2d()

    var Pose2 = Pose2d()
    var SecondPose2 = Pose2d()
    var ThirdPose2 = Pose2d()

  //  var bluePose1 = Pose2d()
    //var blueSecondPose1 = Pose2d()
    //var blueThirdPose1 = Pose2d()
    //var blueFourthPose1 = Pose2d()

    /**
     * Initializes the robot's start positions and trajectories. This is where the trajectories are
     * actually created.
     */
    lateinit var startToMiddle1 : ParallelTrajectory
    lateinit var startToMiddle2 : ParallelTrajectory
    //lateinit var startToMiddleBlue1 : ParallelTrajectory
    override fun initialize() {
        super.initialize()
        // start positions
Pose1 = Pose2d(-35.0,58.0.switchColor, 0.0)
        SecondPose1 = Pose2d(17.0,60.0.switchColor,0.0.toRadians)
        ThirdPose1 = Pose2d(42.0,30.0.switchColor,0.0.toRadians)
      FourthPose1 = Pose2d(42.0,16.0.switchColor,0.0.toRadians)
Pose2 = Pose2d(10.0,58.0.switchColor, 0.0)
        SecondPose2 = Pose2d(45.0,60.0.switchColor,0.0.toRadians)
        ThirdPose2 = Pose2d(42.0,33.0.switchColor,0.0.toRadians)
        // trajectories
    startToMiddle1 = drive.trajectoryBuilder(Pose1, 0.0.toRadians)
        .splineToSplineHeading(SecondPose1, (0.0.toRadians))
        .splineToSplineHeading(ThirdPose1, (0.0.toRadians))
        .splineToSplineHeading(FourthPose1, (0.0.toRadians))
        .build()
    startToMiddle2 = drive.trajectoryBuilder(Pose2, 0.0.toRadians)
        .splineToSplineHeading(SecondPose2, (0.0.toRadians))
       // .splineToSplineHeading(blueThirdPose2, (0.0.toRadians))
        .build()
  //  startToMiddleBlue1 = drive.trajectoryBuilder(bluePose1,0.0.toRadians)
    //    .splineToSplineHeading(blueSecondPose1,0.0.toRadians)
      //  .splineToSplineHeading(blueThirdPose1, 0.0.toRadians)
        //.build()
    }
}