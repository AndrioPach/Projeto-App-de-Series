package alura.com.br.serieapp.ui.detalhes

import alura.com.br.serieapp.MyApplication
import alura.com.br.serieapp.R
import alura.com.br.serieapp.models.Series
import alura.com.br.serieapp.models.SeriesDetalhes
import alura.com.br.serieapp.ui.RatingStars
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import kotlinx.android.synthetic.main.fragment_detalhes.*
import retrofit2.Response


class DetalhesFragment(
    private val series: Series) : Fragment() {

    private val viewModel: DetalhesViewModel by viewModels {
        DetalhesViewModelFactory((activity?.application as MyApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_detalhes,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraDetalhes()

    }

    private fun configuraDetalhes() {
        viewModel.getDetalhes(series.id)
        viewModel.mResponse.observe(viewLifecycleOwner, {
            if (it.isSuccessful) {
                nome_serie_detalhes.text = it.body()?.name
                numero_episodeos_detalhes.text = it.body()?.episodeos.toString()
                numero_temporadas_detalhes.text = it.body()?.temporadas.toString()
                overview_detalhes.text = it.body()?.overview
                lancamento_detalhes.text = it.body()?.release
                configuraScore(it)
                configuraPoster(it)
            }
        })
    }

    private fun configuraScore(seriesDetalhes: Response<SeriesDetalhes>) {
        val rating = seriesDetalhes.body()!!.vote
        RatingStars(ratingBar_detalhes, rating)
    }

    private fun configuraPoster(series: Response<SeriesDetalhes>) {
        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500"+series.body()?.posterDetalhes)
            .transform(CenterCrop())
            .into(serie_detalhes_poster)
    }
}