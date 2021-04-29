package alura.com.br.serieapp.adapter


import alura.com.br.serieapp.R
import alura.com.br.serieapp.models.Series
import alura.com.br.serieapp.ui.RatingStars
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.lista_layout.view.*

class SeriesAdapter(private val context: Context,
                    private val serie: MutableList<Series> = mutableListOf(),
                    var onItemClickListener: (series: Series) -> Unit = {},
                    var onSave: (series: Series) -> Unit = {}
):RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {
    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

    fun add(series: List<Series>) {
        this.serie.clear()
        this.serie.addAll(series)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.lista_layout, parent, false)
        return SeriesViewHolder(view)
    }


    override fun getItemCount(): Int = serie.size

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.bind(serie[position])
    }

    inner class SeriesViewHolder(Itemview: View) : RecyclerView.ViewHolder(Itemview) {
        private lateinit var serie: Series
        private val campoTitulo by lazy { itemView.nome_serie}
        private val campoData by lazy { itemView.data_lancamento }
        private val campoVote by lazy {itemView.vote_average}
        private val campoRatingBar by lazy {itemView.rating_bar}
        private val favbtn by lazy {itemView.favBtn}

        init {
            itemView.setOnClickListener {
                if(::serie.isInitialized){
                    onItemClickListener(serie)
                }
            }
        }

        init {
            favbtn.setOnClickListener() {
                if (::serie.isInitialized) {
                    onSave(serie)
                }
            }
        }

        fun bind(series: Series) {
            this.serie = series
            campoTitulo.text = series.name
            campoData.text = series.release
            campoVote.text = series.vote.toString()
            RatingStars(campoRatingBar, series.vote)
            Glide.with(itemView).load(IMAGE_BASE + series.poster).into(itemView.serie_poster)
        }
    }
}

