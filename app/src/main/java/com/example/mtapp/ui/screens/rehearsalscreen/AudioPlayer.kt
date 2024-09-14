//package com.example.mtapp.ui.screens.rehearsalscreen
//
//import android.util.Log
//import androidx.compose.foundation.background
//import androidx.compose.foundation.gestures.detectDragGestures
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.offset
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.wrapContentWidth
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Flag
//import androidx.compose.material.icons.filled.Pause
//import androidx.compose.material.icons.filled.PlayArrow
//import androidx.compose.material.icons.filled.SkipNext
//import androidx.compose.material.icons.filled.SkipPrevious
//import androidx.compose.material.icons.filled.VolumeDown
//import androidx.compose.material.icons.filled.VolumeOff
//import androidx.compose.material.icons.filled.VolumeUp
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.derivedStateOf
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.key
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberUpdatedState
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.geometry.Size
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.layout.HorizontalAlignmentLine
//import androidx.compose.ui.layout.onGloballyPositioned
//import androidx.compose.ui.platform.LocalDensity
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.IntOffset
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.toSize
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.mtapp.R
//import com.example.mtapp.data.SceneState
//import com.example.mtapp.ui.MediaPlayerViewModel
//import com.example.mtapp.ui.PreviewMediaPlayerViewModel
//import com.example.mtapp.ui.components.DropdownMenu
//import com.example.mtapp.utils.PreviewContext
//import com.example.mtapp.utils.formatTime
//import kotlin.math.roundToInt
//
//@Composable
//fun AudioPlayer(
//    scene: Scene,
//    sceneState: SceneState, //ToDo: Us this to handle playback speed, currently selected audioPath, loop1, etc.
//    onNextScene: () -> Unit,
//    onPrevScene: () -> Unit,
//    onAudioChange: (String) -> Unit,
//    mediaPlayerViewModel: MediaPlayerViewModel,
//
//    modifier: Modifier = Modifier
//) {
//    LaunchedEffect(scene) {
//        println("Scene changed! Resetting audioplayer...")
//        mediaPlayerViewModel.resetMediaPlayer(sceneState.audioPath)
//    }
//
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)
//    ) {
//        MediaPlayerTimeLine(
//            mediaPlayerViewModel,
//            onTimeLineDrag = { newCurrentPosition ->
//                mediaPlayerViewModel.seekTo(newCurrentPosition)
//            }
//        )
//        Text(text = stringResource(scene.name))
//        Row(
//            modifier = Modifier
//                .wrapContentWidth(),
//            horizontalArrangement = Arrangement.spacedBy(0.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            IconButton(onClick = { /*TODO*/ }) {
//                Icon(
//                    if (mediaPlayerViewModel.volume > .7f) Icons.Filled.VolumeUp
//                    else if (mediaPlayerViewModel.volume > 0.3f) Icons.Filled.VolumeDown
//                    else Icons.Filled.VolumeOff,
//                    contentDescription = "Volume"
//                )
//            }
//            IconButton(
//                onClick = { onPrevScene() }
//            ) {
//                Icon(
//                    Icons.Filled.SkipPrevious,
//                    contentDescription = "Prev"
//                )
//            }
//            IconButton(onClick = {
//                if (mediaPlayerViewModel.isPlaying)
//                    mediaPlayerViewModel.pausePlayback()
//                else
//                    mediaPlayerViewModel.startPlayback()
//            }) {
//                Icon(
//                    if (mediaPlayerViewModel.isPlaying)
//                        Icons.Filled.Pause
//                    else
//                        Icons.Filled.PlayArrow,
//                    contentDescription = if (mediaPlayerViewModel.isPlaying) "Pause" else "Play"
//                )
//            }
//            IconButton(
//                onClick = { onNextScene() }
//            ) {
//                Icon(
//                    Icons.Filled.SkipNext,
//                    contentDescription = "Next"
//                )
//            }
//            Text("x1.0")
//        }
//        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//            if (scene is Song) {
//                AudioDropdown(
//                    scene,
//                    sceneState,
//                    onAudioChange,
//                    mediaPlayerViewModel,
//                )
//            } else {
//                DropdownMenu(
//                    optionsDisplayNames = listOf(), optionsValues = listOf(), selectedOption = "",
//                    onOptionSelected = {},
//                )
//            }
//            Spacer(modifier = Modifier.weight(1.0f))
//            IconButton(
//                onClick = {}
//            ) {
//                Icon(
//                    Icons.Filled.Flag,
//                    contentDescription = "Flags",
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun AudioDropdown(
//    scene: Song,
//    sceneState: SceneState,
//    onAudioChange: (String) -> Unit,
//    mediaPlayerViewModel: MediaPlayerViewModel,
//    modifier: Modifier = Modifier
//) {
//    // Precompute display names and values in a composable context
//    val optionsDisplayNames by remember(scene) {
//        derivedStateOf {
//            scene.tracks.map { tr -> tr.name } + scene.masterAudio?.name
//        }
//    }
//    val optionsValues by remember(scene) {
//        derivedStateOf {
//            scene.tracks.map { tr -> tr.audioPath } + scene.masterAudio?.audioPath
//        }
//    }
//
//    Log.v("test", "updating audiodropdownmenu")
//    sceneState.audioPath?.let { audioPath ->
//        key(audioPath) {
//            Log.v("test", audioPath)
//            DropdownMenu(
//                optionsDisplayNames = optionsDisplayNames,
//                optionsValues = optionsValues,
//                selectedOption = sceneState.audioPath,
//                onOptionSelected = { option ->
//                    onAudioChange(option)
//                    mediaPlayerViewModel.changeAudio(option)
//                }
//            )
//        }
//    }
//}
//
//@Composable
//fun MediaPlayerTimeLine(
//    mediaPlayerViewModel: MediaPlayerViewModel,
//    onTimeLineDrag: (Int) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    val circleSize = 24.dp
//    val circleSizePx = with(LocalDensity.current) { circleSize.toPx() }
//    var timeLineSize by remember { mutableStateOf(Size.Zero) }
//
//    val circleX = remember(mediaPlayerViewModel.currentPosition, mediaPlayerViewModel.duration) {
//        if (mediaPlayerViewModel.duration > 0)
//            mediaPlayerViewModel.currentPosition.toFloat() / mediaPlayerViewModel.duration.toFloat() * timeLineSize.width - circleSizePx / 2
//        else 0f
//    }
//    val currentPositionState = rememberUpdatedState(mediaPlayerViewModel.currentPosition)
//    val durationState = rememberUpdatedState(mediaPlayerViewModel.duration)
//    val colors = MaterialTheme.colorScheme
//
//    Box(
//        modifier = Modifier
//            .background(colors.surfaceVariant)
//            .fillMaxWidth()
//            .onGloballyPositioned { layoutCoordinates ->
//                timeLineSize = layoutCoordinates.size.toSize()
//            }
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth(circleX)
//                .background(Color.Red)
//        )
//        Box(
//            modifier = Modifier
//                .size(circleSize)
//                .offset() { IntOffset(circleX.roundToInt(), 0) }
//                .clip(CircleShape)
//                .background(colors.onPrimaryContainer)
//                .pointerInput(Unit) {
//                    detectDragGestures { _, dragAmount ->
//                        val dragDelta =
//                            (durationState.value * dragAmount.x / timeLineSize.width).toInt()
//                        Log.v(
//                            "dragTest",
//                            "dragDelta: " + dragDelta + " durationState.value " + durationState.value
//                        )
//                        val newCurrentPosition =
//                            (currentPositionState.value + dragDelta).coerceIn(
//                                0,
//                                durationState.value
//                            )
//                        Log.v(
//                            "dragTest",
//                            "newCurrentPosition: " + newCurrentPosition + " currentPositionState.value " + currentPositionState.value
//                        )
//                        onTimeLineDrag(newCurrentPosition)
//                    }
//                }
//        )
//        Text(
//            text = formatTime(mediaPlayerViewModel.currentPosition) + "/" + formatTime(
//                mediaPlayerViewModel.duration
//            ),
//            modifier = Modifier
//                .align(Alignment.TopCenter)
//        )
//    }
//
//}
//
//
//@Preview(showBackground = true, heightDp = 200)
//@Composable
//fun AudioPlayerPreview() {
//    val context = PreviewContext()
//    val mediaPlayerViewModel: PreviewMediaPlayerViewModel = viewModel()
//    AudioPlayer(
//        scene = Song(
//            name = R.string.wiz_oz_scene1,
//            actNumber = 1,
//            sceneNumber = 1,
//            startPage = 3,
//            endPage = 3,
//            masterAudio = AudioObject(R.string.backing_track, "")
//        ),
//        onNextScene = {},
//        onPrevScene = {},
//        onAudioChange = {},
//        sceneState = SceneState(),
//        mediaPlayerViewModel = mediaPlayerViewModel
//    )
//}
