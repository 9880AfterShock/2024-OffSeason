package org.firstinspires.ftc.teamcode

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.Servo

@Config
object ClawTemplate {
    private lateinit var claw: Servo
    @JvmField
    var openPos = 0.0 //the positions
    @JvmField
    var closePos = 0.2 //the positions
    private var state = "Open"
    private var clawButtonCurrentlyPressed = false
    private var clawButtonPreviouslyPressed = false

    lateinit var opmode:OpMode
    fun initClaw(opmode: OpMode){
        claw = opmode.hardwareMap.get(Servo::class.java, "Claw") //config name
        this.opmode = opmode
    }
    fun open(){
        claw.position = openPos
        state = "Open"
    }
    fun close(){
        claw.position = closePos
        state = "Close"
    }
    fun swap(){
        if (state == "Open") {

            close()
        } else {
            open()
        }
    }
    fun update() {
        opmode.telemetry.addData("Claw State", state)

        // Check the status of the claw button on the gamepad
        clawButtonCurrentlyPressed = opmode.gamepad1.a //change this to change the button

        // If the button state is different than what it was, then act
        if (clawButtonCurrentlyPressed != clawButtonPreviouslyPressed) {
            // If the button is (now) down
            if (clawButtonCurrentlyPressed) {
                swap()
            }
        }
        clawButtonPreviouslyPressed = clawButtonCurrentlyPressed


    }
}
