package com.b0r1ngx.composefeatures.common.data

class LightningBolt {
    var segments = mutableListOf<Line>()
    var alpha = 0
    var fadeOutRate = 0
    var tint = 0

    fun isComplete() = alpha <= 0
}