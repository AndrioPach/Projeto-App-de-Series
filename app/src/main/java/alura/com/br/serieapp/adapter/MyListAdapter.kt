package alura.com.br.serieapp.adapter

import alura.com.br.serieapp.R
import alura.com.br.serieapp.models.Series
import alura.com.br.serieapp.ui.RatingStars
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.lista_layout.view.*

class MyListAdapter(private val context: Context,
                    private val series: MutableList<Series> = mutableListOf(),
                    var onItemClickListener: (series:Series) -> Unit = {}
):RecyclerView.Adapter<MyListAdapter.MyListViewHolder>() {
    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListViewHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.item_my_list, parent, false)
        return MyListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyListViewHolder, position: Int) {
        holder.bind(series[position])
    }

    override fun getItemCount(): Int = series.size

    inner class MyListViewHolder(Itemview: View) : RecyclerView.ViewHolder(Itemview) {
        private lateinit var serie: Series
        private val campoTitulo by lazy { itemView.nome_serie }
        private val campoData by lazy { itemView.data_lancamento }
        private val campoVote by lazy { itemView.vote_average }
        private val campoRatingBar by lazy { itemView.rating_bar }

        init {
            itemView.setOnClickListener {
                if (::serie.isInitialized) {
                    onItemClickListener(serie)
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