package alura.com.br.serieapp

import alura.com.br.serieapp.repository.RepositorySeries
import android.app.Application

class MyApplication: Application() {


    val repository by lazy { RepositorySeries() }
}
