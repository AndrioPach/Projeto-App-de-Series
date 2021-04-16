package alura.com.br.serieapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "SerieDetalhes")
data class SeriesDetalhes(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("name") val name: String,
    @SerializedName("number_of_episodes") val episodeos: String,
    @SerializedName("number_of_seasons") val temporadas: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterDetalhes: String,
    @SerializedName("first_air_date") val release: String,
    @SerializedName("vote_average") val vote: Float,
    @SerializedName("id") val id: Int
)