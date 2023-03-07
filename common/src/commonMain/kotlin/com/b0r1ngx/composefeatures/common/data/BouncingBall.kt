package com.b0r1ngx.composefeatures.common.data

class BouncingBall(y0: Float) {
    val y = mutableListOf(y0)
    val yV = mutableListOf(0f)
    val yA = mutableListOf(-g)

    fun update(time: Long) {
//        val delta = (time - prevTime).toInt()
//        val floatDelta = (delta / 1E8).toFloat()
//        prevTime = time
//
//        val x = x[delta]
//        val y = y[delta]
//        if (y.last() == 0) {
//            yV.add()
//            xV.add(evaluate())
//            yV.add(evaluate())
            // Evaluate[reflected]
//        }
    }
}