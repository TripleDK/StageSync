package com.example.mtapp.data

import android.content.Context
import com.example.mtapp.Models.AudioObject
import com.example.mtapp.Models.Scene
import com.example.mtapp.Models.Show
import com.example.mtapp.Models.Song
import com.example.mtapp.R
import com.example.mtapp.utils.copyFileFromAssetsToInternalStorage

object DataSource {
    //private lateinit var footlooseScenes: List<Scene>
    private lateinit var addamsFamScenes: List<Scene>
    private lateinit var wizardOzScenes: List<Scene>

    lateinit var shows: List<Show>

    suspend fun initialize(context: Context) {

        val wizardOzScene1 =
            Scene(
                name = R.string.wiz_oz_scene1,
                startPage = 3,
                endPage = 3
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                    "CA_WizardOz_24/wiz_oz_script.pdf"
                )
            }
        val wizardOzScene2 =
            Song(
                name = R.string.wiz_oz_scene2,
                startPage = 3,
                endPage = 4,
                scoreStartPage = 1,
                scoreEndPage = 6
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                    "CA_WizardOz_24/wiz_oz_script.pdf"
                )
                scorePath = copyFileFromAssetsToInternalStorage(
                    context,
                    assetFilePath = "CA_WizardOz_24/5678/5_6_7_8.pdf",
                    outputFilePath = "CA_WizardOz_24/5678/5_6_7_8"
                )
                masterAudio = AudioObject(
                    R.string.backing_track,
                    copyFileFromAssetsToInternalStorage(
                        context,
                        assetFilePath = "CA_WizardOz_24/5678/5678_Backing.mp3",
                        outputFilePath = "CA_WizardOz_24/5678/5678_Backing"
                    )
                )
                tracks = listOf<AudioObject>(
                    AudioObject(
                        R.string.reference_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/5678/5678_Reference_Track_Cut.mp3",
                            outputFilePath = "CA_WizardOz_24/5678/5678_Reference"
                        )
                    ),
                    AudioObject(
                        R.string.note_bash_all_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/5678/5678_All.mp3",
                            outputFilePath = "CA_WizardOz_24/5678/5678_All"
                        )
                    )
                )
            }
        val wizardOzScene3 =
            Scene(
                name = R.string.wiz_oz_scene3,
                startPage = 4,
                endPage = 6
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                    "CA_WizardOz_24/wiz_oz_script.pdf"
                )
            }
        val wizardOzScene4 =
            Song(
                name = R.string.wiz_oz_scene4,
                startPage = 6,
                endPage = 7
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                    "CA_WizardOz_24/wiz_oz_script.pdf"
                )
            }
        wizardOzScenes = listOf(wizardOzScene1, wizardOzScene2, wizardOzScene3, wizardOzScene4)


        val addamsFamScene1 =
            Song(
                name = R.string.addams_fam_scene1,
                startPage = 7,
                endPage = 7
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                    "CA_WizardOz_24/wiz_oz_script.pdf"
                )
            }
        addamsFamScenes = listOf<Scene>(addamsFamScene1)

        shows = listOf(

            Show(
                name = R.string.addams_family,
                scenes = addamsFamScenes
            ),
            Show(
                name = R.string.wizard_of_oz,
                scenes = wizardOzScenes
            )
            //            Show(
//                name = R.string.footloose,
//                scenes = footlooseScenes
//            ),
        )
    }
//    withContext(Dispatchers.IO) {
//        val footlooseScene1 = Song(
//            name = R.string.footloose_scene1,
//            startPage = 15,
//            endPage = 16
//        )
//        footlooseScenes = listOf(footlooseScene1)
//    }

}