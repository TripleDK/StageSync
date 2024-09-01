package com.example.mtapp.data

import android.content.Context
import com.example.mtapp.Models.AudioObject
import com.example.mtapp.Models.Scene
import com.example.mtapp.Models.Show
import com.example.mtapp.Models.ShowCharacter
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
                actNumber = 1,
                sceneNumber = 1,
                startPage = 3,
                endPage = 3
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf"
                )
            }
        val wizardOzScene2 =
            Song(
                name = R.string.wiz_oz_scene2,
                actNumber = 1,
                sceneNumber = 1,
                startPage = 3,
                endPage = 4,
                scoreStartPage = 1,
                scoreEndPage = 6
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf"
                )
                scorePath = copyFileFromAssetsToInternalStorage(
                    context,
                    assetFilePath = "CA_WizardOz_24/5678/5_6_7_8.pdf",
                )
                masterAudio = AudioObject(
                    R.string.backing_track,
                    copyFileFromAssetsToInternalStorage(
                        context,
                        assetFilePath = "CA_WizardOz_24/5678/5678_Backing.mp3",
                    )
                )
                tracks = listOf<AudioObject>(
                    AudioObject(
                        R.string.reference_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/5678/5678_Reference_Track_Cut.mp3",
                        )
                    ),
                    AudioObject(
                        R.string.note_bash_all_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/5678/5678_All.mp3",
                        )
                    )
                )
            }
        val wizardOzScene3 =
            Scene(
                name = R.string.wiz_oz_scene3,
                actNumber = 1,
                sceneNumber = 1,
                startPage = 4,
                endPage = 5
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                )
            }
        val wizardOzScene4 =
            Song(
                name = R.string.wiz_oz_scene4,
                actNumber = 1,
                sceneNumber = 1,
                startPage = 5,
                endPage = 6
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                )
            }
        val wizardOzScene5 =
            Song(
                name = R.string.wiz_oz_scene5,
                actNumber = 1,
                sceneNumber = 1,
                startPage = 7,
                endPage = 7,
                scoreStartPage = 1,
                scoreEndPage = 5
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                )
                scorePath = copyFileFromAssetsToInternalStorage(
                    context,
                    assetFilePath = "CA_WizardOz_24/MerryMunchkinSong/MerryMunchkinSong.pdf",
                )
                masterAudio = AudioObject(
                    R.string.backing_track,
                    copyFileFromAssetsToInternalStorage(
                        context,
                        assetFilePath = "CA_WizardOz_24/MerryMunchkinSong/Backing_Track.mp3",
                    )
                )
                tracks = listOf<AudioObject>(
                    AudioObject(
                        R.string.reference_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/MerryMunchkinSong/Reference_Track.mp3",
                        )
                    ),
                    AudioObject(
                        R.string.note_bash_alto_bass_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/MerryMunchkinSong/Alto_Bass.mp3",
                        )
                    ),
                    AudioObject(
                        R.string.munch1_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/MerryMunchkinSong/Munch 1.mp3",
                        )
                    ),
                    AudioObject(
                        R.string.munch2_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/MerryMunchkinSong/Munch 2.mp3",
                        )
                    ),
                    AudioObject(
                        R.string.munch3_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/MerryMunchkinSong/Munch 3.mp3",
                        )
                    ),
                    AudioObject(
                        R.string.munch4_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/MerryMunchkinSong/Munch 4.mp3",
                        )
                    ),
                    AudioObject(
                        R.string.note_bash_sop1_tenor_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/MerryMunchkinSong/Sop 1 Tenor.mp3",
                        )
                    ),
                    AudioObject(
                        R.string.note_bash_sop2_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/MerryMunchkinSong/Sop 2.mp3",
                        )
                    )
                )
            }
        val wizardOzScene6 =
            Scene(
                name = R.string.wiz_oz_scene6,
                actNumber = 1,
                sceneNumber = 2,
                startPage = 7,
                endPage = 8
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                )
            }
        val wizardOzScene7 =
            Song(
                name = R.string.wiz_oz_scene7,
                actNumber = 1,
                sceneNumber = 2,
                startPage = 8,
                endPage = 8
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                )
            }
        val wizardOzScene9 =
            Scene(
                name = R.string.wiz_oz_scene9,
                actNumber = 1,
                sceneNumber = 2,
                startPage = 8,
                endPage = 14
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                )
            }
        val wizardOzScene10 =
            Song(
                name = R.string.wiz_oz_scene10,
                actNumber = 1,
                sceneNumber = 2,
                startPage = 14,
                endPage = 14
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                )
            }
        val wizardOzScene11 =
            Scene(
                name = R.string.wiz_oz_scene11,
                actNumber = 1,
                sceneNumber = 2,
                startPage = 14,
                endPage = 16
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                )
            }
        val wizardOzScene12 =
            Scene(
                name = R.string.wiz_oz_scene12,
                actNumber = 1,
                sceneNumber = 3,
                startPage = 16,
                endPage = 19
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                )
            }
        val wizardOzScene13 =
            Song(
                name = R.string.wiz_oz_scene13,
                actNumber = 1,
                sceneNumber = 3,
                startPage = 19,
                endPage = 19
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                )
            }
        val wizardOzScene14 =
            Scene(
                name = R.string.wiz_oz_scene14,
                actNumber = 1,
                sceneNumber = 4,
                startPage = 20,
                endPage = 22
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                )
            }
        val wizardOzScene15 =
            Scene(
                name = R.string.wiz_oz_scene15,
                actNumber = 1,
                sceneNumber = 5,
                startPage = 22,
                endPage = 25
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                )
            }
        val wizardOzScene16 =
            Song(
                name = R.string.wiz_oz_scene16,
                actNumber = 1,
                sceneNumber = 5,
                startPage = 25,
                endPage = 26
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                )
            }
        val wizardOzScene17 =
            Song(
                name = R.string.wiz_oz_scene17,
                actNumber = 1,
                sceneNumber = 6,
                startPage = 26,
                endPage = 27,
                scoreStartPage = 2,
                scoreEndPage = 8
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                )
                scorePath = copyFileFromAssetsToInternalStorage(
                    context,
                    assetFilePath = "CA_WizardOz_24/ItsAHardKnockLife/Hard knock life.pdf",
                )
                masterAudio = AudioObject(
                    R.string.backing_track,
                    copyFileFromAssetsToInternalStorage(
                        context,
                        assetFilePath = "CA_WizardOz_24/ItsAHardKnockLife/Backing Track.mp3",
                    )
                )
                tracks = listOf<AudioObject>(
                    AudioObject(
                        R.string.reference_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/ItsAHardKnockLife/Reference Track.mp3",
                        )
                    ),
                    AudioObject(
                        R.string.note_bash_alto_bass_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/ItsAHardKnockLife/Alto_Bass.mp3",
                        )
                    ),
                    AudioObject(
                        R.string.note_bash_sop_tenor_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/ItsAHardKnockLife/Sop_Tenor.mp3",
                        )
                    )
                )
            }
        val wizardOzScene18 =
            Scene(
                name = R.string.wiz_oz_scene18,
                actNumber = 1,
                sceneNumber = 6,
                startPage = 27,
                endPage = 29
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                )
            }
        val wizardOzScene19 =
            Song(
                name = R.string.wiz_oz_scene19,
                actNumber = 1,
                sceneNumber = 6,
                startPage = 29,
                endPage = 30
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                )
            }
        val wizardOzScene20 =
            Scene(
                name = R.string.wiz_oz_scene20,
                actNumber = 1,
                sceneNumber = 7,
                startPage = 30,
                endPage = 33
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                )
            }
        val wizardOzScene21 =
            Scene(
                name = R.string.wiz_oz_scene21,
                actNumber = 1,
                sceneNumber = 8,
                startPage = 33,
                endPage = 33
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                )
            }
        val wizardOzScene22 =
            Song(
                name = R.string.wiz_oz_scene22,
                actNumber = 1,
                sceneNumber = 8,
                startPage = 33,
                endPage = 35,
                scoreStartPage = 1,
                scoreEndPage = 10
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                )
                scorePath = copyFileFromAssetsToInternalStorage(
                    context,
                    assetFilePath = "CA_WizardOz_24/BackstageRomance/Backstage Romance Oz.pdf",
                )
                masterAudio = AudioObject(
                    R.string.backing_track,
                    copyFileFromAssetsToInternalStorage(
                        context,
                        assetFilePath = "CA_WizardOz_24/BackstageRomance/Backing Track.mp3",
                    )
                )
                tracks = listOf<AudioObject>(
                    AudioObject(
                        R.string.reference_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/BackstageRomance/Reference Track.mp3",
                        )
                    ),
                    AudioObject(
                        R.string.note_bash_alto_bass_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/BackstageRomance/Alto_Bass.mp3",
                        )
                    ),
                    AudioObject(
                        R.string.dame_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/BackstageRomance/Dame.mp3",
                        )
                    ),
                    AudioObject(
                        R.string.dorothy_toto_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/BackstageRomance/Dorothy_Toto.mp3",
                        )
                    ),
                    AudioObject(
                        R.string.witch_crows_high_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/BackstageRomance/Glinda_Witch_Crows High.mp3",
                        )
                    ),
                    AudioObject(
                        R.string.witch_crows_middle_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/BackstageRomance/Glinda_Witch_Crows Middle.mp3",
                        )
                    ),
                    AudioObject(
                        R.string.witch_crows_low_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/BackstageRomance/Glinda_Witch_Crows Low.mp3",
                        )
                    ),
                    AudioObject(
                        R.string.lion_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/BackstageRomance/Lion.mp3",
                        )
                    ),
                    AudioObject(
                        R.string.scarecrow_tinman_high,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/BackstageRomance/Scarecrow_Tinman High.mp3",
                        )
                    ),
                    AudioObject(
                        R.string.scarecrow_tinman_low,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/BackstageRomance/Scarecrow_Tinman Low.mp3",
                        )
                    ),
                    AudioObject(
                        R.string.note_bash_sop1_tenor_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/BackstageRomance/Sop 1_Tenor.mp3",
                        )
                    ),
                    AudioObject(
                        R.string.note_bash_sop2_track,
                        copyFileFromAssetsToInternalStorage(
                            context,
                            assetFilePath = "CA_WizardOz_24/BackstageRomance/Sop 2.mp3",
                        )
                    )
                )
            }
        val wizardOzScene23 =
            Scene(
                name = R.string.wiz_oz_scene23,
                actNumber = 1,
                sceneNumber = 8,
                startPage = 36,
                endPage = 37
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                )
            }

        val wizardOzScene24 =
            Song(
                name = R.string.wiz_oz_scene24,
                actNumber = 2,
                sceneNumber = 1,
                startPage = 37,
                endPage = 38
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                )
            }
        val wizardOzScene25 =
            Scene(
                name = R.string.wiz_oz_scene25,
                actNumber = 2,
                sceneNumber = 1,
                startPage = 37,
                endPage = 39
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                )
            }
        val wizardOzScene26 =
            Scene(
                name = R.string.wiz_oz_scene26,
                actNumber = 2,
                sceneNumber = 1,
                startPage = 39,
                endPage = 41
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/wiz_oz_script.pdf",
                )
            }

        wizardOzScenes = listOf(
            wizardOzScene1,
            wizardOzScene2,
            wizardOzScene3,
            wizardOzScene4,
            wizardOzScene5,
            wizardOzScene6,
            wizardOzScene7,
            wizardOzScene9,
            wizardOzScene10,
            wizardOzScene11,
            wizardOzScene12,
            wizardOzScene13,
            wizardOzScene14,
            wizardOzScene15,
            wizardOzScene16,
            wizardOzScene17,
            wizardOzScene18,
            wizardOzScene19,
            wizardOzScene20,
            wizardOzScene21,
            wizardOzScene22,
            wizardOzScene23,
            wizardOzScene24,
            wizardOzScene25,
            wizardOzScene26
        )


        val addamsFamScene1 =
            Song(
                name = R.string.addams_fam_scene1,
                actNumber = 1,
                sceneNumber = 1,
                startPage = 7,
                endPage = 7
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_AddamsFamily_24/scripts_score.pdf",
                )
            }
        val addamsFamScene2 =
            Song(
                name = R.string.addams_fam_scene2,
                actNumber = 1,
                sceneNumber = 1,
                startPage = 7,
                endPage = 7
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_AddamsFamily_24/scripts_score.pdf",
                )
            }
        val addamsFamScene3 =
            Scene(
                name = R.string.addams_fam_scene3,
                actNumber = 1,
                sceneNumber = 1,
                startPage = 7,
                endPage = 7
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_AddamsFamily_24/scripts_score.pdf",
                )
            }
        val addamsFamScene4 =
            Song(
                name = R.string.addams_fam_scene4,
                actNumber = 1,
                sceneNumber = 1,
                startPage = 7,
                endPage = 12
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_AddamsFamily_24/scripts_score.pdf",
                )
            }
        val addamsFamScene5 =
            Scene(
                name = R.string.addams_fam_scene5,
                actNumber = 1,
                sceneNumber = 1,
                startPage = 12,
                endPage = 13
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_AddamsFamily_24/scripts_score.pdf",
                )
            }
        val addamsFamScene6 =
            Song(
                name = R.string.addams_fam_scene6,
                actNumber = 1,
                sceneNumber = 1,
                startPage = 13,
                endPage = 13
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_AddamsFamily_24/scripts_score.pdf",
                )
            }
        val addamsFamScene7 =
            Scene(
                name = R.string.addams_fam_scene7,
                actNumber = 1,
                sceneNumber = 2,
                startPage = 14,
                endPage = 18
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_AddamsFamily_24/scripts_score.pdf",
                )
            }
        val addamsFamScene8 =
            Song(
                name = R.string.addams_fam_scene8,
                actNumber = 1,
                sceneNumber = 2,
                startPage = 18,
                endPage = 18
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_AddamsFamily_24/scripts_score.pdf",
                )
            }
        val addamsFamScene9 =
            Scene(
                name = R.string.addams_fam_scene9,
                actNumber = 1,
                sceneNumber = 2,
                startPage = 18,
                endPage = 18
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_AddamsFamily_24/scripts_score.pdf",
                )
            }
        val addamsFamScene10 =
            Song(
                name = R.string.addams_fam_scene10,
                actNumber = 1,
                sceneNumber = 2,
                startPage = 18,
                endPage = 19
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_AddamsFamily_24/scripts_score.pdf",
                )
            }
        val addamsFamScene11 =
            Scene(
                name = R.string.addams_fam_scene11,
                actNumber = 1,
                sceneNumber = 2,
                startPage = 19,
                endPage = 20
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_AddamsFamily_24/scripts_score.pdf",
                )
            }
        val addamsFamScene12 =
            Song(
                name = R.string.addams_fam_scene12,
                actNumber = 1,
                sceneNumber = 2,
                startPage = 20,
                endPage = 22
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_AddamsFamily_24/scripts_score.pdf",
                )
            }
        val addamsFamScene13 =
            Song(
                name = R.string.addams_fam_scene13,
                actNumber = 1,
                sceneNumber = 2,
                startPage = 23,
                endPage = 23
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_AddamsFamily_24/scripts_score.pdf",
                )
            }
        val addamsFamScene14 =
            Scene(
                name = R.string.addams_fam_scene14,
                actNumber = 1,
                sceneNumber = 3,
                startPage = 23,
                endPage = 23
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_AddamsFamily_24/scripts_score.pdf",
                )
            }
        val addamsFamScene15 =
            Song(
                name = R.string.addams_fam_scene15,
                actNumber = 1,
                sceneNumber = 3,
                startPage = 23,
                endPage = 26
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_AddamsFamily_24/scripts_score.pdf",
                )
            }
        val addamsFamScene16 =
            Scene(
                name = R.string.addams_fam_scene16,
                actNumber = 1,
                sceneNumber = 3,
                startPage = 26,
                endPage = 30
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_AddamsFamily_24/scripts_score.pdf",
                )
            }
        val addamsFamScene17 =
            Song(
                name = R.string.addams_fam_scene17,
                actNumber = 1,
                sceneNumber = 3,
                startPage = 30,
                endPage = 38
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_AddamsFamily_24/scripts_score.pdf",
                )
            }
        val addamsFamScene18 =
            Scene(
                name = R.string.addams_fam_scene18,
                actNumber = 1,
                sceneNumber = 5,
                startPage = 39,
                endPage = 48
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_AddamsFamily_24/scripts_score.pdf",
                )
            }
        val addamsFamScene19 =
            Song(
                name = R.string.addams_fam_scene19,
                actNumber = 1,
                sceneNumber = 5,
                startPage = 48,
                endPage = 48
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_AddamsFamily_24/scripts_score.pdf",
                )
            }
        val addamsFamScene20 =
            Scene(
                name = R.string.addams_fam_scene20,
                actNumber = 1,
                sceneNumber = 6,
                startPage = 49,
                endPage = 50
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_AddamsFamily_24/scripts_score.pdf",
                )
            }
        val addamsFamScene21 =
            Song(
                name = R.string.addams_fam_scene21,
                actNumber = 1,
                sceneNumber = 6,
                startPage = 50,
                endPage = 50
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_AddamsFamily_24/scripts_score.pdf",
                )
            }
        val addamsFamScene22 =
            Scene(
                name = R.string.addams_fam_scene22,
                actNumber = 1,
                sceneNumber = 7,
                startPage = 51,
                endPage = 53
            ).apply {
                scriptPath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_AddamsFamily_24/scripts_score.pdf",
                )
            }

        addamsFamScenes = listOf<Scene>(
            addamsFamScene1,
            addamsFamScene2,
            addamsFamScene3,
            addamsFamScene4,
            addamsFamScene5,
            addamsFamScene6,
            addamsFamScene7,
            addamsFamScene8,
            addamsFamScene9,
            addamsFamScene10,
            addamsFamScene11,
            addamsFamScene12,
            addamsFamScene13,
            addamsFamScene14,
            addamsFamScene15,
            addamsFamScene16,
            addamsFamScene17,
            addamsFamScene18,
            addamsFamScene19,
            addamsFamScene20,
            addamsFamScene21,
            addamsFamScene22
        )

        shows = listOf(
            Show(
                name = R.string.addams_family,
                scenes = addamsFamScenes,

                ).apply {
                schedulePath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_AddamsFamily_24/ADDAMS FAMILY SCHEDULE.pdf",
                )
            },
            Show(
                name = R.string.wizard_of_oz,
                scenes = wizardOzScenes,
                characters = listOf(
                    ShowCharacter(
                        name = "Ike"
                    ),
                    ShowCharacter(
                        name = "Jan"
                    ),
                    ShowCharacter(
                        name = "Dorothy"
                    ),
                    ShowCharacter(
                        name = "Toto"
                    ),
                    ShowCharacter(
                        name = "Scarecrow"
                    ),
                    ShowCharacter(
                        name = "Dame"
                    )
                )
            ).apply {
                schedulePath = copyFileFromAssetsToInternalStorage(
                    context,
                    "CA_WizardOz_24/WOZ SCHEDULE.pdf",
                )
            }
        )
    }


}