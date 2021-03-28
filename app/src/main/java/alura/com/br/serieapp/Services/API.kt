package alura.com.br.serieapp.Services

import retrofit2.Response
import retrofit2.http.GET

interface API {
    @GET("/3/tv/popular?api_key=4cc2822d765baa01bddd3620618e1be1")
    fun getList(): Response<AppResponse>

    @GET("/tv/{tv_id}?api_key=4cc2822d765baa01bddd3620618e1be1")
    fun getDetails(): Response<AppResponse>
}