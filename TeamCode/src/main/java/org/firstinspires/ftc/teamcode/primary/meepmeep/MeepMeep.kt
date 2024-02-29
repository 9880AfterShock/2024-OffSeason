package org.firstinspires.ftc.teamcode.primary.meepmeep

import com.noahbres.meepmeep.MeepMeep
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequence
import com.noahbres.meepmeep.roadrunner.trajectorysequence.sequencesegment.TrajectorySegment
import org.firstinspires.ftc.teamcode.primary.TrajectoryFactory
import org.firstinspires.ftc.teamcode.primary.drive.DriveConstants
import org.firstinspires.ftc.teamcode.primary.drive.SampleMecanumDrive

var visualizerColor = Color.BLUE
var width = 15.0
var length = 17.0
enum class Color {
    BLUE, RED
}

fun main() {
    val windowSize: Int = 600
    val darkMode: Boolean = true
    val backgroundAlpha: Float = 0.95f
    val background: MeepMeep.Background = MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK
    val meepMeep = MeepMeep(windowSize)
    meepMeep.setBackground(background)
        .setDarkMode(darkMode)
        .setBackgroundAlpha(backgroundAlpha)
    val botBuilder: DefaultBotBuilder = DefaultBotBuilder(meepMeep)
        .setDimensions(width, length)
        .setConstraints(
            DriveConstants.MAX_VEL, DriveConstants.MAX_ACCEL,
            DriveConstants.MAX_ANG_VEL, DriveConstants.MAX_ANG_ACCEL,
            DriveConstants.TRACK_WIDTH
        ).setColorScheme(
            if (visualizerColor == Color.RED) ColorSchemeRedDark()
            else ColorSchemeBlueDark()
        )
    TrajectoryFactory.initTrajectories(SampleMecanumDrive(null))
    meepMeep.addEntity(botBuilder.followTrajectorySequence(
        TrajectorySequence(
            listOf(TrajectorySegment(TrajectoryFactory.startToCenterDropSide))
        )
    ))

    meepMeep.start()

}