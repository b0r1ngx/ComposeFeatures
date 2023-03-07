package com.b0r1ngx.composefeatures.common.data

const val g = 9.81f
const val radius = 100f

class ChaoticBall(x0: Float, y0: Float) {
    var prevTime = 0L

    // tmax = 10 , time to end simulation? no needed
    // val reflected = ReflectionTransform[{-x[t],-y[t]}][{x'[t], y'[t]}]
    // NDSolveValue[{
    val x = mutableListOf(x0)
    val y = mutableListOf(y0)

    val xV = mutableListOf(0f)
    val yV = mutableListOf(0f)

    val xA = mutableListOf(0f)
    val yA = mutableListOf(-g)

    fun update(time: Long) {
        val delta = (time - prevTime).toInt()
        val floatDelta = (delta / 1E8).toFloat()
        prevTime = time

        val x = x[delta]
        val y = y[delta]
        if (x * x + y * y == radius) {
//            xV.add(evaluate())
//            yV.add(evaluate())
            // Evaluate[reflected]
        }
    }

    private fun evaluate() {

    }
}

