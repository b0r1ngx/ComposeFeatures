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

const val SideSize = 750f
const val StartAngle = 1.125f
const val TowersFloors = 40
const val StartAlpha = 0.1f


@Composable
fun AxisTowers() {
    Canvas(modifier = Modifier.fillMaxSize().background(color = SkyColor).padding(16.dp)) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val leftTowerCenterX = canvasWidth * 1.5f / 4
        val leftTowerCenterY = canvasHeight * 1.15f / 4
        println(leftTowerCenterX to leftTowerCenterY)

        val rightTowerCenterX = canvasWidth * 2.6f / 4
        val rightTowerCenterY = canvasHeight * 2.85f / 4
        println(rightTowerCenterX to rightTowerCenterY)

        for (floor in 0 until TowersFloors) {
            val angle = StartAngle * floor
            drawRotatingRectangle(leftTowerCenterX, leftTowerCenterY, angle, floor, LeftTowerColor, true)
            drawRotatingRectangle(rightTowerCenterX, rightTowerCenterY, angle, floor, RightTowerColor, false)
        }
    }
}

fun DrawScope.drawRotatingRectangle(
    centerX: Float,
    centerY: Float,
    angle: Float,
    index: Int,
    color: Color,
    isRotationClockwise: Boolean = true,
) {
    val (rectWidth, rectHeight) = SideSize to SideSize
    val degrees = if (isRotationClockwise) angle else -angle
    val computedAlpha = (1 - StartAlpha) * (index + 1) / TowersFloors

    rotate(degrees = degrees, pivot = Offset(centerX, centerY)) {
        drawRect(
            color = color,
            style = Stroke(width = 2f),
            topLeft = Offset(centerX - rectWidth / 2, centerY - rectHeight / 2),
            size = Size(rectWidth, rectHeight),
            alpha = StartAlpha + computedAlpha,
        )
    }
}
