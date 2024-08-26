package com.example.mtapp.ui.screens

import android.media.MediaPlayer
import android.net.Uri
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.mtapp.Models.AudioObject
import com.example.mtapp.Models.Scene
import com.example.mtapp.Models.Song
import com.example.mtapp.R
import com.example.mtapp.data.RehearsalOptions
import com.example.mtapp.data.SceneState
import com.example.mtapp.ui.StageSyncViewModel
import com.example.mtapp.ui.components.PdfViewer
import com.example.mtapp.utils.formatTime
import kotlinx.coroutines.delay
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
                    startPage = scene.startPage!! - 1,
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
    modifier: Modifier = Modifier
) {
    var isPlaying by remember { mutableStateOf(false) }
    val context = LocalContext.current

    var audioPath by remember { mutableStateOf(if (scene is Song) scene.masterAudio?.audioPath else "") }
    val mediaPlayer = remember {
        if (audioPath?.isNotEmpty() == true) {
            MediaPlayer().apply {
                try {
                    setDataSource(context, Uri.parse(audioPath)) // Use URI for file path
                    prepare() // Prepare the media player
                    setOnCompletionListener {
                        isPlaying = false
                    }
                } catch (e: Exception) {
                    e.printStackTrace() // Handle or log the exception
                }
            }
        } else
            null
    }
    var currentPosition by remember { mutableStateOf(0) }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
        }
    }


    //The drag function should then set the position on its own time
    LaunchedEffect(isPlaying) {
        while (mediaPlayer != null && isPlaying) {
            currentPosition = mediaPlayer.currentPosition
            delay(100)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MediaPlayerTimeLine(
            duration = mediaPlayer?.duration ?: 0,
            currentPosition = currentPosition,
            onTimeLineDrag = { newCurrentPosition ->
                mediaPlayer?.seekTo(newCurrentPosition)
            }
        )
//        Text(
//            text = "currentPosition: " + currentPosition + ", " + stringResource(scene.name) + ", duration: " + mediaPlayer?.duration
//                ?: 0,
//        )
        Text(text = stringResource(scene.name))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (scene is Song && scene.tracks != null) {
                audioPath?.let { it1 ->
                    DropdownMenu(
                        optionsDisplayNames = scene.tracks!!.map { tr -> stringResource(tr.name) }
                                + stringResource(scene.masterAudio!!.name),
                        optionsValues = scene.tracks!!.map { tr -> tr.audioPath } + scene.masterAudio?.audioPath,
                        selectedOption = it1,
                        onOptionSelected = { option ->
                            audioPath = option
                            if (mediaPlayer != null) {
                                val currentAudioPostion = mediaPlayer.currentPosition
                                mediaPlayer.reset()
                                mediaPlayer.setDataSource(context, Uri.parse(audioPath))
                                mediaPlayer.prepare()
                                mediaPlayer.seekTo(currentAudioPostion)

                                if (isPlaying)
                                    mediaPlayer.start()
                            }
                        },
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                }
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
                    if (isPlaying)
                        mediaPlayer?.pause()
                    else
                        mediaPlayer?.start()
                    isPlaying = !isPlaying
                }) {
                    Icon(
                        if (isPlaying)
                            Icons.Filled.Pause
                        else
                            Icons.Filled.PlayArrow,
                        contentDescription = if (isPlaying) "Pause" else "Play"
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
fun MediaPlayerTimeLine(
    duration: Int,
    currentPosition: Int,
    onTimeLineDrag: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val circleSize = 24.dp
    val circleSizePx = with(LocalDensity.current) { circleSize.toPx() }
    var timeLineSize by remember { mutableStateOf(Size.Zero) }

    var circleX = remember(currentPosition, duration) {
        if (duration > 0)
            currentPosition.toFloat() / duration.toFloat() * timeLineSize.width - circleSizePx / 2
        else 0f
    }
    val currentPositionState = rememberUpdatedState(currentPosition)
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
                        val dragDelta = (duration * dragAmount.x / timeLineSize.width).toInt()
                        val newCurrentPosition =
                            (currentPositionState.value + dragDelta).coerceIn(0, duration)
                        onTimeLineDrag(newCurrentPosition)
                    }
                }
        )
        Text(
            text = formatTime(currentPosition) + "/" + formatTime(duration),
            modifier = Modifier
                .align(Alignment.TopCenter)
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenu(
    optionsDisplayNames: List<String>,
    optionsValues: List<String?>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    require(optionsDisplayNames.size == optionsValues.size) { "Visible options and actual values must have the same size." }

    var expanded by remember { mutableStateOf(false) }
    val selectedOptionDisplayName =
        optionsDisplayNames.getOrNull(optionsValues.indexOf(selectedOption)) ?: ""

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
                    //  .fillMaxWidth()
                    .background(Color.Yellow)
                    .padding(2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = selectedOptionDisplayName,
                    onValueChange = {},
                    enabled = false,
                    singleLine = true,
                    interactionSource = remember { MutableInteractionSource() },
                    modifier = Modifier
                        // .weight(1f) // Take up remaining space
                        .padding(0.dp) // Padding inside the text field
                ) { innerTextField ->
                    TextFieldDefaults.DecorationBox(
                        value = selectedOptionDisplayName,
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
                    text = { Text(optionDisplayName!!) })
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
        sceneState = SceneState()
    )
}

@Preview(showBackground = true, heightDp = 200)
@Composable
fun DropdownPreview() {
    DropdownMenu(
        optionsDisplayNames = listOf("test", "test2"),
        optionsValues = listOf("value", "value2"),
        selectedOption = "value",
        onOptionSelected = { }
    )
}