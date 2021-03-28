package alura.com.br.serieapp.repository

import alura.com.br.serieapp.Services.API
import alura.com.br.serieapp.Services.ApiService
import alura.com.br.serieapp.Services.AppResponse
import alura.com.br.serieapp.dao.SeriesDao
import retrofit2.Call
import retrofit2.Response


class RepositorySeries (
    private val dao: SeriesDao,
    private val api: API = ApiService().seriesService
){
    suspend fun getSeries(): Response<AppResponse>{
        return api.getList()
    }



}