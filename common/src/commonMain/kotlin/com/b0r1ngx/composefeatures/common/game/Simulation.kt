package com.b0r1ngx.composefeatures.common.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.b0r1ngx.composefeatures.common.data.fundamentals.MovableType
import com.b0r1ngx.composefeatures.common.data.fundamentals.Move
import org.openrndr.math.Vector2
import kotlin.math.atan2

class Simulation {
    var width by mutableStateOf(0.dp)
    var height by mutableStateOf(0.dp)
    private var prevTime = 0L

    // init objects
    val logo = Logo(
        position = Vector2(width.value / 2.0, height.value / 2.0),
        acceleration = 9.81,
        angle = 90.0
    )

    val line = StaticObject(
        position = Vector2(600.0, 700.0),
        acceleration = 0.0,
        angle = 90.0
    )

    var simulationObjects = mutableStateListOf<SimulationObject>()


    fun startSimulation() {
        simulationObjects.add(logo)
        simulationObjects.add(line)
    }

    fun move(to: Move) {
        simulationObjects[0].move(to)

//        for (obj in simulationObjects) {
//        }
    }

    fun update(time: Long) {
        val delta = time - prevTime
        val floatDelta = (delta / 1E8).toFloat()
        prevTime = time

        for (obj in simulationObjects) {
            obj.update(floatDelta, this)
        }
    }
}

class Logo(
    type: MovableType = MovableType.Dynamic,
    position: Vector2 = Vector2.ZERO,
    speed: Double = 0.0,
    acceleration: Double = 0.0,
    angle: Double = 0.0,
    override var size: Double = 128.0
) : SimulationObject(type, position, speed, acceleration, angle)

class StaticObject(
    type: MovableType = MovableType.Static,
    position: Vector2 = Vector2.ZERO,
    speed: Double = 0.0,
    acceleration: Double = 0.0,
    angle: Double = 0.0,
    override var size: Double = 128.0
) : SimulationObject(type, position, speed, acceleration, angle)

sealed class SimulationObject(
    type: MovableType = MovableType.Static,
    position: Vector2 = Vector2.ZERO,
    speed: Double = 0.0,
    acceleration: Double = 9.81,
    angle: Double = 0.0
) {
    var speed by mutableStateOf(speed)
    var acceleration by mutableStateOf(acceleration)
    var angle by mutableStateOf(angle)
    var position by mutableStateOf(position)
    var movementVector
        get() = if (angle == 0.0) newMovementVector() else newMovementVector().rotate(angle)
        set(value) {
            speed = value.length
            angle = value.angle()
        }
    abstract val size: Double // Diameter

    val MOVE_TO_FIFTEEN = Vector2(15.0, 0.0)

    private fun newMovementVector() = Vector2.UNIT_X * speed

    fun update(delta: Float, world: Simulation) {
        if (overlapsWith(world)) speed *= -1
        speed += acceleration
        val velocity = movementVector * delta.toDouble()
        println("angle: $angle")
        println("movementVector: $movementVector")
        println("velocity: $velocity")
        position += velocity
//        position = position.mod( // allow object transition at end of screen
//            Vector2(world.width.value.toDouble(), world.height.value.toDouble())
//        )
    }

    fun overlapsWith(other: SimulationObject) =
        this.position.distanceTo(other.position) < (this.size / 2 + other.size / 2)

    // todo: must be more accurate
    private fun overlapsWith(world: Simulation) =
        if (position.x + size / 1.5 > world.width.value) true
        else position.y + size / 1.5 > world.height.value

    private fun overlapsWithCircle(world: Simulation) =
        position.x * position.x + position.y * position.y == 100.0

    fun move(to: Move) {
        when (to) {
            Move.LEFT -> {
                movementVector -= MOVE_TO_FIFTEEN
            }

            Move.RIGHT -> {
                movementVector += MOVE_TO_FIFTEEN
            }

            else -> {}
        }
    }

    fun moveByChangePosition(to: Move) {
        when (to) {
            Move.LEFT -> {
                position -= MOVE_TO_FIFTEEN
            }

            Move.RIGHT -> {
                position += MOVE_TO_FIFTEEN
            }

            else -> {}
        }
    }

}

fun Vector2.angle(): Double {
    val rawAngle = atan2(y = this.y, x = this.x)
    return (rawAngle / Math.PI) * 180
}

val SimulationObject.xOffset: Dp get() = position.x.dp - (size.dp / 2)
val SimulationObject.yOffset: Dp get() = position.y.dp - (size.dp / 2)