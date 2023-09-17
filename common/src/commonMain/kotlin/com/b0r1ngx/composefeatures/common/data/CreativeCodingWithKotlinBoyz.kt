package com.b0r1ngx.composefeatures.common.data

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlin.math.cos
import kotlin.math.sin

fun DrawScope.drawSomethingStrange() {
    val orbitRadius = 200f
    drawCircle(
        color = Color.Yellow,
        radius = orbitRadius,
        style = Stroke(width = 10f)
    )

    for (dot in 0 until 360) {
        val rad = Math.toRadians(dot.toDouble())
        val x = (orbitRadius * cos(rad)).toFloat()
        val y = (orbitRadius * sin(rad)).toFloat()

        drawCircle(
            color = Color.Blue,
            center = Offset(
                this.center.x + x,
                this.center.y + y
            ),
            style = Stroke(width = 1f)
        )
    }
}

fun DrawScope.drawCircles() {
    val orbitRadius = 200f

    for (dot in 0 until 360 step 5) {
        val rad = Math.toRadians(dot.toDouble())
        val x = (orbitRadius * cos(rad)).toFloat()
        val y = (orbitRadius * sin(rad)).toFloat()

        drawOval(
            color = Color(0xFF7400A8),
            topLeft = Offset(
                this.center.x + x,
                this.center.y + y
            ),
            style = Stroke(width = 1f)
        )
    }
}

fun DrawScope.rocketScience() {
    val orbitRadius = 200f
    drawCircle(
        color = Color.Yellow,
        radius = orbitRadius,
        style = Stroke(width = 10f)
    )
    println(size.minDimension )
    for (dot in 0 until 360 step 10) {
        val rad = Math.toRadians(dot.toDouble())
        val x = (orbitRadius * cos(rad)).toFloat()
        val y = (orbitRadius * sin(rad)).toFloat()

        drawArc(
            color = Color.Cyan,
            startAngle = 90f,
            sweepAngle = 180f,
            useCenter = false,
            style = Stroke(width = 5f),
            topLeft = Offset(this.center.x + x, this.center.y + y),
            size = Size(this.center.x + x, this.center.y + y)
        )
        drawCircle(
            color = Color.Blue,
            radius = 10f,
            center = Offset(this.center.x + x, this.center.y + y),
            style = Stroke(width = 5f)
        )
    }
}