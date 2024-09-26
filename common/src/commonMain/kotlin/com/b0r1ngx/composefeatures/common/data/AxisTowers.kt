package com.b0r1ngx.composefeatures.common.data

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp

val SkyColor = Color(0xFF76D7EA)
val LeftTowerColor = Color.Black
val RightTowerColor = Color(0xFFfdfff5)


@Composable
fun AxisTowers() {
    val baseAngle = 1.125f

    Canvas(modifier = Modifier.fillMaxSize().background(color = SkyColor).padding(16.dp)) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val numberOfRectangles = 40 // floors

        val leftTowerCenterX = canvasWidth / 4
        val leftTowerCenterY = canvasHeight / 4

        val rightTowerCenterX = 3 * canvasWidth / 4
        val rightTowerCenterY = 3 * canvasHeight / 4

        for (i in 0 until numberOfRectangles) {
            // Angle gets progressively larger
            val angle = baseAngle * i
            // Call draw function with calculated angle
//            drawRotatingRectangle(centerX, centerY, angle, i)
            drawRotatingRectangle(leftTowerCenterX, leftTowerCenterY, angle, LeftTowerColor)
            drawRotatingRectangle(rightTowerCenterX, rightTowerCenterY, angle, RightTowerColor, false)
        }
    }
}

fun DrawScope.drawRotatingRectangle(
    centerX: Float,
    centerY: Float,
    angle: Float,
    color: Color,
    isRotationClockwise: Boolean = true
) {
    val rectWidth = 900f
    val rectHeight = 900f

    val degree = if (isRotationClockwise) angle else -angle

    rotate(degrees = degree, pivot = Offset(centerX, centerY)) {
        drawRect(
            color = color,
            style = Stroke(width = 2f),
            topLeft = Offset(
                centerX - rectWidth / 2,
                centerY - rectHeight / 2
            ),
            size = Size(rectWidth, rectHeight)
        )
    }
}
