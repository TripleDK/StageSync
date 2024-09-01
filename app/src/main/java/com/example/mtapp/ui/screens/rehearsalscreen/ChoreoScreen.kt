package com.example.mtapp.ui.screens.rehearsalscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.mtapp.Models.ChoreoPosition
import com.example.mtapp.Models.KeyFrame
import com.example.mtapp.Models.ShowCharacter
import com.example.mtapp.ui.MediaPlayerViewModel
import java.util.UUID

@Composable
fun ChoreoScreen(
    people: List<ChoreoPosition>,
    mediaPlayerViewModel: MediaPlayerViewModel,
    modifier: Modifier = Modifier
) {
    var parentBoxSize by remember { mutableStateOf(IntSize.Zero) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                parentBoxSize = coordinates.size
            }
    ) {
        people.forEach { person ->
            CastMember(
                person,
                mediaPlayerViewModel,
                onCastMoved = {/*TODO*/ },
                parentBoxSize
            )
        }

    }
}


@Composable
fun CastMember(
    member: ChoreoPosition,
    mediaPlayerViewModel: MediaPlayerViewModel,
    onCastMoved: (ChoreoPosition) -> Unit,
    parentBoxSize: IntSize
) {
    val offset = remember(mediaPlayerViewModel.currentPosition, member.keyFrames) {
        val relativeOffset = calculateOffset(
            mediaPlayerViewModel.currentPosition,
            mediaPlayerViewModel.duration,
            member.keyFrames
        )
        val finalOffset = IntOffset(
            (relativeOffset.x * parentBoxSize.width).toInt(),
            (relativeOffset.y * parentBoxSize.height).toInt()
        )
        // println(finalOffset)
        finalOffset
    }


    // println("relativePosition - X: " + relativeOffsetX + ", Y: " + relativeOffsetY)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .offset {
                offset
            }) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Blue, CircleShape)

//            .pointerInput(Unit) {
//                detectDragGestures { _, dragAmount ->
//                    offsetX = (offsetX + dragAmount.x).coerceIn(0f, size.width.toFloat())
//                    offsetY = (offsetY + dragAmount.y).coerceIn(0f, size.height.toFloat())
//                    onCastMoved(member.copy(position = Offset(offsetX,offsetY))
        )
        Text(
            text = member.character.name
        )
    }
}

//Calculates offset interpolation based on nearest keyframes to currentPosition
fun calculateOffset(currentPosition: Int, maxPosition: Int, keyFrames: List<KeyFrame>): Offset {
    val prevAndNextKeyFrame = GetPrevAndNextKeyFrame(keyFrames, currentPosition)
    val prevKeyFrame = prevAndNextKeyFrame.first
    val nextKeyFrame = prevAndNextKeyFrame.second
    if (prevKeyFrame == null && nextKeyFrame == null)
        return Offset.Zero

    val prevKeyFrameTime = prevKeyFrame?.time ?: 0
    val nextKeyFrameTime = nextKeyFrame?.time ?: maxPosition


    if (nextKeyFrame == null && prevKeyFrame != null) {
        return Offset(
            prevKeyFrame.relativePositionX,
            prevKeyFrame.relativePositionY
        )
    }

    if (prevKeyFrame == null && nextKeyFrame != null) {
        return Offset(
            nextKeyFrame.relativePositionX,
            nextKeyFrame.relativePositionY
        )
    } else {
        val relativeTime =
            (currentPosition - prevKeyFrameTime).toFloat() / (nextKeyFrameTime - prevKeyFrameTime).toFloat()


        val relativeOffsetX =
            prevKeyFrame!!.relativePositionX + relativeTime * (nextKeyFrame!!.relativePositionX - prevKeyFrame!!.relativePositionX)
//        println("Rel time: $relativeTime - Prev: (${prevKeyFrame?.relativePositionX} Current: ($relativeOffsetX) - Next(${nextKeyFrame?.relativePositionX})")

        val relativeOffsetY =
            prevKeyFrame!!.relativePositionY + relativeTime * (nextKeyFrame!!.relativePositionY - prevKeyFrame!!.relativePositionY)

        return Offset(relativeOffsetX, relativeOffsetY)
    }
}

fun GetPrevAndNextKeyFrame(
    keyFrames: List<KeyFrame>,
    currentPosition: Int
): Pair<KeyFrame?, KeyFrame?> {
    //Assume keyframes are always sorted by time
    val keyFrames = keyFrames
    val index = keyFrames.binarySearchBy(currentPosition) { it.time }
    //If index is positive, it finds an exact match on currentPosition
    //If index is negative, it represents that index a keyframe on this position would have

    //TODO: First keyframe must always be at currentPosition = 0, but need to check if currentPosition > last Keyframe
    val insertionPoint = if (index >= 0) {
        index
    } else
        -index - 1
    //E.g. -6 -> 5


    val previousKeyFrame = if (insertionPoint > 0) keyFrames[insertionPoint - 1] else null
    val nextKeyFrame = if (insertionPoint < keyFrames.size) keyFrames[insertionPoint] else null

    return Pair(previousKeyFrame, nextKeyFrame)
}

@Preview
@Composable
fun CastMemberPreview() {
//    CastMember(
//        member = ChoreoPosition(
//            keyFrames = listOf(
//                KeyFrame(
//                    time = 0,
//                    relativePositionX = 0.5f,
//                    relativePositionY = 0.5f
//                )
//            ),
//            character = ShowCharacter(name = "Scooby Doo")
//        ),
//        mediaPlayerViewModel = null,
//        onCastMoved = { },
//        parentBoxSize = IntSize(1000, 1000)
//    )
}

