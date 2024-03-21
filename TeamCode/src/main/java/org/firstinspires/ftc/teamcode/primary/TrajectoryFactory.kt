package org.firstinspires.ftc.teamcode.primary

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.trajectory.Trajectory
import org.firstinspires.ftc.teamcode.primary.drive.SampleMecanumDrive
import kotlin.math.PI

object TrajectoryFactory {
    // Poses
    lateinit var startDropSide : Pose2d
    lateinit var scoreCenterDropSide : Pose2d
    lateinit var middleBackdrop : Pose2d
    // Trajectories
    lateinit var startToCenterDropSide : Trajectory
    lateinit var centerToBackdrop : Trajectory
    //lateinit var centerToBackDropSide : Trajectory
    fun initTrajectories(drive : SampleMecanumDrive) {
        //put pos here
        startDropSide = Pose2d(9.0,61.6,-PI/2)
        scoreCenterDropSide = Pose2d(12.0, 31.0, -PI/2)
        middleBackdrop = Pose2d(56.0, 38.0, 0.0)

        //put trajectories here
        startToCenterDropSide = drive.trajectoryBuilder(startDropSide)
            .splineToSplineHeading(scoreCenterDropSide, -PI/2)
            .build()
        centerToBackdrop = drive.trajectoryBuilder(scoreCenterDropSide, 0.0)
            .splineToSplineHeading(middleBackdrop, 0.0)
            .build()
//        centerToBackDropSide = drive.trajectoryBuilder(scoreCenterDropSide, 0.0)
//            .back(1.0)
//            .build()


    }
}