package com.example.mtapp.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mtapp.Models.Scene
import com.example.mtapp.data.DataSource
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
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        NavHeaders(scene, modifier = Modifier.weight(0.2f))
        SceneView(scene, modifier = Modifier.weight(0.8f))
    }
}

@Composable
fun NavHeaders(
    scene: Scene,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Blue)

    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .background(Color.Yellow)
                .fillMaxHeight()
        ) {
            Text(text = "Stage")
        }
        Column(
            modifier = Modifier
                .weight(0.3f)
                .background(Color.Green)
                .fillMaxHeight()
        ) {
            Text(text = "Script")
        }
        if (scene.type == SceneType.Song) {
            Column(
                modifier = Modifier
                    .weight(0.3f)
                    .background(Color.Cyan)
                    .fillMaxHeight()
            ) {
                Text(text = "Score")
            }
        }
    }
}

@Composable
fun SceneView(
    scene: Scene,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ) {
        Text(text = "Scene here!")
        PdfViewer(
            pdfFilePath = scene.scriptPath!!,
            initialPage = scene.startPage!! - 1,
            lastPage = scene.
            modifier = Modifier.fillMaxSize()
        )
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
    RehearseScreen(
        viewModel = viewModel(factory = StageSyncViewModel.Factory)
    )
}
