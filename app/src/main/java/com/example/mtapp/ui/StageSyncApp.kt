package com.example.mtapp.ui

import ScenesListContent
import ShowScenesListDestination
import androidx.annotation.StringRes
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mtapp.R
import com.example.mtapp.ui.screens.shows.ShowCreateScreen
import com.example.mtapp.ui.screens.shows.ShowListViewModel
import com.example.mtapp.ui.screens.shows.ShowsListContent
import com.example.mtapp.ui.theme.MTAPPTheme

enum class StageSyncScreen(@StringRes val title: Int) {
    Shows(title = R.string.app_name),
    AddShow(title = R.string.add_show),
    Rehearse(title = R.string.rehearsal)
}


@Composable
fun StageSyncApp(
    navController: NavHostController = rememberNavController()
) {
    MTAPPTheme {
        Surface {

            NavHost(
                navController = navController,
                startDestination = StageSyncScreen.Shows.name,
                modifier = Modifier
            ) {
                composable(route = StageSyncScreen.Shows.name) {
                    ShowsListContent(
                        onAddShowClicked = {
                            navController.navigate(StageSyncScreen.AddShow.name)
                        },
                        //TODO: This
//                        onEditShowClicked = {
//                            navController.navigate(StageSyncScreen.EditShow.name)
//                        },
                        onShowClicked = {
                            navController.navigate("${ShowScenesListDestination.route}/${it}")
                        }
                    )
                }
                composable(route = StageSyncScreen.AddShow.name) {
                    ShowCreateScreen(
                        navigateBack = {
                            navController.navigate(StageSyncScreen.Shows.name)
                        }
                    )
                }
                composable(
                    route = ShowScenesListDestination.routeWithArgs,
                    arguments = listOf(navArgument(ShowScenesListDestination.showIdArg) {
                        type = NavType.IntType
                    })
                )
                {
                    ScenesListContent(
                        onBack = {
                            navController.navigate(StageSyncScreen.Shows.name)
                        },
                        onSceneClicked = {
                            navController.navigate(StageSyncScreen.Rehearse.name)
                        }
                    )
                }
//                composable(route = StageSyncScreen.Rehearse.name) {
//                    uiState.currentScene?.let { curScene ->
//                        RehearseScreen(stageSyncViewModel)
//                    }
//                }
            }
        }
    }
}

