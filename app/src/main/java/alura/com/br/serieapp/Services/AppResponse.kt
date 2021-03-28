package alura.com.br.serieapp.Services

import alura.com.br.serieapp.models.Series
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AppResponse(
    @SerializedName("results")
    val series : List<Series>,

) : Parcelable {
    constructor(): this(mutableListOf())
}
