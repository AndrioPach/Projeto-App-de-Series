package alura.com.br.serieapp.ViewModel

import alura.com.br.serieapp.Services.AppResponse
import alura.com.br.serieapp.repository.RepositorySeries
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class SerieViewModel(private val repository: RepositorySeries): ViewModel() {

    val mMovie: MutableLiveData<Response<AppResponse>> = MutableLiveData()

    fun getMovie(){
        viewModelScope.launch {
            val response = repository.getSeries()
            mMovie.value = response
        }
    }
}