package alura.com.br.serieapp

import alura.com.br.serieapp.dao.DataBase
import alura.com.br.serieapp.repository.RepositorySeries
import android.app.Application

class MyApplication: Application() {


    val dataBase by lazy { DataBase.getDataBase(this) }
    val repository by lazy { RepositorySeries(dataBase.serieDao()) }
}
