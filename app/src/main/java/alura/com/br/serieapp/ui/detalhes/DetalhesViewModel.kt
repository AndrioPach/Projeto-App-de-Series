package alura.com.br.serieapp.ui.detalhes

import alura.com.br.serieapp.models.Series
import alura.com.br.serieapp.models.SeriesDetalhes
import alura.com.br.serieapp.repository.RepositorySeries
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class DetalhesViewModel(private val repository: RepositorySeries): ViewModel() {

    val mResponse: MutableLiveData<Response<SeriesDetalhes>> = MutableLiveData()

    fun getDetalhes(id: Int){
        viewModelScope.launch {
            val response = repository.getSeriesDetalhes(id)
            mResponse.value = response
        }
    }

    fun saveList(series: Series){
        viewModelScope.launch {
            repository.saveList(series)
        }
    }
}