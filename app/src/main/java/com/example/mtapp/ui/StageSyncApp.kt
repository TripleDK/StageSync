package com.example.mtapp.ui

import androidx.annotation.StringRes
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mtapp.R
import com.example.mtapp.ui.screens.RehearseScreen
import com.example.mtapp.ui.screens.ScenesListContent
import com.example.mtapp.ui.screens.ShowsListContent
import com.example.mtapp.ui.theme.MTAPPTheme

enum class StageSyncScreen(@StringRes val title: Int) {
    Shows(title = R.string.app_name),
    Scenes(title = R.string.scene_list),
    Rehearse(title = R.string.rehearsal)
}


@Composable
fun StageSyncApp(
    stageSyncViewModel: StageSyncViewModel = viewModel(factory = StageSyncViewModel.Factory),
    navController: NavHostController = rememberNavController()
) {
    MTAPPTheme {
        Surface {
            val uiState by stageSyncViewModel.uiState.collectAsState()

            NavHost(
                navController = navController,
                startDestination = StageSyncScreen.Shows.name,
                modifier = Modifier
            ) {
                composable(route = StageSyncScreen.Shows.name) {
                    ShowsListContent(
                        stageSyncViewModel.shows,
                        onShowClicked = {
                            stageSyncViewModel.setSelectedShow(it)
                            navController.navigate(StageSyncScreen.Scenes.name)
                        }
                    )
                }
                composable(route = StageSyncScreen.Scenes.name) {
                    uiState.currentShow?.let { curShow ->
                        ScenesListContent(
                            curShow.scenes,
                            onSceneClicked = {
                                stageSyncViewModel.setSelectedScene(it)
                                navController.navigate(StageSyncScreen.Rehearse.name)
                            }
                        )
                    }
                }
                composable(route = StageSyncScreen.Rehearse.name) {
                    uiState.currentScene?.let { curScene ->
                        RehearseScreen(stageSyncViewModel)
                    }
                }
            }
        }
    }
}

