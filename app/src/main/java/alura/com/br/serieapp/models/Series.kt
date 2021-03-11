package alura.com.br.serieapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Series(
    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("vote_average")
    val vote: String?,

    @SerializedName("poster_path")
    val poster: String?,

    @SerializedName("first_air_date")
    val release: String?


) : Parcelable {
    constructor() : this("", "", "", "","")
}
