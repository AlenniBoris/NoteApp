package com.example.noteapplication.presentation.home.views

import android.annotation.SuppressLint
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.noteapplication.domain.model.Note
import kotlin.math.roundToInt

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun NoteItem(
    note: Note,
    isRevealed: Boolean,
    noteOffset: Float,
    onExpand: () -> Unit,
    onCollapse: () -> Unit,
    onClick: () -> Unit
){

    var offsetX by remember { mutableStateOf(0f) }
    val transitionState = remember {
        MutableTransitionState(isRevealed).apply {
            targetState = !isRevealed
        }
    }

    val transition = updateTransition(transitionState)
    val offsetTransition by transition.animateFloat(
        label = "noteOffsetTransition",
        transitionSpec = { tween(durationMillis = 200) },
        targetValueByState = { if(isRevealed) noteOffset - offsetX else -offsetX },
    )

    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .offset { IntOffset((offsetX + offsetTransition).roundToInt(), 0) }
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    val original = Offset(offsetX, 0f)
                    val summed = original + Offset(x = dragAmount, y = 0f)
                    val newValue = Offset(x = summed.x.coerceIn(0f, noteOffset), y = 0f)
                    if (newValue.x >= 10) {
                        onExpand()
                        return@detectHorizontalDragGestures
                    } else if (newValue.x <= 0) {
                        onCollapse()
                        return@detectHorizontalDragGestures
                    }
                    if (change.positionChange() != Offset.Zero) change.consume()
                    offsetX = newValue.x
                }
            },
        content = {
            NoteItemContent(note, onClick)
        }
    )


}