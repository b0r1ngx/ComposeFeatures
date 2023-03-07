package com.b0r1ngx.composefeatures.common.data

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun DrawCurves() {
    Canvas(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    ) {
        val width = size.width
        val height = size.height
        val halfWidth = width.times(.5f)
        val halfHeight = height.times(.5f)

        val startPoints = listOf(
            Offset(0f, 0f),
            Offset(0f, halfHeight),
            Offset(0f, height),

            Offset(width, 0f),
            Offset(width, halfHeight),
            Offset(width, height),
        )

        val path = Path().apply {
            startPoints.forEach { point ->

                val lineEndX =
                    if (point.x > halfWidth)
                        width.minus(halfWidth.times(.3f))
                    else
                        halfWidth.times(.3f)

                val curveXPart1 =
                    if (point.x > halfWidth)
                        width.minus(halfWidth.times(.5f))
                    else
                        halfWidth.times(.5f)

                val curveXPart2 =
                    if (point.x > halfWidth)
                        width.minus(halfWidth.times(.7f))
                    else
                        halfWidth.times(.7f)

                val curve1 = Offset(curveXPart1, point.y)
                val curve2 = Offset(curveXPart1, halfHeight - ((halfHeight - point.y) / 2))

                moveTo(point.x, point.y)
                lineTo(lineEndX, point.y)
                quadraticBezierTo(
                    curve1.x,
                    curve1.y,
                    curve2.x,
                    curve2.y,
                )
                quadraticBezierTo(
                    curveXPart1,
                    halfHeight,
                    curveXPart2,
                    halfHeight,
                )
                lineTo(halfWidth, halfHeight)
            }
        }
        drawPath(
            path = path,
            color = Color.Black,
            style = Stroke(width = 20f, cap = StrokeCap.Round)
        )
    }
}