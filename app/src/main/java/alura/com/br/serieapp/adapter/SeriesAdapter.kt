package alura.com.br.serieapp.adapter

import alura.com.br.serieapp.R
import alura.com.br.serieapp.models.Series
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.lista_layout.view.*

class SeriesAdapter(private val context: Context,
                    private val serie: MutableList<Series> = mutableListOf(),
                    var onItemClickListener: (series: Series) -> Unit = {}
):RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {


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

//        init {
//            itemView.setOnClickListener {
//                if(::serie.isInitialized){
//                    onItemClickListener(serie)
//                }
//            }
//        }
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

        fun bind(series: Series) {
            this.serie = series
            campoTitulo.text = series.name
            campoData.text = series.release
            Glide.with(itemView).load(IMAGE_BASE + series.poster).into(itemView.serie_poster)
        }


    }
}

