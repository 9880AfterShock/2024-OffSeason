package org.firstinspires.ftc.teamcode.primary

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.LiftTemplate
import org.firstinspires.ftc.teamcode.PositionMotorTemplate


@TeleOp(name = "Template OpMode") //change string for display name
//Toggle Disabled to make appear in list or not.
//@Disabled
class OpmodeTemplate : LinearOpMode() {
    private val runtime = ElapsedTime()
    //Make Motor Vars

    override fun runOpMode() {
        //Add init msg
        telemetry.addData("Status", "Initialized")
        telemetry.update()

        //Call Init Functions (make sure to add "this")
        LiftTemplate.initLift(this)
        PositionMotorTemplate.initMotor(this)

        //Wait for start
        waitForStart()
        runtime.reset()

        //Running Loop
        while (opModeIsActive()) {
            LiftTemplate.updateLift()
            PositionMotorTemplate.updateMotor()
            // Show the elapsed time (and other telemetry) on driver station
            telemetry.addData("Status", "Run Time: $runtime")
            telemetry.update()
        }
    }

}