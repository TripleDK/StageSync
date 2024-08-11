package com.example.mtapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mtapp.Models.Scene
import com.example.mtapp.R
import com.example.mtapp.data.DataSource
import com.example.mtapp.data.RehearsalOptions
import com.example.mtapp.data.SceneType
import com.example.mtapp.ui.StageSyncViewModel
import com.example.mtapp.ui.components.PdfPageView
import com.example.mtapp.ui.components.PdfViewer

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
                    .background(Color.DarkGray)
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
        if (scene.type == SceneType.Song) {
            Column(
                modifier = Modifier
                    .weight(if (toggledHeader == RehearsalOptions.Score) 1f else 0.3f)
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
        }
    }
}

@Composable
fun SceneView(
    scene: Scene,
    onNextScene: () -> Unit,
    onPrevScene: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ) {
        Text(text = "Scene here!")
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
}

@Composable
fun AudioPlayer(scene: Scene, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(text = stringResource(scene.name))
    }
}

@Preview(showBackground = true)
@Composable
fun RehearseScreenPreview() {
    NavHeadersAndSceneView(
        scene = Scene(
            name = R.string.wiz_oz_scene1,
            type = SceneType.Song,
            startPage = 3,
            endPage = 3,
        ),
        onNextScene = {},
        onPrevScene = {},
        onToggleRehearsalOptions = {},
        toggledHeader = RehearsalOptions.Scene
    )
}
