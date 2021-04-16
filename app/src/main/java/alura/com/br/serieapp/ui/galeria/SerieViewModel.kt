package alura.com.br.serieapp.ui.galeria

import alura.com.br.serieapp.Services.AppResponse
import alura.com.br.serieapp.models.Series
import alura.com.br.serieapp.repository.RepositorySeries
import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response

class SerieViewModel(private val repository: RepositorySeries): ViewModel() {

    val mResponse: MutableLiveData<Response<AppResponse>> = MutableLiveData()


    fun getSerie(){
        viewModelScope.launch {
            val response = repository.getSeries()
            mResponse.value = response
        }
    }

}