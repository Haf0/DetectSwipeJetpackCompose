package com.haf.experiment.swipeablescreen.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import kotlin.math.abs

@Composable
fun SwipeAbleScreen(modifier: Modifier = Modifier) {
    var counter by remember { mutableStateOf(0) }
    var accumulatedDrag by remember { mutableStateOf(0f) }
    val screen = LocalConfiguration.current.screenWidthDp.dp
    val threshold = screen.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    change.consume()
                    accumulatedDrag += dragAmount
                    if (accumulatedDrag >= threshold) {
                        counter++
                        accumulatedDrag = 0f
                    } else if (accumulatedDrag <= -threshold) {
                        counter--
                        accumulatedDrag = 0f
                    }
                }
            }
    ) {
        Text("Swipe me $counter", modifier = Modifier.align(Alignment.Center))

        Text(
            "Swipe me $counter",
            modifier = Modifier.clip(shape = RectangleShape)
                .size(threshold.toInt().pxToDp())
                .border(10.dp, Color.Red)
                .align(Alignment.BottomStart)
        )
        Text(
            "Swipe me $counter",
            modifier = Modifier.clip(shape = RectangleShape)
                .size(threshold.toInt().pxToDp())
                .border(10.dp, Color.Red)
                .align(Alignment.BottomEnd)
        )
    }
}

@Composable
fun Int.pxToDp() = with(LocalDensity.current) { this@pxToDp.toDp() }