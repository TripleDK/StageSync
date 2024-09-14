package com.example.mtapp.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "Scenes")
data class Scene(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val showId: Int = 0,
    val name: String = "",
    val actNumber: Int = 1,
    val sceneNumber: Int = 1,
    val isSong: Boolean = false,
    val scriptPath: String? = null,
    val startPage: Int? = 0,
    val endPage: Int? = 0,
)

@Dao
interface SceneDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(scene: Scene)

    @Update
    suspend fun update(scene: Scene)

    @Delete
    suspend fun delete(scene: Scene)

    @Query("SELECT name, id, showId, actNumber, sceneNumber, isSong FROM scenes WHERE showId = :showId ORDER BY actNumber ASC, sceneNumber ASC")
    fun getScenesForShow(showId: Int): Flow<List<Scene>>

    @Query("SELECT * FROM scenes WHERE id = :id")
    fun getScene(id: Int): Flow<Scene>
}

@Database(entities = [Scene::class], version = 1, exportSchema = false)
abstract class SceneDatabase : RoomDatabase() {
    abstract fun sceneDao(): SceneDao

    companion object {
        @Volatile
        private var Instance: SceneDatabase? = null

        fun getDatabase(context: Context): SceneDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, SceneDatabase::class.java, "scene_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}