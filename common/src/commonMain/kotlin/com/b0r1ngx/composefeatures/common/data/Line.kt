package com.b0r1ngx.composefeatures.common.data

import org.openrndr.math.Vector2
import java.lang.StrictMath.atan2


class Line(
    val a: Vector2,
    val b: Vector2,
    val thickness: Double = 1.0
    ) {

    fun draw() {
        val tangent = b - a
        val rotation = atan2(tangent.y, tangent.x)

        val imageThickness = 8
        val thicknessScale = thickness / imageThickness

//        val capOrigin = Vector2(Art.HalfCircle.Width, Art.HalfCircle.Height / 2f)
//
//        val middleOrigin = Vector2(0.0, Art.LightningSegment.Height / 2f)
//        val middleScale = Vector2(tangent.length, thicknessScale)
    }
}