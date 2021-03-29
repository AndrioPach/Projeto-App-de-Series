package alura.com.br.serieapp.fragments

import alura.com.br.serieapp.MyApplication
import alura.com.br.serieapp.R
import alura.com.br.serieapp.ViewModel.SerieViewModel
import alura.com.br.serieapp.ViewModel.SeriesViewModelFactory
import alura.com.br.serieapp.adapter.SeriesAdapter
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout.VERTICAL
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_galeria.*


class FragmentGaleria : Fragment() {

    private val viewModel: SerieViewModel by viewModels{
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

    private fun getSeries() {
        viewModel.getSerie()
        viewModel.mMovie.observe(this, { resposta ->
            if (resposta.isSuccessful) {
                resposta.body()?.let { series ->
                    adapter?.add(series.series)
                }
            } else {
                Log.i("Response", resposta.errorBody().toString())

            }
        })
    }

    private fun configuraRecyclerView() {
        galeria_recyclerView.adapter = adapter
        galeria_recyclerView.layoutManager = LinearLayoutManager(context)
    }
}