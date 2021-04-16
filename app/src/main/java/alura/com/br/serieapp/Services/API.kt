package alura.com.br.serieapp.Services

import alura.com.br.serieapp.models.Series
import alura.com.br.serieapp.models.SeriesDetalhes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface API {
    @GET("tv/popular")
    suspend fun getList(): Response<AppResponse>

    @GET("tv/{tv_id}")
    suspend fun getDetalhes(@Path("tv_id")id:Int): Response<SeriesDetalhes>
}