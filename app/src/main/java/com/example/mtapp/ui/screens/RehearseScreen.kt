package com.example.mtapp.ui.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mtapp.Models.AudioObject
import com.example.mtapp.Models.Scene
import com.example.mtapp.Models.Song
import com.example.mtapp.R
import com.example.mtapp.data.RehearsalOptions
import com.example.mtapp.data.SceneState
import com.example.mtapp.ui.MediaPlayerViewModel
import com.example.mtapp.ui.MediaPlayerViewModelFactory
import com.example.mtapp.ui.StageSyncViewModel
import com.example.mtapp.ui.components.PdfViewer
import com.example.mtapp.utils.formatTime
import kotlin.math.roundToInt

@Composable
fun RehearseScreen(
    viewModel: StageSyncViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        if (uiState.currentScene != null) {
            NavHeadersAndSceneView(
                uiState.currentScene!!,
                uiState.sceneStates[uiState.currentScene!!]!!,
                onNextScene = { src -> viewModel.setNextScene(src) },
                onPrevScene = { src -> viewModel.setPrevScene(src) },
                onToggleRehearsalOptions = { option -> viewModel.toggleRehearsalOptions(option) },
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxSize()
                    .background(Color.LightGray)
            )

            AudioPlayer(
                uiState.currentScene!!,
                uiState.sceneStates[uiState.currentScene!!]!!,
                onNextScene = { viewModel.setNextScene() },
                onPrevScene = { viewModel.setPrevScene() },
                onAudioChange = { audioPath -> viewModel.updateSceneAudioPath(audioPath) },
                modifier = Modifier
                    .weight(0.2f)
                    .fillMaxWidth()
                    .background(Color.LightGray)
            )
        } else {
            Text(text = "sum ting wong")
        }
    }
}

@Composable
fun NavHeadersAndSceneView(
    scene: Scene,
    sceneState: SceneState,
    onNextScene: (RehearsalOptions) -> Unit,
    onPrevScene: (RehearsalOptions) -> Unit,
    onToggleRehearsalOptions: (RehearsalOptions) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        NavHeaders(
            scene,
            sceneState,
            onToggleRehearsalOptions = onToggleRehearsalOptions,
            modifier = Modifier.weight(0.1f)
        )
        SceneView(
            scene,
            sceneState,
            onNextScene = onNextScene,
            onPrevScene = onPrevScene,
            modifier = Modifier.weight(0.9f)
        )
    }
}

@Composable
fun NavHeaders(
    scene: Scene,
    sceneState: SceneState,
    onToggleRehearsalOptions: (RehearsalOptions) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Blue)

    ) {
        Column(
            modifier = Modifier
                .weight(if (sceneState.toggledHeader == RehearsalOptions.Script) 1f else 0.3f)
                .background(Color.Green)
                .fillMaxHeight()
                .clickable { onToggleRehearsalOptions(RehearsalOptions.Script) }

        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "Script",
                    fontSize = 22.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
        Column(
            modifier = Modifier
                .weight(
                    if (scene !is Song) 0.0001f
                    else {
                        if (sceneState.toggledHeader == RehearsalOptions.Score) 1f else 0.3f
                    }
                )
                .background(Color.Cyan)
                .fillMaxHeight()
                .clickable { onToggleRehearsalOptions(RehearsalOptions.Score) }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "Score",
                    fontSize = 22.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
        Column(
            modifier = Modifier
                .weight(if (sceneState.toggledHeader == RehearsalOptions.Scene) 1f else 0.3f)
                .background(Color.Yellow)
                .fillMaxHeight()
                .clickable { onToggleRehearsalOptions(RehearsalOptions.Scene) }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "Scene",
                    fontSize = 22.sp,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                )
            }
        }
    }
}

@Composable
fun SceneView(
    scene: Scene,
    sceneState: SceneState,
    onNextScene: (RehearsalOptions) -> Unit,
    onPrevScene: (RehearsalOptions) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ) {
        Box(
            modifier = Modifier
                .weight(if (sceneState.toggledHeader == RehearsalOptions.Script) 1f else 0.001f)
        ) {
            if (scene.scriptPath != null) {
                PdfViewer(
                    pdfFilePath = scene.scriptPath!!,
                    initialPage = sceneState.scriptPage - 1,
                    startPage = scene.startPage - 1,
                    lastPage = scene.endPage - 1,
                    onTurnLastPage = { onNextScene(RehearsalOptions.Script) },
                    onTurnFirstPage = { onPrevScene(RehearsalOptions.Script) },
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Cyan)
                )
            }
        }
        Box(
            modifier = Modifier
                .weight(if (sceneState.toggledHeader == RehearsalOptions.Score) 1f else 0.001f)
        ) {
            if (scene is Song && scene.scorePath != null) {
                PdfViewer(
                    pdfFilePath = scene.scorePath!!,
                    initialPage = sceneState.scorePage?.minus(1) ?: 0,
                    startPage = if (scene.scoreStartPage != null) scene.scoreStartPage!! - 1 else 1,
                    lastPage = if (scene.scoreEndPage != null) scene.scoreEndPage!! - 1 else -1,
                    onTurnLastPage = { onNextScene(RehearsalOptions.Score) },
                    onTurnFirstPage = { onPrevScene(RehearsalOptions.Score) },
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Magenta)
                )
            }
        }
        Box(
            modifier = Modifier
                .weight(if (sceneState.toggledHeader == RehearsalOptions.Scene) 1f else 0.001f)
        ) {

        }
    }
}

