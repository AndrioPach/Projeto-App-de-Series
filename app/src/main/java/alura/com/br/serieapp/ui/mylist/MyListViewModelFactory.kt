package alura.com.br.serieapp.ui.mylist

import alura.com.br.serieapp.repository.RepositorySeries
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyListViewModelFactory(private val repository: RepositorySeries):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyListViewModel(repository) as T
    }
}