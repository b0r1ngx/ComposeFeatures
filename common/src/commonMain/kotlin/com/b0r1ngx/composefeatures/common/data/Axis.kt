package com.b0r1ngx.composefeatures.common.data

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import kotlin.math.max

@Composable
fun TowerWrapping() {
    // 10f for spiral intersecting / overlapping corridor
    // 20f for rose
    // 30f to get twelve-sided polygon
    var baseAngle by remember { mutableStateOf(10f) }
    var nextFigureSizeDifference by remember { mutableStateOf(0) }

    Column {
        Row {
            Text("Base angle:")
            Slider(value = baseAngle, onValueChange = { baseAngle = it }, valueRange = 0f..180f)
        }
        Row {
            Text("Next figure size difference:")
            Slider(
                value = nextFigureSizeDifference.toFloat(),
                onValueChange = { nextFigureSizeDifference = it.toInt() },
                valueRange = 0f..100f
            )
        }
        AxisTower(baseAngle, nextFigureSizeDifference)
    }
}

@Composable
fun AxisTower(
    baseAngle: Float = 10f,
    sizeDifference: Int = 0
) {
    Canvas(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val numberOfRectangles = 40

        val centerX = canvasWidth / 2
        val centerY = canvasHeight / 2

        for (i in 0 until numberOfRectangles) {
            // Angle gets progressively larger
            val angle = baseAngle * i
            // Call draw function with calculated angle
//            drawRotatingRectangle(centerX, centerY, angle, i)
            drawDecreasingSizeRotatingRectangle(centerX, centerY, angle, i, sizeDifference)
        }
    }
}

fun createColor(index: Int): Color {
    val formula = 1 * index / 255f
    return Color(red = formula, green = formula, blue = formula)
}


fun DrawScope.drawDecreasingSizeRotatingRectangle(
    centerX: Float,
    centerY: Float,
    angle: Float,
    index: Int,
    sizeDifference: Int,
) {
    // Each rectangle will be smaller than the previous
    val rectWidth = max(900f - (index * sizeDifference), 1f)
    val rectHeight = max(900f - (index * sizeDifference), 1f)

    rotate(degrees = angle, pivot = Offset(centerX, centerY)) {
        drawRect(
            color = createColor(index),
            style = Stroke(width = 2f),
            topLeft = Offset(
                centerX - rectWidth / 2,
                centerY - rectHeight / 2
            ),
            size = Size(rectWidth, rectHeight)
        )
    }
}
