package com.example.mtapp.data

import android.content.Context
import android.os.Parcelable
import androidx.annotation.StringRes
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
import androidx.room.TypeConverters
import androidx.room.Update
import com.example.mtapp.Models.Group
import com.example.mtapp.Models.ShowCharacter
import com.example.mtapp.data.local.exercises.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Shows")
data class Show(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    var schedulePath: String? = null,
//    @TypeConverters(Converters::class)
//    val scenes: List<Scene>,
//    @TypeConverters(Converters::class)
//    val characters: List<ShowCharacter> = emptyList(),
//    @TypeConverters(Converters::class)
//    var groups: List<Group> = emptyList()
)

@Dao
interface ShowDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(show: Show)

    @Update
    suspend fun update(show: Show)

    @Delete
    suspend fun delete(show: Show)

    @Query("SELECT name, id FROM shows ORDER BY name ASC")
    fun getAllShows(): Flow<List<Show>>

    @Query("SELECT * FROM shows WHERE id = :id")
    fun getShow(id: Int): Flow<Show>
}

@Database(entities = [Show::class], version = 1, exportSchema = false)
abstract class ShowDatabase : RoomDatabase() {
    abstract fun showDao(): ShowDao

    companion object {
        @Volatile
        private var Instance: ShowDatabase? = null

        fun getDatabase(context: Context): ShowDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ShowDatabase::class.java, "show_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}