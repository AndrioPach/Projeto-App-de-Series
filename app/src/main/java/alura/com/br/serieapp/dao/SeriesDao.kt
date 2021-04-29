package alura.com.br.serieapp.dao

import alura.com.br.serieapp.models.Series
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import kotlinx.coroutines.flow.Flow


@Dao
interface SeriesDao {
    @Insert(onConflict = REPLACE)
    suspend fun saveList(series: Series)

    @Delete
    suspend fun deleteList(series: Series)

    @Query("SELECT * FROM Series WHERE id")
    fun findSeriesList(): Flow<List<Series>>
}