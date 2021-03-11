package alura.com.br.serieapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AppResponse(
    @SerializedName("results")
    val series : List<Series>

) : Parcelable {
    constructor() : this(mutableListOf())
}
