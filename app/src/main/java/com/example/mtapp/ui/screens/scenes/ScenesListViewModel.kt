package com.example.mtapp.ui.screens.scenes

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mtapp.data.Scene
import com.example.mtapp.StageSyncApplication
import com.example.mtapp.data.OfflineShowsRepository
import com.example.mtapp.data.ScenesRepository
import com.example.mtapp.data.Show
import com.example.mtapp.data.ShowsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


data class ScenesListUiState(
    val show: Show? = null,
    val scenes: List<Scene> = listOf()
) {
//    fun updateCurrentShow(show: Show?): ShowListUiState {
//        return copy(
//            currentShow = show,
//            currentScene = null
//        )
//    }
}

class SceneListViewModel(
    savedStateHandle: SavedStateHandle,
    private val scenesRepository: ScenesRepository,
    private val showsRepository: ShowsRepository
) :
    ViewModel() {

    private val showId: Int = checkNotNull(savedStateHandle[ShowScenesListDestination.showIdArg])

    val sceneListUiState: StateFlow<ScenesListUiState> =
        combine(
            showsRepository.getShowStream(showId),
            scenesRepository.getAllScenesInShowStream(showId)

        ) { show, scenes ->
            ScenesListUiState(
                show = show,
                scenes = scenes
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = ScenesListUiState()
        )
    //  val showListUiState: StateFlow<ShowListUiState> = _showListUiState.asStateFlow()


//    fun setSelectedShow(show: Show?) {
//        _showListUiState.value = _showListUiState.value.updateCurrentShow(show)
//    }

//    fun setSelectedScene(scene: Scene) {
//        _uiState.value = _uiState.value.updateCurrentScene(scene)
//    }

//    fun setSceneState(scene: Scene, newState: SceneState) {
//        _uiState.value = _uiState.value.updateSceneState(scene, newState)
//    }

//    fun setNextScene(source: RehearsalOptions? = null) {
//        val currentShow = requireShow()
//        val currentScene = requireScene()
//        val currentState = requireSceneState(currentScene)
//
//        var nextScene = getNextScene(currentShow, currentScene)
//
//        if (nextScene != null) {
//            var nextState = _uiState.value.sceneStates[nextScene]
//            if (nextState == null) {
//                nextState = SceneState(
//                    scorePage = if (nextScene is Song) {
//                        nextScene.scoreStartPage ?: 0
//                    } else {
//                        0
//                    },
//                    scriptPage = nextScene.startPage,
//                    audioPath = if (currentScene is Song) currentScene.masterAudio?.audioPath else ""
//                )
//            }
//
//            if (source == RehearsalOptions.Script) {
//                val newScriptPage =
//                    if (nextScene.scriptPath == currentScene.scriptPath && nextScene.startPage == currentScene.endPage) {
//                        nextScene.startPage + 1
//                    } else {
//                        nextScene.startPage
//                    }
//                //Check if next scene starts and ends on the same page, in that case we go to the next scene that actually starts on the next page
//                while (nextScene?.startPage == nextScene?.endPage && nextScene != null) {
//                    nextScene = getNextScene(currentShow, nextScene)
//                }
//                if (nextScene == null)
//                    return
//
//                setSceneState(nextScene, nextState.copy(scriptPage = newScriptPage))
//            }
//            if (source == RehearsalOptions.Score) {
//                //Since we swipe from score, we go to the next song and skip normal scenes
//                while (nextScene !is Song && nextScene != null) {
//                    nextScene = getNextScene(currentShow, nextScene)
//                }
//                //If no more songs, do nothing
//                if (nextScene == null) {
//                    return
//                }
//                if (currentScene is Song && nextScene is Song) { //It HAS to be a song at this point, just for linter's sake!
//                    val newScorePage =
//                        if (nextScene.scorePath == currentScene.scorePath && nextScene.scoreStartPage == currentScene.scoreEndPage) {
//                            currentState.scorePage?.plus(1)
//                        } else {
//                            nextScene.scoreStartPage
//                        }
//                    //Check if next scene starts and ends on the same page, in that case we go to the next scene that actually starts on the next page
//                    while (nextScene?.startPage == nextScene?.endPage && nextScene != null) {
//                        nextScene = getNextScene(currentShow, nextScene)
//                    }
//                    if (nextScene == null)
//                        return
//
//                    _uiState.value.updateSceneState(
//                        nextScene,
//                        nextState.copy(scorePage = newScorePage)
//                    )
//                }
//            }
//
//            setSelectedScene(nextScene)
//        } else {
//            Log.v("MainActivity", "Already at last scene!")
//        }
//    }

//    private fun getNextScene(show: Show, scene: Scene): Scene? {
//        val scenes = show.scenes
//        val currentSceneIndex = scenes.indexOf(scene)
//        return if (currentSceneIndex < scenes.size - 1) {
//            scenes[currentSceneIndex + 1]
//        } else {
//            null
//        }
//    }

//    fun setPrevScene(source: RehearsalOptions? = null) {
//        val currentShow = requireShow()
//        val currentScene = requireScene()
//        val currentState = requireSceneState(currentScene)
//
//        var prevScene = getPrevScene(currentShow, currentScene)
//
//        if (prevScene != null) {
//            var prevState = _uiState.value.sceneStates[prevScene]
//            if (prevState == null) {
//                prevState = SceneState(
//                    scorePage = if (prevScene is Song) {
//                        prevScene.scoreStartPage ?: 0
//                    } else {
//                        0
//                    },
//                    scriptPage = prevScene.startPage,
//                    audioPath = if (currentScene is Song) currentScene.masterAudio?.audioPath else ""
//                )
//            }
//            if (source == RehearsalOptions.Script) {
//                val newScriptPage =
//                    if (prevScene.scriptPath == currentScene.scriptPath && prevScene.endPage == currentScene.startPage) {
//                        prevScene.endPage - 1
//                    } else {
//                        prevScene.endPage
//                    }
//                //Check if next scene starts and ends on the same page, in that case we go to the next scene that actually starts on the next page
//                while (prevScene?.startPage == prevScene?.endPage && prevScene != null) {
//                    prevScene = getPrevScene(currentShow, prevScene)
//                }
//                if (prevScene == null)
//                    return
//
//                setSceneState(prevScene, prevState.copy(scriptPage = newScriptPage))
//            }
//            if (source == RehearsalOptions.Score) {
//                //Since we swipe from score, we go to the next song and skip normal scenes
//                while (prevScene !is Song && prevScene != null) {
//                    prevScene = getPrevScene(currentShow, prevScene)
//                }
//                //If no more songs, do nothing
//                if (prevScene == null) {
//                    return
//                }
//                if (currentScene is Song && prevScene is Song) { //It HAS to be a song at this point, just for linter's sake!
//                    val newScorePage =
//                        if (prevScene.scorePath == currentScene.scorePath && prevScene.scoreStartPage == currentScene.scoreEndPage) {
//                            currentState.scorePage?.plus(1)
//                        } else {
//                            prevScene.scoreStartPage
//                        }
//                    //Check if previous scene starts and ends on the same page, in that case we go to the previous scene that actually starts on the previous page
//                    while (prevScene?.startPage == prevScene?.endPage && prevScene != null) {
//                        prevScene = getPrevScene(currentShow, prevScene)
//                    }
//                    if (prevScene == null)
//                        return
//
//                    _uiState.value.updateSceneState(
//                        prevScene,
//                        prevState.copy(scorePage = newScorePage)
//                    )
//                }
//            }
//
//            setSelectedScene(prevScene)
//        } else {
//            Log.v("MainActivity", "Already at first scene!")
//        }
//    }
//
//    private fun getPrevScene(show: Show, scene: Scene): Scene? {
//        val scenes = show.scenes
//        val currentSceneIndex = scenes.indexOf(scene)
//        return if (currentSceneIndex > 0) {
//            scenes[currentSceneIndex - 1]
//        } else {
//            null
//        }
//    }


//    fun toggleRehearsalOptions(options: RehearsalOptions) {
//        val scene = requireScene()
//        val sceneState = requireSceneState(scene)
//        setSceneState(scene, sceneState.copy(toggledHeader = options))
//    }
//
//    fun updateSceneAudioPath(audioPath: String) {
//        val scene = requireScene()
//        val sceneState = requireSceneState(scene)
//        setSceneState(scene, sceneState.copy(audioPath = audioPath))
//    }
//
//    private fun requireShow(): Show {
//        return _uiState.value.currentShow ?: throw IllegalStateException("Show is not selected!")
//    }
//
//    private fun requireScene(): Scene {
//        return _uiState.value.currentScene ?: throw IllegalStateException("Scene is not selected!")
//    }

//    private fun requireSceneState(scene: Scene): SceneState {
//        return _uiState.value.sceneStates[scene]
//            ?: throw IllegalStateException("No state for scene!")
//    }

    companion object {
        //        val Factory: ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                val stageSyncRepository =
//                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as StageSyncApplication).container.showsRepository
//                SceneListViewModel(
//                    scenesRepository = stageSyncRepository
//                )
//            }
//        }
        private const val TIMEOUT_MILLIS = 5_000L
    }

}