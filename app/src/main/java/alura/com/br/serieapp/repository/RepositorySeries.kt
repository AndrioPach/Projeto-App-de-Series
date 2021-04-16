package alura.com.br.serieapp.repository

import alura.com.br.serieapp.Services.API
import alura.com.br.serieapp.Services.ApiService
import alura.com.br.serieapp.Services.AppResponse
import alura.com.br.serieapp.dao.SeriesDao
import alura.com.br.serieapp.models.Series
import alura.com.br.serieapp.models.SeriesDetalhes
import androidx.room.Dao
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


class RepositorySeries (
    private val dao: SeriesDao,
    private val api: API = ApiService().seriesService
){

    val seriesListDB: Flow<List<Series>> = dao.findSeriesList()

    suspend fun getSeries(): Response<AppResponse>{
        return api.getList()
    }
    suspend fun getSeriesDetalhes(id: Int):Response<SeriesDetalhes>{
        return api.getDetalhes(id)
    }

    suspend fun deleteList(series: Series) {
        dao.deleteList(series)
    }

    suspend fun saveList(series: Series) {
        series.id
        dao.saveList(series)
    }
    suspend fun getSearchSeries(keyword: String): Response<AppResponse> {
        return api.getSearchSerie(keyword)
    }

}