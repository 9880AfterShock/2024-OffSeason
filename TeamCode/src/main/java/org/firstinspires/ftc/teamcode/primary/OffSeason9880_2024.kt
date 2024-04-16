package org.firstinspires.ftc.teamcode.primary

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.util.ElapsedTime
import com.qualcomm.robotcore.util.Range


@TeleOp(name = "9880 2024 aftershock")

class OffSeason9880_2024 : LinearOpMode() {
    // Declare OpMode members.
    private val runtime = ElapsedTime()
    private lateinit var leftRear: DcMotor
    private lateinit var leftFront: DcMotor
    private lateinit var rightRear: DcMotor
    private lateinit var rightFront: DcMotor
// anything you write here will not ruin my code
    override fun runOpMode() {
        telemetry.addData("Status", "Initialized")
        telemetry.update()

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftRear = hardwareMap.get(DcMotor::class.java, "leftRear")
        leftFront = hardwareMap.get(DcMotor::class.java,"leftFront")
        rightRear = hardwareMap.get(DcMotor::class.java, "rightRear")
        rightFront = hardwareMap.get(DcMotor::class.java, "rightFront")

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        leftRear.direction = DcMotorSimple.Direction.REVERSE
        leftFront.direction = DcMotorSimple.Direction.REVERSE
        rightRear.direction = (DcMotorSimple.Direction.FORWARD)
        rightFront.direction = (DcMotorSimple.Direction.FORWARD)

        // Wait for the game to start (driver presses PLAY)
        waitForStart()
        runtime.reset()

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            var leftBackPower: Double
            var leftFrontPower: Double
            var rightBackPower: Double
            var rightFrontPower: Double

            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            val strafe = gamepad1.left_stick_x.toDouble()
            val drive = -gamepad1.left_stick_y.toDouble()
            val turn = gamepad1.right_stick_x.toDouble()
            leftBackPower = Range.clip(drive + turn - strafe, -1.0, 1.0)
            leftFrontPower = Range.clip(drive + turn + strafe, -1.0, 1.0)
            rightBackPower = Range.clip(drive - turn + strafe, -1.0, 1.0)
            rightFrontPower = Range.clip(drive - turn - strafe, -1.0, 1.0)


            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y ;
            // rightPower = -gamepad1.right_stick_y ;

            // Send calculated power to wheels
            leftRear.power = leftBackPower
            leftFront.power = leftFrontPower
            rightRear.power = rightBackPower
            rightFront.power = rightFrontPower

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: $runtime")
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftBackPower, rightBackPower)
            telemetry.update()
        }
    }

}