package alura.com.br.serieapp.ui.galeria

import alura.com.br.serieapp.MyApplication
import alura.com.br.serieapp.R
import alura.com.br.serieapp.adapter.SeriesAdapter
import alura.com.br.serieapp.ui.detalhes.DetalhesFragment
import alura.com.br.serieapp.models.Series
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_galeria.*


class FragmentGaleria : Fragment() {

    private val viewModel: SerieViewModel by viewModels {
        SeriesViewModelFactory((activity?.application as MyApplication).repository)
    }

    private val adapter by lazy {
        context?.let {
            SeriesAdapter(context = it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSeries()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_galeria,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraRecyclerView()
    }

    private fun configuraRecyclerView() {
        adapter?.onItemClickListener = {
            goToDeteils(it)
        }
        galeria_recyclerView.adapter = adapter
        galeria_recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun getSeries() {
        viewModel.getSerie()
        viewModel.mResponse.observe(this, { resposta ->
            if (resposta.isSuccessful) {
                resposta.body()?.let { series ->
                    adapter?.add(series.series)
                }
            } else {
                Log.i("Response", resposta.errorBody().toString())

            }
        })
    }

    private fun goToDeteils(series: Series) {
        val details = DetalhesFragment(series)
        val fragmentManager = activity?.supportFragmentManager
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment_galeria_container, details)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}