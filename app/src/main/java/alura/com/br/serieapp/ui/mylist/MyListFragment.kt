package alura.com.br.serieapp.ui.mylist

import alura.com.br.serieapp.MyApplication
import alura.com.br.serieapp.R
import alura.com.br.serieapp.adapter.MyListAdapter
import alura.com.br.serieapp.models.Series
import alura.com.br.serieapp.ui.detalhes.DetalhesFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
//import kotlinx.android.synthetic.main.fragment_galeria.*
import kotlinx.android.synthetic.main.fragment_my_list.*

class MyListFragment : Fragment() {

    private val viewModel: MyListViewModel by viewModels {
        MyListViewModelFactory((activity?.application as MyApplication).repository)
    }

    private val adapter by lazy {
        context?.let {
            MyListAdapter(context = it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSeriesLista()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_my_list,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configSeries()
    }


    private fun configSeries() {
        adapter?.onItemClickListener = {
            goToDeteils(it)
        }
        MyList_recyclerview.adapter = adapter
        MyList_recyclerview.layoutManager = LinearLayoutManager(context)
        adapter?.onDeleta = {
            deletaListInterna(it)
            Toast.makeText(context,"Deletado com Sucesso",Toast.LENGTH_LONG).show()}
    }

    private fun getSeriesLista() {
        viewModel.seriesListDB.observe(this, {
            it?.let {
                it.sortedBy { it.id }
                adapter?.add(it)
            }
        })
    }

    private fun goToDeteils(series: Series) {
        val details = DetalhesFragment(series)
        val fragmentManager = activity?.supportFragmentManager
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment_mylist_container, details)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    private fun deletaListInterna(series: Series) {
        viewModel.deleteList(series)
        adapter?.deletaSerie(series)
    }

}