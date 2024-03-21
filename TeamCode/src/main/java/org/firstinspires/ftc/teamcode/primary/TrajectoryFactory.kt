package org.firstinspires.ftc.teamcode.primary

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.trajectory.Trajectory
import org.firstinspires.ftc.teamcode.primary.drive.SampleMecanumDrive
import kotlin.math.PI

object TrajectoryFactory {
    // Poses
    lateinit var startDropSide : Pose2d
    lateinit var scoreCenterDropSide : Pose2d
<<<<<<< HEAD
    lateinit var middleBackdrop : Pose2d
    // Trajectories
    lateinit var startToCenterDropSide : Trajectory
    lateinit var centerToBackdrop : Trajectory
    //lateinit var centerToBackDropSide : Trajectory
=======
    lateinit var centerScorePose : Pose2d
    lateinit var parkPose : Pose2d
    // Trajectories
    lateinit var startToCenterDropSide : Trajectory
    lateinit var centerToBackDropSide : Trajectory
    lateinit var centerBackToScore : Trajectory
    lateinit var scoreCenterToBackup : Trajectory
    lateinit var centerBackupToPark : Trajectory
>>>>>>> c677f0009fcd15fc38d14372fe678e803b998aa1
    fun initTrajectories(drive : SampleMecanumDrive) {
        //put pos here
        startDropSide = Pose2d(9.0,61.6,-PI/2)
        scoreCenterDropSide = Pose2d(12.0, 31.0, -PI/2)
<<<<<<< HEAD
        middleBackdrop = Pose2d(56.0, 38.0, 0.0)
=======
        centerScorePose = Pose2d(48.0, 36.0, 0.0)
        parkPose = Pose2d(58.0, 13.0, -PI/8)
>>>>>>> c677f0009fcd15fc38d14372fe678e803b998aa1

        //put trajectories here
        startToCenterDropSide = drive.trajectoryBuilder(startDropSide)
            .splineToSplineHeading(scoreCenterDropSide, -PI/2)
            .build()
<<<<<<< HEAD
        centerToBackdrop = drive.trajectoryBuilder(scoreCenterDropSide, 0.0)
            .splineToSplineHeading(middleBackdrop, 0.0)
=======
        centerToBackDropSide = drive.trajectoryBuilder(scoreCenterDropSide, 0.0)
            .back(16.0)
            .build()
        centerBackToScore = drive.trajectoryBuilder(centerToBackDropSide.end(), -PI/2)
            .splineToSplineHeading(centerScorePose, 0.0)
            .build()
        scoreCenterToBackup = drive.trajectoryBuilder(centerScorePose, 0.0)
            .back(12.0)
            .build()
        centerBackupToPark = drive.trajectoryBuilder(scoreCenterToBackup.end(), -PI/2)
            .splineToSplineHeading(parkPose, 0.0)
>>>>>>> c677f0009fcd15fc38d14372fe678e803b998aa1
            .build()
//        centerToBackDropSide = drive.trajectoryBuilder(scoreCenterDropSide, 0.0)
//            .back(1.0)
//            .build()


    }
}