package com.example.mtapp.ui.screens.rehearsalscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mtapp.Models.Scene
import com.example.mtapp.Models.Show
import com.example.mtapp.Models.Song
import com.example.mtapp.R
import com.example.mtapp.data.RehearsalOptions
import com.example.mtapp.data.SceneState
import com.example.mtapp.ui.MediaPlayerViewModel
import com.example.mtapp.ui.MediaPlayerViewModelFactory
import com.example.mtapp.ui.StageSyncViewModel
import com.example.mtapp.ui.components.PdfViewer


@Composable
fun RehearseScreen(
    viewModel: StageSyncViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        if (uiState.currentScene != null) {
            val context = LocalContext.current
            val mediaPlayerViewModel: MediaPlayerViewModel =
                viewModel(factory = MediaPlayerViewModelFactory(context))
            NavHeadersAndSceneView(
                uiState.currentShow!!,
                uiState.currentScene!!,
                uiState.sceneStates[uiState.currentScene!!]!!,
                mediaPlayerViewModel,
                onNextScene = { src -> viewModel.setNextScene(src) },
                onPrevScene = { src -> viewModel.setPrevScene(src) },
                onToggleRehearsalOptions = { option -> viewModel.toggleRehearsalOptions(option) },
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxSize()
            )


            AudioPlayer(
                uiState.currentScene!!,
                uiState.sceneStates[uiState.currentScene!!]!!,
                onNextScene = { viewModel.setNextScene() },
                onPrevScene = { viewModel.setPrevScene() },
                onAudioChange = { audioPath -> viewModel.updateSceneAudioPath(audioPath) },
                mediaPlayerViewModel = mediaPlayerViewModel,
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
    show: Show,
    scene: Scene,
    sceneState: SceneState,
    mediaPlayerViewModel: MediaPlayerViewModel,
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
            show,
            scene,
            sceneState,
            mediaPlayerViewModel,
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

    ) {
        NavHeader(
            sceneState = sceneState,
            headerTitle = "Script",
            headerType = RehearsalOptions.Script,
            onToggleRehearsalOptions = onToggleRehearsalOptions,
            modifier = Modifier
                .weight(if (sceneState.toggledHeader == RehearsalOptions.Script) 1f else 0.3f)
        )
        NavHeader(
            sceneState = sceneState,
            headerTitle = "Score",
            headerType = RehearsalOptions.Score,
            onToggleRehearsalOptions = onToggleRehearsalOptions,
            modifier = Modifier
                .weight(
                    if (scene !is Song) 0.0001f
                    else {
                        if (sceneState.toggledHeader == RehearsalOptions.Score) 1f else 0.3f
                    }
                )
        )
        NavHeader(
            sceneState = sceneState,
            headerTitle = "Scene",
            headerType = RehearsalOptions.Scene,
            onToggleRehearsalOptions = onToggleRehearsalOptions,
            modifier = Modifier
                .weight(if (sceneState.toggledHeader == RehearsalOptions.Scene) 1f else 0.3f)
        )
    }
}

@Composable
fun NavHeader(
    sceneState: SceneState,
    headerTitle: String,
    headerType: RehearsalOptions,
    onToggleRehearsalOptions: (RehearsalOptions) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme
    val backgroundColor =
        if (sceneState.toggledHeader == headerType) colors.primary else colors.surface
    val textColor =
        if (sceneState.toggledHeader == headerType) colors.onPrimary else colors.onSurface
    val borderColor =
        colors.onSurface
    val headerShape = RoundedCornerShape(
        topEndPercent = 20,
        topStartPercent = 20
    )
    Column(
        modifier = modifier
            .background(backgroundColor, shape = headerShape)
            .fillMaxHeight()
            .clickable { onToggleRehearsalOptions(headerType) }
            .border(
                width = 2.dp,
                color = borderColor,
                shape = headerShape
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text(
                text = headerTitle,
                fontSize = 16.sp,
                color = textColor,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
fun SceneView(
    show: Show,
    scene: Scene,
    sceneState: SceneState,
    mediaPlayerViewModel: MediaPlayerViewModel,
    onNextScene: (RehearsalOptions) -> Unit,
    onPrevScene: (RehearsalOptions) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
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
                        .background(Color.White)
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
            ChoreoScreenContainer(show, mediaPlayerViewModel)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RehearseScreenPreview() {
    NavHeadersAndSceneView(
        show = Show(
            name = R.string.vanilla,
            scenes = emptyList()
        ),
        scene = Song(
            name = R.string.wiz_oz_scene1,
            startPage = 3,
            endPage = 3,
            actNumber = 1,
            sceneNumber = 1,
        ),
        onNextScene = {},
        onPrevScene = {},
        onToggleRehearsalOptions = {},
        mediaPlayerViewModel = MediaPlayerViewModel(LocalContext.current),
        sceneState = SceneState()
    )
}

