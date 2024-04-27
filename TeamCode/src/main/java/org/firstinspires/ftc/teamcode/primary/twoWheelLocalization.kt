package org.firstinspires.ftc.teamcode.drive

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.localization.TwoTrackingWheelLocalizer
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.primary.drive.SampleMecanumDrive
import org.firstinspires.ftc.teamcode.primary.util.Encoder
import java.util.Arrays

/*
* Sample tracking wheel localizer implementation assuming the standard configuration:
*
*    ^
*    |
*    | ( x direction)
*    |
*    v
*    <----( y direction )---->

*        (forward)
*    /--------------\
*    |     ____     |
*    |     ----     |    <- Perpendicular Wheel
*    |           || |
*    |           || |    <- Parallel Wheel
*    |              |
*    |              |
*    \--------------/
*
*/
class TwoWheelTrackingLocalizer(hardwareMap: HardwareMap, drive: SampleMecanumDrive) :
    TwoTrackingWheelLocalizer(
        listOf<Pose2d>(
            Pose2d(PARALLEL_X, PARALLEL_Y, 0.0),
            Pose2d(PERPENDICULAR_X, PERPENDICULAR_Y, Math.toRadians(90.0))
        )
    ) {
    // Parallel/Perpendicular to the forward axis
    // Parallel wheel is parallel to the forward axis
    // Perpendicular is perpendicular to the forward axis
    private val parallelEncoder: Encoder
    private val perpendicularEncoder: Encoder
    private val drive: SampleMecanumDrive
    var X_MULTIPLIER = 1.016949153 // Multiplier in the X direction

    var Y_MULTIPLIER = 1.020408163 // Multiplier in the Y direction

    init {
        this.drive = drive
        parallelEncoder = Encoder(hardwareMap[DcMotorEx::class.java, "rightRear"])
        perpendicularEncoder = Encoder(hardwareMap[DcMotorEx::class.java, "leftRear"])
        perpendicularEncoder.direction = Encoder.Direction.REVERSE

        // TODO: reverse any encoders using Encoder.setDirection(Encoder.Direction.REVERSE)
    }

    override fun getHeading(): Double {
        return drive.rawExternalHeading
    }

    override fun getHeadingVelocity(): Double? {
        return drive.getExternalHeadingVelocity()
    }

    override fun getWheelPositions(): List<Double> {
        return Arrays.asList(
            encoderTicksToInches(parallelEncoder.currentPosition.toDouble()) * X_MULTIPLIER,
            encoderTicksToInches(perpendicularEncoder.currentPosition.toDouble()) * Y_MULTIPLIER
        )
    }

    override fun getWheelVelocities(): List<Double> {
        // TODO: If your encoder velocity can exceed 32767 counts / second (such as the REV Through Bore and other
        //  competing magnetic encoders), change Encoder.getRawVelocity() to Encoder.getCorrectedVelocity() to enable a
        //  compensation method
        return Arrays.asList(
            encoderTicksToInches(parallelEncoder.getRawVelocity()) * X_MULTIPLIER,
            encoderTicksToInches(perpendicularEncoder.getRawVelocity()) * Y_MULTIPLIER
        )
    }

    companion object {
        var TICKS_PER_REV = 2400.0
        var WHEEL_RADIUS = 1.5 // in
        var GEAR_RATIO = 1.0 // output (wheel) speed / input (encoder) speed
        var PARALLEL_X = -6.25 // X is the up and down direction
        var PARALLEL_Y = -1.625 // Y is the strafe direction
        var PERPENDICULAR_X = -3.1
        var PERPENDICULAR_Y = 1.225
        fun encoderTicksToInches(ticks: Double): Double {
            return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV
        }
    }
}