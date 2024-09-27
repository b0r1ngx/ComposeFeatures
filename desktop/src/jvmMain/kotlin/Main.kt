import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.b0r1ngx.composefeatures.common.App


fun main() = application {
    // rememberWindowState(size = DpSize(1280.dp, 720.dp))
    val windowState = rememberWindowState(placement = WindowPlacement.Fullscreen)

    Window(
        title = "Compose Features",
        onCloseRequest = ::exitApplication,
        state = windowState,
    ) {
        App()
    }
}
