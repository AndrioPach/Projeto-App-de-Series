package alura.com.br.serieapp.ui.detalhes

import alura.com.br.serieapp.repository.RepositorySeries
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetalhesViewModelFactory(
    private val repository: RepositorySeries):ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DetalhesViewModel(repository) as T
        }
}