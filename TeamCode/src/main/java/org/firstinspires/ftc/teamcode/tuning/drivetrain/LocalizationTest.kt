package org.firstinspires.ftc.teamcode.tuning.drivetrain

import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.roadrunner.geometry.Pose2d
import org.atomicrobotics3805.cflib.CommandScheduler
import org.atomicrobotics3805.cflib.Constants.drive
import org.atomicrobotics3805.cflib.Constants.opMode
import org.atomicrobotics3805.cflib.TelemetryController
import org.atomicrobotics3805.cflib.driving.drivers.MecanumDrive
import org.atomicrobotics3805.cflib.driving.localizers.TwoWheelOdometryLocalizer
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.atomicrobotics3805.cflib.subsystems.DisplayRobot
import org.atomicrobotics3805.cflib.utilCommands.TelemetryCommand
import org.firstinspires.ftc.teamcode.tuning.constants.TuningMecanumDriveConstants
import org.firstinspires.ftc.teamcode.tuning.constants.TuningOdometryConstants

/**
 * 192.168.43.1:8080/dash
 *
 * This is a simple teleop routine for testing localization. Drive the robot around like a normal
 * teleop routine and make sure the robot's estimated pose matches the robot's actual pose (slight
 * errors are not out of the ordinary, especially with sudden drive motions). The goal of this
 * exercise is to ascertain whether the localizer has been configured properly (note: the pure
 * encoder localizer heading may be significantly off if the track width has not been tuned).
 */
@Config
@TeleOp(group = "drive")
class LocalizationTest : LinearOpMode() {
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        opMode = this
        drive = MecanumDrive(
            TuningMecanumDriveConstants,
            TwoWheelOdometryLocalizer(TuningOdometryConstants),
        ) { Pose2d() }
        CommandScheduler.registerSubsystems(TelemetryController, drive)
        waitForStart()
        CommandScheduler.scheduleCommand(drive.driverControlled(gamepad1))
        CommandScheduler.scheduleCommand(DisplayRobot())
        CommandScheduler.scheduleCommand(TelemetryCommand(1000.0, "Position") { drive.poseEstimate.toString() })
        CommandScheduler.scheduleCommand(TelemetryCommand(1000.0, "Velocity") { drive.poseVelocity.toString() })
        while (!isStopRequested) {
            CommandScheduler.run()
        }
    }
}