package com.b0r1ngx.composefeatures.common.data

fun evaluateReflectionTransform(x: (t: Float) -> Float, y: (t: Float) -> Float, t: Float): Pair<Float, Float> {
    val sqrX = x(t) * x(t)
    val sqrY = y(t) * y(t)
    val evaluatedX = .0f //(((3 * sqrX + sqrY) * x*x) + 2 * y(t) * x(t) * y*y) / (sqrX + sqrY)
    val evaluatedY = .0f
    return evaluatedX to evaluatedY
}