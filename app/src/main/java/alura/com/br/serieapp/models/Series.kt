package alura.com.br.serieapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Series")
data class Series(

    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("vote_average")
    val vote: String,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("first_air_date")
    val release: String
)

