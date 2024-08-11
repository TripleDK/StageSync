package com.example.mtapp.data

import android.content.Context
import com.example.mtapp.Models.Scene
import com.example.mtapp.Models.Show
import com.example.mtapp.R
import com.example.mtapp.utils.copyFileFromAssetsToInternalStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DataSource {
    private lateinit var footlooseScenes: List<Scene>
    private lateinit var addamsFamScenes: List<Scene>
    private lateinit var wizardOzScenes: List<Scene>

    lateinit var shows: List<Show>

    suspend fun initialize(context: Context) {
        withContext(Dispatchers.IO) {
            val footlooseScene1 = Scene(
                name = R.string.footloose_scene1,
                type = SceneType.Song,
                startPage = 15,
                endPage = 16
            )
            footlooseScenes = listOf(footlooseScene1)
        }

        val wizardOzScene1 =
            Scene(
                name = R.string.wiz_oz_scene1,
                type = SceneType.Scene,
                startPage = 3,
                endPage = 3
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "wiz_oz_script.pdf",
                    "wiz_oz_script.pdf"
                )
            }
        val wizardOzScene2 =
            Scene(
                name = R.string.wiz_oz_scene2,
                type = SceneType.Song,
                startPage = 3,
                endPage = 4
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "wiz_oz_script.pdf",
                    "wiz_oz_script.pdf"
                )
            }
        val wizardOzScene3 =
            Scene(
                name = R.string.wiz_oz_scene3,
                type = SceneType.Scene,
                startPage = 4,
                endPage = 6
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "wiz_oz_script.pdf",
                    "wiz_oz_script.pdf"
                )
            }
        val wizardOzScene4 =
            Scene(
                name = R.string.wiz_oz_scene4,
                type = SceneType.Song,
                startPage = 6,
                endPage = 7
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "wiz_oz_script.pdf",
                    "wiz_oz_script.pdf"
                )
            }
        wizardOzScenes = listOf(wizardOzScene1, wizardOzScene2, wizardOzScene3, wizardOzScene4)
        addamsFamScenes = listOf<Scene>()

        shows = listOf(
            Show(
                name = R.string.footloose,
                scenes = footlooseScenes
            ),
            Show(
                name = R.string.addams_family,
                scenes = addamsFamScenes
            ),
            Show(
                name = R.string.wizard_of_oz,
                scenes = wizardOzScenes
            )
        )
    }


}