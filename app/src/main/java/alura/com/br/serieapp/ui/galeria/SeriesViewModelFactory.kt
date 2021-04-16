package alura.com.br.serieapp.ViewModel

import alura.com.br.serieapp.repository.RepositorySeries
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SeriesViewModelFactory(
    private val repositorySeries: RepositorySeries): ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SerieViewModel(repositorySeries) as T
    }

}