@Composable
fun AudioPlayer(
    scene: Scene,
    sceneState: SceneState, //ToDo: Us this to handle playback speed, currently selected audioPath, loop1, etc.
    onNextScene: () -> Unit,
    onPrevScene: () -> Unit,
    onAudioChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val mediaPlayerViewModel: MediaPlayerViewModel =
        viewModel(factory = MediaPlayerViewModelFactory(context))


    LaunchedEffect(scene) {
        println("Scene changed! Resetting audioplayer...")
        mediaPlayerViewModel.resetMediaPlayer(sceneState.audioPath)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MediaPlayerTimeLine(
            mediaPlayerViewModel,
            onTimeLineDrag = { newCurrentPosition ->
                mediaPlayerViewModel.seekTo(newCurrentPosition)
            }
        )
        Text(text = stringResource(scene.name))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (scene is Song) {
                AudioDropdown(
                    scene,
                    sceneState,
                    onAudioChange,
                    mediaPlayerViewModel,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            } else {
                //Show characters with lines
                DropdownMenu(
                    optionsDisplayNames = listOf(), optionsValues = listOf(), selectedOption = "",
                    onOptionSelected = {},
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }

            Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .wrapContentWidth(),
                horizontalArrangement = Arrangement.spacedBy(0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onPrevScene() }
                ) {
                    Icon(
                        Icons.Filled.SkipPrevious,
                        contentDescription = "Prev",
                        modifier = Modifier.padding(0.dp)
                    )
                }
                IconButton(onClick = {
                    if (mediaPlayerViewModel.isPlaying)
                        mediaPlayerViewModel.pausePlayback()
                    else
                        mediaPlayerViewModel.startPlayback()
                }) {
                    Icon(
                        if (mediaPlayerViewModel.isPlaying)
                            Icons.Filled.Pause
                        else
                            Icons.Filled.PlayArrow,
                        contentDescription = if (mediaPlayerViewModel.isPlaying) "Pause" else "Play"
                    )
                }
                IconButton(
                    onClick = { onNextScene() }
                ) {
                    Icon(
                        Icons.Filled.SkipNext,
                        contentDescription = "Next"
                    )
                }
            }

            // Composable aligned to the right
            IconButton(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            ) {
                Icon(
                    Icons.Filled.Flag,
                    contentDescription = "Right Aligned Icon",
                )
            }
        }
    }
}

@Composable
fun AudioDropdown(
    scene: Song,
    sceneState: SceneState,
    onAudioChange: (String) -> Unit,
    mediaPlayerViewModel: MediaPlayerViewModel,
    modifier: Modifier = Modifier
) {
    // Precompute display names and values in a composable context
    val optionsDisplayNames by remember(scene) {
        derivedStateOf {
            scene.tracks.map { tr -> tr.name } + scene.masterAudio?.name
        }
    }
    val optionsValues by remember(scene) {
        derivedStateOf {
            scene.tracks.map { tr -> tr.audioPath } + scene.masterAudio?.audioPath
        }
    }

    Log.v("test", "updating audiodropdownmenu")
    sceneState.audioPath?.let { audioPath ->
        key(audioPath) {
            Log.v("test", audioPath)
            DropdownMenu(
                optionsDisplayNames = optionsDisplayNames,
                optionsValues = optionsValues,
                selectedOption = sceneState.audioPath,
                onOptionSelected = { option ->
                    onAudioChange(option)
                    mediaPlayerViewModel.changeAudio(option)
                }
            )
        }
    }
}

