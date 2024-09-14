import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mtapp.ui.navigation.NavigationDestination

//package com.example.mtapp.ui.screens.scenes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mtapp.R
import com.example.mtapp.data.Scene
import com.example.mtapp.ui.AppViewModelProvider
import com.example.mtapp.ui.components.FullScreenPdfViewer
import com.example.mtapp.ui.screens.scenes.SceneListViewModel

//import com.example.mtapp.ui.components.FullScreenPdfViewer

object ShowScenesListDestination : NavigationDestination {
    override val route = "show_scenes"
    override val titleRes = R.string.scene_list
    const val showIdArg = "showId"
    val routeWithArgs = "$route/{${showIdArg}}"
}

@Composable
fun ScenesListContent(
    onSceneClicked: (Int) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SceneListViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val sceneListUiState by viewModel.sceneListUiState.collectAsState()
    val show = sceneListUiState.show
    val scenes = sceneListUiState.scenes

    val scenesByAct = scenes.groupBy { it.actNumber }
    var currentAct by remember { mutableStateOf(1) }
    var showSchedule by remember { mutableStateOf(false) }

    if (show != null) {
        Column(modifier = modifier) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    IconButton(
                        onClick = onBack,
                        enabled = !show.schedulePath.isNullOrEmpty(),
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                    Text(
                        text = show.name,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.align(Alignment.Center)
                    )
                    IconButton(
                        onClick = { showSchedule = true },
                        enabled = !show.schedulePath.isNullOrEmpty(),
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Icon(Icons.Filled.CalendarMonth, contentDescription = "Schedule")
                    }

                }
            }
            ActHeader(
                currentAct = currentAct,
                onPreviousAct = { currentAct-- },
                onNextAct = { currentAct++ },
                totalActs = scenesByAct.keys.size
            )
            ScenesByAct(
                scenesByAct = scenesByAct,
                currentAct = currentAct,
                onSceneClicked = onSceneClicked,
                modifier = Modifier.weight(1f)
            )
        }

        if (!show?.schedulePath.isNullOrEmpty()) {
            FullScreenPdfViewer(
                isVisible = showSchedule,
                onDismiss = { showSchedule = false },
                pdfFilePath = sceneListUiState.show?.schedulePath!!
            )
        }
    } else {
        Text("Something wrong, no show")
    }
}

@Composable
fun ActHeader(
    currentAct: Int,
    onPreviousAct: () -> Unit,
    onNextAct: () -> Unit,
    totalActs: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        IconButton(onClick = onPreviousAct, enabled = currentAct > 1) {
            Icon(Icons.Filled.ArrowBackIosNew, contentDescription = "Previous Act")
        }
        Text(text = "Act $currentAct", style = MaterialTheme.typography.headlineSmall)
        IconButton(onClick = onNextAct, enabled = currentAct < totalActs) {
            Icon(Icons.Filled.ArrowForwardIos, contentDescription = "Next Act")
        }
    }
}

@Composable
fun ScenesByAct(
    scenesByAct: Map<Int, List<Scene>>,
    currentAct: Int,
    onSceneClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val scenes = scenesByAct[currentAct] ?: emptyList()
    // Group scenes by sceneNumber
    val scenesGroupedByNumber = scenes.groupBy { it.sceneNumber }

    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
        modifier = modifier
    ) {
        scenesGroupedByNumber.forEach { (sceneNumber, scenesForNumber) ->
            item {
                Text(
                    text = "Scene $sceneNumber"
                )
            }
            items(scenesForNumber) { scene ->
                SceneListItem(
                    scene = scene,
                    onClick = { onSceneClicked(scene.id) }
                )
            }
        }
    }
}

@Composable
fun SceneListItem(
    scene: Scene,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier
        .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (scene.isSong) {
                Icon(
                    Icons.Filled.MusicNote,
                    contentDescription = "Prev",
                    modifier = Modifier.padding(0.dp)
                )
            }
            Text(
                text = scene.name
            )
        }
    }
}

//@Preview
//@Composable
//fun SceneListItemPreview() {
//    SceneListItem(
//        scene = Scene(
//            name = R.string.wiz_oz_scene6,
//            actNumber = 1,
//            sceneNumber = 1,
//            startPage = 3,
//            endPage = 3
//        )
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ScenesListContentPreview() {
//    ScenesListContent(
//        show = Show(
//            name = R.string.app_name,
//            scenes = listOf(
//                Scene(
//                    name = R.string.wiz_oz_scene6,
//                    actNumber = 1,
//                    sceneNumber = 1,
//                    startPage = 3,
//                    endPage = 3,
//                    scriptPath = ""
//                ),
//                Song(
//                    name = R.string.wiz_oz_scene7,
//                    actNumber = 1,
//                    sceneNumber = 1,
//                    startPage = 3,
//                    endPage = 3,
//                    scriptPath = ""
//                )
//            ),
//        ),
//        onSceneClicked = {},
//        onBack = {}
//    )
//}
