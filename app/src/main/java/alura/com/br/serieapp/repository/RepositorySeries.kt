package alura.com.br.serieapp.repository

import alura.com.br.serieapp.Services.API
import alura.com.br.serieapp.Services.ApiService
import alura.com.br.serieapp.Services.AppResponse
import retrofit2.Response


class RepositorySeries (
    private val api: API = ApiService().seriesService
){
    suspend fun getSeries(): Response<AppResponse>{
        return api.getList()
    }



}