@Composable
fun MediaPlayerTimeLine(
    mediaPlayerViewModel: MediaPlayerViewModel,
    onTimeLineDrag: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val circleSize = 24.dp
    val circleSizePx = with(LocalDensity.current) { circleSize.toPx() }
    var timeLineSize by remember { mutableStateOf(Size.Zero) }

    val circleX = remember(mediaPlayerViewModel.currentPosition, mediaPlayerViewModel.duration) {
        if (mediaPlayerViewModel.duration > 0)
            mediaPlayerViewModel.currentPosition.toFloat() / mediaPlayerViewModel.duration.toFloat() * timeLineSize.width - circleSizePx / 2
        else 0f
    }
    val currentPositionState = rememberUpdatedState(mediaPlayerViewModel.currentPosition)
    val durationState = rememberUpdatedState(mediaPlayerViewModel.duration)

    Box(
        modifier = Modifier
            .background(Color.Red)
            .fillMaxWidth()
            .onGloballyPositioned { layoutCoordinates ->
                timeLineSize = layoutCoordinates.size.toSize()
            }
    ) {
        Box(
            modifier = Modifier
                .size(circleSize)
                .offset() { IntOffset(circleX.roundToInt(), 0) }
                .clip(CircleShape)
                .background(Color.Green)
                .pointerInput(Unit) {
                    detectDragGestures { _, dragAmount ->
                        val dragDelta =
                            (durationState.value * dragAmount.x / timeLineSize.width).toInt()
                        Log.v(
                            "dragTest",
                            "dragDelta: " + dragDelta + " durationState.value " + durationState.value
                        )
                        val newCurrentPosition =
                            (currentPositionState.value + dragDelta).coerceIn(
                                0,
                                durationState.value
                            )
                        Log.v(
                            "dragTest",
                            "newCurrentPosition: " + newCurrentPosition + " currentPositionState.value " + currentPositionState.value
                        )
                        onTimeLineDrag(newCurrentPosition)
                    }
                }
        )
        Text(
            text = formatTime(mediaPlayerViewModel.currentPosition) + "/" + formatTime(
                mediaPlayerViewModel.duration
            ),
            modifier = Modifier
                .align(Alignment.TopCenter)
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenu(
    optionsDisplayNames: List<Int?>,
    optionsValues: List<String?>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    require(optionsDisplayNames.size == optionsValues.size) { "Visible options and actual values must have the same size." }
    Log.v("test", "updating dropdownmenu")

    var expanded by remember { mutableStateOf(false) }
    val selectedOptionDisplayName =
        optionsDisplayNames.getOrNull(optionsValues.indexOf(selectedOption))

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .widthIn(min = 100.dp, max = 150.dp)
                .background(MaterialTheme.colorScheme.surface)
                .border(BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)))
                .padding(start = 2.dp, end = 0.dp) // Padding for the text field
                .clip(RoundedCornerShape(4.dp))
                .menuAnchor()
        ) {
            Row(
                modifier = Modifier
                    // .background(Color.Yellow)
                    .padding(2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = selectedOptionDisplayName?.let { stringResource(it) } ?: "",
                    onValueChange = {},
                    enabled = false,
                    singleLine = true,
                    interactionSource = remember { MutableInteractionSource() },
                    modifier = Modifier
                        // .weight(1f) // Take up remaining space
                        .padding(0.dp) // Padding inside the text field
                ) { innerTextField ->
                    TextFieldDefaults.DecorationBox(
                        value = selectedOptionDisplayName?.let { stringResource(it) } ?: "",
                        visualTransformation = VisualTransformation.None,
                        innerTextField = innerTextField,
                        singleLine = true,
                        enabled = false,
                        interactionSource = remember { MutableInteractionSource() },
                        contentPadding = PaddingValues(2.dp),

                        )
                }
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Dropdown",
                )
            }
        }
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { /*TODO*/ }
        ) {
            optionsDisplayNames.forEachIndexed { index, optionDisplayName ->
                DropdownMenuItem(
                    onClick = {
                        optionsValues[index]?.let { onOptionSelected(it) }
                        expanded = false
                    },
                    text = { Text(stringResource(optionDisplayName!!)) })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RehearseScreenPreview() {
    NavHeadersAndSceneView(
        scene = Song(
            name = R.string.wiz_oz_scene1,
            startPage = 3,
            endPage = 3,
        ),
        onNextScene = {},
        onPrevScene = {},
        onToggleRehearsalOptions = {},
        sceneState = SceneState()
    )
}

@Preview(showBackground = true, heightDp = 200)
@Composable
fun AudioPlayerPreview() {
    AudioPlayer(
        scene = Song(
            name = R.string.wiz_oz_scene1,
            startPage = 3,
            endPage = 3,
            masterAudio = AudioObject(R.string.backing_track, "")
        ),
        onNextScene = {},
        onPrevScene = {},
        onAudioChange = {},
        sceneState = SceneState()
    )
}

@Preview(showBackground = true, heightDp = 200)
@Composable
fun DropdownPreview() {
    DropdownMenu(
        optionsDisplayNames = listOf(1, 2),
        optionsValues = listOf("value", "value2"),
        selectedOption = "value",
        onOptionSelected = { }
    )
}