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

    var RandStartPose2 = Pose2d()
    var OutsideRandPose2 = Pose2d()
    var OutRandToScore2 = Pose2d()
    var CenterRandPose2 = Pose2d()
    var CentRandToScore2 = Pose2d()
    var InsideRandPose2 = Pose2d()
    var InsRandToScore2 = Pose2d()

    var ParkPos = Pose2d()

    var Pose1 = Pose2d()
    var Pose1_1 = Pose2d()
    var SecondPose1 = Pose2d()
    var ThirdPose1 = Pose2d()
    var FourthPose1 = Pose2d()
    var FithPose1 = Pose2d()
    var SixthPose1 = Pose2d()

    var Pose2 = Pose2d()
    var Pose2_1 = Pose2d()
    var SecondPose2 = Pose2d()
    var ThirdPose2 = Pose2d()
    var FourthPose2 = Pose2d()
    var FithPose2 = Pose2d()

  //  var bluePose1 = Pose2d()
    //var blueSecondPose1 = Pose2d()
    //var blueThirdPose1 = Pose2d()
    //var blueFourthPose1 = Pose2d()

    /**
     * Initializes the robot's start positions and trajectories. This is where the trajectories are
     * actually created.
     */
    lateinit var startToStart1 : ParallelTrajectory
    lateinit var startTostart2 : ParallelTrajectory
    lateinit var startToMiddle1 : ParallelTrajectory
    lateinit var startToMiddle2 : ParallelTrajectory
    lateinit var middleToEnd1 : ParallelTrajectory
    lateinit var startToPark2 : ParallelTrajectory
    lateinit var startToPark1 : ParallelTrajectory
    lateinit var middleToScore : ParallelTrajectory
    // Randomization V
    lateinit var startToOutside2 : ParallelTrajectory
    lateinit var outside2ToScore : ParallelTrajectory
    lateinit var scoreOutside2ToPark : ParallelTrajectory
    lateinit var startToCenter2 : ParallelTrajectory
    lateinit var center2ToScore : ParallelTrajectory
    lateinit var scoreCenter2ToPark : ParallelTrajectory
    lateinit var startToInside2 : ParallelTrajectory
    lateinit var inside2ToScore : ParallelTrajectory
    lateinit var scoreInside2ToPark : ParallelTrajectory
    lateinit var scoreInsideToPark2 : ParallelTrajectory
    lateinit var startToOutside1 : ParallelTrajectory
    lateinit var startToCenter1 : ParallelTrajectory
    lateinit var startToInside1 : ParallelTrajectory

    //lateinit var startToMiddleBlue1 : ParallelTrajectory
    override fun initialize() {
        super.initialize()
        // start positions
RandStartPose2 = Pose2d(10.0,63.0.switchColor, -90.0.switchAngle.toRadians)
    OutsideRandPose2 = Pose2d(24.0, 45.0.switchColor, -90.0.switchAngle.toRadians)
    OutRandToScore2 = Pose2d(38.0, 38.0.switchColor, 0.0.toRadians)
    CenterRandPose2 = Pose2d(11.0, 38.0.switchColor, 270.0.switchAngle.toRadians)
    CentRandToScore2 = Pose2d(38.0, 36.0.switchColor, 0.0.switchAngle.toRadians)
    InsideRandPose2 = Pose2d(10.0, 44.0.switchColor, 240.0.switchAngle.toRadians)
    InsRandToScore2 = Pose2d(38.0, 38.0.switchColor, 0.0.switchAngle.toRadians)
    ParkPos = Pose2d(52.0, 11.0.switchColor, 0.0.toRadians)
Pose1 = Pose2d(-35.0,64.0.switchColor, 0.0)
        Pose1_1 = Pose2d(-35.0,58.0.switchColor, 0.0.switchAngle.toRadians)
        SecondPose1 = Pose2d(17.0,60.0.switchColor,0.0.switchAngle.toRadians)
        ThirdPose1 = Pose2d(38.0,38.0.switchColor,0.0.switchAngle.toRadians)
        FourthPose1 = Pose2d(43.0,38.0.switchColor,0.0.switchAngle.toRadians)
        FithPose1 = Pose2d(44.0,15.0.switchColor,0.0.switchAngle.toRadians)
        SixthPose1 = Pose2d(60.0,60.0.switchColor,0.0.switchAngle.toRadians)
Pose2 = Pose2d(10.0,63.5.switchColor, 0.0)
        Pose2_1 = Pose2d(10.0,58.0.switchColor,0.0.toRadians)
        SecondPose2 = Pose2d(45.0,60.0.switchColor,0.0.toRadians)
        ThirdPose2 = Pose2d(38.0,38.0.switchColor,0.0.toRadians)
        FourthPose2 = Pose2d(43.0,38.0.switchColor,0.0.toRadians)
        FithPose2 = Pose2d(44.5,38.0.switchColor,0.0.toRadians)
        // trajectories
    startToOutside2 = drive.trajectoryBuilder(RandStartPose2, 270.0.switchAngle.toRadians)
        .splineToSplineHeading(OutsideRandPose2, 270.0.switchAngle.toRadians)
        .build()
    outside2ToScore = drive.trajectoryBuilder(OutsideRandPose2, 0.0.toRadians)
        .splineToSplineHeading(OutRandToScore2, 0.0.toRadians)
        .build()
    scoreOutside2ToPark = drive.trajectoryBuilder(OutRandToScore2, 270.0.switchAngle.toRadians)
        .splineToSplineHeading(ParkPos, 0.0.toRadians)
        .build()
    startToCenter2 = drive.trajectoryBuilder(RandStartPose2, 270.0.switchAngle.toRadians)
            .splineToSplineHeading(CenterRandPose2, 270.0.switchAngle.toRadians)
            .build()
    center2ToScore = drive.trajectoryBuilder(CenterRandPose2, 0.0.switchAngle.toRadians)
        .splineToSplineHeading(CentRandToScore2, 0.0.switchAngle.toRadians)
        .build()
    scoreCenter2ToPark = drive.trajectoryBuilder(CentRandToScore2, 270.0.switchAngle.toRadians)
        .splineToSplineHeading(ParkPos, 0.0.switchAngle.toRadians)
        .build()
    startToInside2 = drive.trajectoryBuilder(RandStartPose2, 270.0.switchAngle.toRadians)
        .splineToSplineHeading(InsideRandPose2, 270.0.switchAngle.toRadians)
        .build()
    inside2ToScore = drive.trajectoryBuilder(InsideRandPose2, 270.0.switchAngle.toRadians)
        .splineToSplineHeading(InsRandToScore2, 0.0.toRadians)
        .build()
    scoreInside2ToPark = drive.trajectoryBuilder(InsRandToScore2)
        .splineToSplineHeading(ParkPos, 0.0.toRadians)
        .build()
    startToStart1 = drive.trajectoryBuilder(Pose1, 0.0.toRadians)
        .lineToConstantHeading(Pose1_1.vec())
        .build()
    startToMiddle1 = drive.trajectoryBuilder(Pose1_1, 0.0.toRadians)
        .splineToSplineHeading(SecondPose1, (0.0.toRadians))
        .splineToSplineHeading(ThirdPose1, (0.0.switchAngle.toRadians))
       // .splineToSplineHeading(FourthPose1, 0.0.toRadians)
       // .splineToSplineHeading(FourthPose1, (75.0.toRadians))
        .build()
    startToPark1 = drive.trajectoryBuilder(Pose1_1, 0.0.toRadians)
        .splineToSplineHeading(SecondPose1, (0.0.toRadians))
        .splineToSplineHeading(SixthPose1, 0.0.toRadians)
        // .splineToSplineHeading(FourthPose1, (75.0.toRadians))
        .build()

    middleToEnd1 = drive.trajectoryBuilder(FithPose2)
        .back(1.0)
        .splineToSplineHeading(FithPose1, 0.0.switchAngle.toRadians)
        .build()
    startTostart2 = drive.trajectoryBuilder(Pose2,0.0.toRadians)
        .lineToConstantHeading(Pose2_1.vec())
        .build()
    startToMiddle2 = drive.trajectoryBuilder(Pose2, 0.0.toRadians)
        .splineToSplineHeading(ThirdPose2, (0.0.switchAngle.toRadians))
        //.splineToSplineHeading(FourthPose2,45.0.toRadians)
        .build()
    startToPark2 = drive.trajectoryBuilder(Pose2, 180.0.toRadians)
            .splineToSplineHeading(SecondPose2, (0.0.toRadians))
       // .splineToSplineHeading(blueThirdPose2, (0.0.toRadians))
        .build()
    middleToScore = drive.trajectoryBuilder(ThirdPose1)
        .splineToSplineHeading(FithPose2,0.0.toRadians)
        .build()
  //  startToMiddleBlue1 = drive.trajectoryBuilder(bluePose1,0.0.toRadians)
    //    .splineToSplineHeading(blueSecondPose1,0.0.toRadians)
      //  .splineToSplineHeading(blueThirdPose1, 0.0.toRadians)
        //.build()

    }
}