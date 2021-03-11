package alura.com.br.serieapp.Services

import alura.com.br.serieapp.models.AppResponse
import retrofit2.Call
import retrofit2.http.GET

interface API {
    @GET("/3/tv/popular?api_key=4cc2822d765baa01bddd3620618e1be1")
    fun getList(): Call<AppResponse>
}