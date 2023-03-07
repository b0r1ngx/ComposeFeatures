package com.b0r1ngx.composefeatures.common.features

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun rippleEffect(
    content: () -> Unit
) {
    var tapped by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val elevation by animateDpAsState(
        targetValue = if (tapped) 0.dp else 5.dp,
        animationSpec = tween(50)
    )

    Surface(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize() // requiredSize(150.dp)
            .indication(interactionSource, LocalIndication.current)
            .pointerInput(Unit) {
                detectTapGestures(onPress = {
                    tapped = true
                    with(press(it)) {
                        interactionSource.emit(this)
                        tryAwaitRelease()
                        interactionSource.emit(release(this))
                    }
                    tapped = false
                })
            },
        elevation = elevation
    ) {
        content()
    }
}

fun press(offset: Offset) = PressInteraction.Press(offset)
fun release(press: PressInteraction.Press) = PressInteraction.Release(press)