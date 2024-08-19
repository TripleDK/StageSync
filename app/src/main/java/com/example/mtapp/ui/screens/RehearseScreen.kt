package com.example.mtapp.ui.screens

import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mtapp.Models.AudioObject
import com.example.mtapp.Models.Scene
import com.example.mtapp.Models.Song
import com.example.mtapp.R
import com.example.mtapp.data.RehearsalOptions
import com.example.mtapp.ui.StageSyncViewModel
import com.example.mtapp.ui.components.PdfViewer
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
                uiState.currentHeader,
                onNextScene = { viewModel.setNextScene() },
                onPrevScene = { viewModel.setPrevScene() },
                onToggleRehearsalOptions = { option -> viewModel.toggleRehearsalOptions(option) },
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxSize()
                    .background(Color.LightGray)
            )

            AudioPlayer(
                uiState.currentScene!!,
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
    toggledHeader: RehearsalOptions,
    onNextScene: () -> Unit,
    onPrevScene: () -> Unit,
    onToggleRehearsalOptions: (RehearsalOptions) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        NavHeaders(
            scene,
            toggledHeader,
            onToggleRehearsalOptions = onToggleRehearsalOptions,
            modifier = Modifier.weight(0.2f)
        )
        SceneView(
            scene,
            toggledHeader,
            onNextScene = onNextScene,
            onPrevScene = onPrevScene,
            modifier = Modifier.weight(0.8f)
        )
    }
}

@Composable
fun NavHeaders(
    scene: Scene,
    toggledHeader: RehearsalOptions,
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
                .weight(if (toggledHeader == RehearsalOptions.Script) 1f else 0.3f)
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
                        if (toggledHeader == RehearsalOptions.Score) 1f else 0.3f
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
                .weight(if (toggledHeader == RehearsalOptions.Scene) 1f else 0.3f)
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
    toggledHeader: RehearsalOptions,
    onNextScene: () -> Unit,
    onPrevScene: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ) {
        Box(
            modifier = Modifier
                .weight(if (toggledHeader == RehearsalOptions.Scene) 1f else 0.001f)
        ) {

        }
        Box(
            modifier = Modifier
                .weight(if (toggledHeader == RehearsalOptions.Script) 1f else 0.001f)
        ) {
            Text(text = "Script here!")
            if (scene.scriptPath != null) {
                PdfViewer(
                    pdfFilePath = scene.scriptPath!!,
                    initialPage = scene.startPage!! - 1,
                    lastPage = scene.endPage - 1,
                    onTurnLastPage = onNextScene,
                    onTurnFirstPage = onPrevScene,
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
                .weight(if (toggledHeader == RehearsalOptions.Score) 1f else 0.001f)
        ) {
            if (scene.scorePath != null) {
                PdfViewer(
                    pdfFilePath = scene.scorePath!!,
                    initialPage = scene.scoreStartPage!! - 1,
                    lastPage = scene.scoreEndPage!! - 1,
                    onTurnLastPage = onNextScene,
                    onTurnFirstPage = onPrevScene,
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
    }
}

@Composable
fun AudioPlayer(scene: Scene, modifier: Modifier = Modifier) {
    if (scene !is Song) {
        //Not yet implemented!
        return
    }
    var isPlaying by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var audioPath by remember { mutableStateOf(scene.masterAudio?.audioPath) }
    val mediaPlayer = remember {
        if (audioPath?.isNotEmpty() == true) {
            MediaPlayer().apply {
                try {
                    setDataSource(context, Uri.parse(audioPath)) // Use URI for file path
                    prepare() // Prepare the media player
                } catch (e: Exception) {
                    e.printStackTrace() // Handle or log the exception
                }
            }
        } else
            null
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {

        MediaPlayerTimeLine(
            mediaPlayer,
//            modifier = Modifier
//                .background(Color.Red)
        )

        Text(
            text = stringResource(scene.name),
            modifier = Modifier
                .align(Alignment.TopCenter)
        )


        scene.tracks?.let {
            audioPath?.let { it1 ->
                DropdownMenu(
                    optionsDisplayNames = it.map { tr -> stringResource(tr.name) }
                            + stringResource(scene.masterAudio!!.name),
                    optionsValues = it.map { tr -> tr.audioPath } + scene.masterAudio?.audioPath,
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
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                )
            }
        }

        Row(
            modifier = Modifier.align(Alignment.Center)
        ) {
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
        }
        // Composable aligned to the right
        Icon(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Right Aligned Icon",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp) // Optional padding
        )
    }
}

@Composable
fun MediaPlayerTimeLine(mediaPlayer: MediaPlayer?) {
    val circleSize = 24.dp
    // val offset = animateOffset(mediaPlayer!!.currentPosition / mediaPlayer.duration, 0f)

    var duration by remember { mutableStateOf(1f) }
    var circleX by remember { mutableStateOf(0f) }

    LaunchedEffect(mediaPlayer) {
        if (mediaPlayer != null)
            duration = mediaPlayer.duration.toFloat()
    }

    LaunchedEffect(Unit) {
        while (true) {
            mediaPlayer?.let {
                val currentPosition = it.currentPosition.toFloat()
                circleX = (currentPosition / duration) * 300
                println("Current Position: $currentPosition, Circle X: $circleX") // Debug log

                delay(100)
            }
        }
    }

    Spacer(modifier = Modifier.height(5.dp))
    Box(
        modifier = Modifier
            .background(Color.Red)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(circleSize)
                .offset() { IntOffset(circleX.roundToInt(), 0) }
                .clip(CircleShape)
                .background(Color.Green)
                .pointerInput(Unit) {
                    detectDragGestures { _, dragAmount ->
                        circleX += dragAmount.x
                        mediaPlayer?.seekTo((mediaPlayer.currentPosition + dragAmount.x).toInt())
                    }
                }
        )
    }
}


fun animateOffset(
    animationProgress: Int,
    circleSizePx: Float
): IntOffset {
    val xOffSet = (animationProgress)
    return IntOffset(xOffSet.toInt(), 0)
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
        TextField(
            value = selectedOptionDisplayName,
            readOnly = true,
            onValueChange = {},
            singleLine = true,
            trailingIcon = {
                Icon(Icons.Filled.ArrowDropDown, contentDescription = "Dropdown")
            },
            modifier = Modifier
                .widthIn(min = 100.dp, max = 150.dp)
                .menuAnchor()
        )
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
        toggledHeader = RehearsalOptions.Scene
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
        )
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