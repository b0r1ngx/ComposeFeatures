package com.b0r1ngx.composefeatures.common

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.b0r1ngx.composefeatures.common.data.ChaoticBall
import com.b0r1ngx.composefeatures.common.data.TowerWrapping
import com.b0r1ngx.composefeatures.common.game.Logo
import com.b0r1ngx.composefeatures.common.game.Simulation
import com.b0r1ngx.composefeatures.common.game.xOffset
import com.b0r1ngx.composefeatures.common.game.yOffset

@Composable
fun App() {
    MaterialTheme {
        TowerWrapping()
//        Box(
//            modifier = Modifier.fillMaxSize(),
////            contentAlignment = Alignment.Center
//        ) {
////            Simulate()
//            Canvas(modifier = Modifier.fillMaxSize()) {
////                drawSnowflake(color = Color.Blue)
////                drawCircles()
//                axisTowers()
//            }
//        }
    }
}

@Composable
fun DrawScope.drawLogo(logo: Logo) {
    val color = Color.Cyan
    drawRect(color)
}

@Composable
fun DrawLogo(logo: Logo) {
    val color by animateColorBetween(Color.Blue, Color.Yellow)
    Box(
        modifier = Modifier
            .offset(logo.xOffset, logo.yOffset)
            .size(logo.size.dp)
            .rotate(logo.angle.toFloat())
            .clip(CircleShape)
            .drawBehind {
                drawRect(color)
            }
    )
}

//@Composable
//fun DrawLine(line: StaticObject) {
//    val color = Color.Black
//    Box(
//        modifier = Modifier
//            .offset(line.xOffset, line.yOffset)
//            .size(line.size.dp)
//            .rotate(line.angle.toFloat())
//            .clip(RectangleShape)
//            .drawBehind {
////                drawRect(color)
//            }
//    )
//}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Simulate() {
    val simulation = remember { Simulation() }
    val density = LocalDensity.current

    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    LaunchedEffect(Unit) {
        while (true) {
            withFrameNanos { simulation.update(it) }
        }
    }

    Box(modifier = Modifier.fillMaxSize().onSizeChanged {
        with(density) {
            simulation.width = it.width.toDp()
            simulation.height = it.height.toDp()
        }
    }.onPreviewKeyEvent {
        if (it.type == KeyEventType.KeyDown) {
            when (it.key) {
                Key.A -> {
//                    simulation.move(Move.LEFT)
                    true
                }

                Key.D -> {
//                    simulation.move(Move.RIGHT)
                    true
                }

                else -> false
            }
        } else false
    }) {
        Button(onClick = { simulation.startSimulation() }) {
            Text("Start")
        }
        simulation.simulationObjects.forEach {
            when (it) {
                is Logo -> DrawLogo(it)
//                is StaticObject -> DrawLine(it)
            }
        }
    }
}

@Composable
fun Canvasing() {
    Canvas(modifier = Modifier.size(100.dp)) {
        drawRect(Color.Blue, size = size) // BG
        println("Recomposing")
    }
    println("Recomposing")
}

@Composable
fun SquareComponent(componentSize: Dp = 300.dp) {
    val canvasSizePx = with(LocalDensity.current) {
        componentSize.toPx()
    }

    val infiniteScale = rememberInfiniteTransition()

    val animatedDotScale by infiniteScale.animateFloat(
        initialValue = 20f,
        targetValue = canvasSizePx / 2,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(
        modifier = Modifier
            .size(componentSize)
    ) {
        withTransform({
            rotate(degrees = 20f)
            translate(top = size.width / 3f)
        }) {
            drawRect(
                brush = Brush.linearGradient(
                    colors = listOf(Color.Red, Color.Green)
                ),
                size = size
            )
        }

        drawCircle(
            color = Color.White,
            center = Offset(x = size.width / 2f, y = size.height / 2f),
            radius = animatedDotScale
        )
    }
}

@Composable
fun BoxWithAnimatedColorBackground(
    from: Color = Color.Red,
    to: Color = Color.Green,
    width: Dp = 128.dp,
    height: Dp = 128.dp
) {
    val color by animateColorBetween(from, to)
    Box(
        modifier = Modifier // .wrapContentSize(Alignment.Center)
            .size(width, height)
            .drawBehind {
                drawRect(color)
                drawCircle(
                    color = Color.White,
                    center = Offset(x = size.width / 2f, y = size.height / 2f),
                    radius = size.minDimension / 2f
                )
            }
    ) {
        println("Recomposing")
    }
}

@Composable
private fun animateColorBetween(
    initialValue: Color,
    targetValue: Color
): State<Color> {
    val infiniteTransition = rememberInfiniteTransition()
    return infiniteTransition.animateColor(
        initialValue = initialValue,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
}

@Composable
fun littleExampleWithInset() {
    Canvas(Modifier.size(120.dp)) {
        // Draw grey background, drawRect function is provided by the receiver
        drawRect(color = Color.Gray)

        // Inset content by 10 pixels on the left/right sides
        // and 12 by the top/bottom
        inset(10.0f, 12.0f) {
            val quadrantSize = size / 2.0f

            // Draw a rectangle within the inset bounds
            drawRect(
                size = quadrantSize,
                color = Color.Red
            )

            rotate(45.0f) {
                drawRect(size = quadrantSize, color = Color.Blue)
            }
        }
    }
}

@Composable
fun chaoticBalls() {
    val ball1 = ChaoticBall(1f, 2f)// Color.Cyan
    val ball2 = ChaoticBall(1.5f, 2f)// Color.Yellow
    LaunchedEffect(Unit) {
        while (true) {
            withFrameNanos {
                ball1.update(it)
                ball2.update(it)
            }
        }
    }
    Box {
        Circle()
        Ball(ball1, Color.Blue)
        Ball(ball2, Color.Yellow)
    }
}

@Composable
fun Ball(ball: ChaoticBall, color: Color) {
    Box(
        Modifier
            .offset(ball.x.last().dp, ball.y.last().dp)
            .size(25.dp)
            .clip(CircleShape)
            .background(color)
    )
}


@Composable
fun Circle() {
    val canvasModifier = Modifier.fillMaxSize().background(color = Color.Black)
    Canvas(modifier = canvasModifier) {
        drawCircle(
            color = Color.White,
            radius = 100f,
            style = Stroke(width = 2f)
        )
    }
}

@Composable
fun HelloPlatform() {
    var text by remember { mutableStateOf("Hello, World!") }
    val platformName = getPlatformName()

    Button(onClick = {
        text = "Hello, $platformName!"
    }) {
        Text(text)
    }
}