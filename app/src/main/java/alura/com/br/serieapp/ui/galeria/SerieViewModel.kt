package alura.com.br.serieapp.ui.galeria

import alura.com.br.serieapp.Services.AppResponse
import alura.com.br.serieapp.models.Series
import alura.com.br.serieapp.repository.RepositorySeries
import alura.com.br.serieapp.ui.Resource
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import retrofit2.Response

class SerieViewModel(private val repository: RepositorySeries): ViewModel() {

    val mResponse: MutableLiveData<Response<AppResponse>> = MutableLiveData()
    val fPesquisaResponse: MutableLiveData<Resource<AppResponse>> = MutableLiveData()
    var fPesquisaResponseNew:AppResponse? = null

    fun getSerie(){
        viewModelScope.launch {
            val response = repository.getSeries()
            mResponse.value = response
        }
    }

    fun saveList(series: Series) {
        viewModelScope.launch {
            repository.saveList(series)
        }
    }

    fun getSearchSeries(keyword: String) = viewModelScope.launch {
        searchSeries(keyword)
    }

    private suspend fun searchSeries(searchQuery: String) {
        val response = repository.getSearchSeries(searchQuery)
        fPesquisaResponse.postValue(searchSerieResponse(response))
    }


    // Obtem os filmes através da pesquisa
    private fun searchSerieResponse(response: Response<AppResponse>): Resource<AppResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if (fPesquisaResponseNew == null) {
                    fPesquisaResponseNew = resultResponse
                }
                return Resource.Success(fPesquisaResponseNew ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}