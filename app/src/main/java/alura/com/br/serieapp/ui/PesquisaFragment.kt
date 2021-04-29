package alura.com.br.serieapp.ui

import alura.com.br.serieapp.MyApplication
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import alura.com.br.serieapp.R
import alura.com.br.serieapp.adapter.PesquisaAdapter
import alura.com.br.serieapp.models.Series
import alura.com.br.serieapp.ui.detalhes.DetalhesFragment
import alura.com.br.serieapp.ui.galeria.SerieViewModel
import alura.com.br.serieapp.ui.galeria.SeriesViewModelFactory
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_pesquisa.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val PESQUISA_DELAY = 500L
class PesquisaFragment : Fragment() {


    private val viewModel: SerieViewModel by viewModels {
        SeriesViewModelFactory((activity?.application as MyApplication).repository)
    }

    private val adapter by lazy {
        context?.let {
            PesquisaAdapter(context = it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_pesquisa,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configAdapterRecyclerView()

        var job: Job? = null
        pesquisa_filme.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(PESQUISA_DELAY)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.getSearchSeries(editable.toString())
                    }
                }
            }
        }

        viewModel.fPesquisaResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success<*> -> {
                    hideProgressBar()
                    response.data?.let { series ->
                        series.series.let { it1 -> adapter?.append(it1) }
                    }
                }
                is Resource.Error<*> -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(
                            activity, "Erro!: $message", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }


    private fun hideProgressBar() {
        barra_progresso.visibility = View.INVISIBLE
    }
    private fun configAdapterRecyclerView() {
        adapter?.onItemClickListener = {
            goToMovieDetails(it)
        }
        rv_pesquisa.adapter = adapter
        rv_pesquisa.layoutManager = GridLayoutManager(context, 3)
    }

    private fun goToMovieDetails(series: Series) {
        val details = DetalhesFragment(series)
        val fragmentManager = activity?.supportFragmentManager
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment_pesquisa_container, details)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

}