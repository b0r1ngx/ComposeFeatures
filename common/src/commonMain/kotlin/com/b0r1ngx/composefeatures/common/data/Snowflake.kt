package com.b0r1ngx.composefeatures.common.data

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.nextDown
import kotlin.math.sin
import kotlin.random.Random

// Snowflake may be asymmetric

fun DrawScope.drawSnowflake(color: Color) {
    val orbitRadius = 100f

    val snowflakeBranchData = snowflakeBranchData()
    for (dot in -90..269 step 60) { // we can rotate like this
        val rad = Math.toRadians(dot.toDouble())
        val x = (orbitRadius * cos(rad)).toFloat()
        val y = (orbitRadius * sin(rad)).toFloat()
        drawSnowflakeBranch(color, x, y)
    }
}

fun snowflakeBranchData(): List<Offset> {
    val result = mutableListOf<Offset>()
//    val baseEnd = Offset(center.x + x, center.y + y)
//    result.add(baseEnd)
    return result
}

fun DrawScope.drawSnowflakeBranch(color: Color, x: Float, y: Float) {
    val baseEnd = Offset(center.x + x, center.y + y)
    println(center)
    println(baseEnd)
    drawLine(
        color = color,
        start = center,
        end = baseEnd
    )

    val secondStart = Offset(nextFloat(center.x, baseEnd.x), nextFloat(center.y, baseEnd.y))
    val secondEnd = Offset(secondStart.x + x, secondStart.y + y) // Offset(nextFloat(), nextFloat()),
    drawLine(
        color = color,
        start = secondStart,
        end = secondEnd
    )
}

fun nextFloat(from: Float, until: Float): Float {
//    checkRangeBounds(from, until)
    if (from == until) return from
//    if (from > until) {
//        val from = until.also { val until = from }
//    }
    val size = until - from
    val r = if (size.isInfinite() && from.isFinite() && until.isFinite()) {
        val r1 = Random.nextFloat() * abs(until / 2 - from / 2)
        from + r1 + r1
    } else {
        from + Random.nextFloat() * size
    }
    return if (r >= until) until.nextDown() else r
}

fun checkRangeBounds(from: Float, until: Float) = require(until > from) { boundsErrorMessage(from, until) }
fun boundsErrorMessage(from: Any, until: Any) = "Random range is empty: [$from, $until)."

