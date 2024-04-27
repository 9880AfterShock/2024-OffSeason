package org.firstinspires.ftc.teamcode.primary

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.geometry.Vector2d
import com.acmerobotics.roadrunner.trajectory.Trajectory
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequence
import com.noahbres.meepmeep.roadrunner.trajectorysequence.sequencesegment.TrajectorySegment
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.primary.TrajectoryFactory.centerBackToScore
import org.firstinspires.ftc.teamcode.primary.TrajectoryFactory.centerBackupToPark
import org.firstinspires.ftc.teamcode.primary.TrajectoryFactory.centerToBackDropSide
import org.firstinspires.ftc.teamcode.primary.TrajectoryFactory.scoreCenterToBackup
import org.firstinspires.ftc.teamcode.primary.TrajectoryFactory.startToCenterDropSide
import org.firstinspires.ftc.teamcode.primary.drive.SampleMecanumDrive
@Autonomous(group = "drive")
class OffSeason9880_2024_Auto_Op : LinearOpMode() {
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        val drive = SampleMecanumDrive(hardwareMap)
        TrajectoryFactory.initTrajectories(drive)
        waitForStart()
        if (isStopRequested) return
        drive.followTrajectory(startToCenterDropSide) // the PID or similar for the heading and giro sensor is not set right. use the turn test program and see the heading and error. also tune dead-wheels with the localization test. use the robot auto drive by gyro file.
        sleep(250)
        drive.followTrajectory(centerToBackDropSide)
        sleep(250)
        drive.followTrajectory(centerBackToScore)
        sleep(250)
        drive.followTrajectory(scoreCenterToBackup)
        sleep(250)
        drive.followTrajectory(centerBackupToPark)
       /* val traj = drive.trajectoryBuilder(Pose2d())
                .splineTo(Vector2d(30.0, 30.0), 0.0)
                //.back(1.0)
                .build()
        drive.followTrajectory(traj) */
     //   sleep(2000)
       /* drive.followTrajectory(
                drive.trajectoryBuilder(traj.end(), true)
                        .splineTo(Vector2d(0.0, 0.0), Math.toRadians(180.0))
                        .build()
        ) */
    }
    fun camelCase() {
    /*    if (pos == left) { run left thingy }
        else if (magic) run right
                else run middle
            */
    }
}
