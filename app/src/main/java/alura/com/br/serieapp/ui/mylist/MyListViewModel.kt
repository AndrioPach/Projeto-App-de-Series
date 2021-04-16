package alura.com.br.serieapp.ui.mylist

import alura.com.br.serieapp.models.Series
import alura.com.br.serieapp.repository.RepositorySeries
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyListViewModel(private val repository: RepositorySeries):ViewModel(){

    val seriesListDB: LiveData<List<Series>> = repository.seriesListDB.asLiveData()

    fun saveList(series: Series) {
        viewModelScope.launch {
            repository.saveList(series)
        }
    }
    fun deleteList(series: Series){
        viewModelScope.launch {
            repository.deleteList(series)
        }
    }



}