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
import kotlinx.android.synthetic.main.item_my_list.view.*
import kotlinx.android.synthetic.main.lista_layout.view.*
import kotlinx.android.synthetic.main.lista_layout.view.serie_poster

 class MyListAdapter(private val context: Context,
                    private val serie: MutableList<Series> = mutableListOf(),
                    var onItemClickListener: (series:Series) -> Unit = {},
                    var onDeleta: (series:Series) -> Unit = {}
):RecyclerView.Adapter<MyListAdapter.MyListViewHolder>() {
    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListViewHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.item_my_list, parent, false)
        return MyListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyListViewHolder, position: Int) {
        holder.bind(serie[position])
    }

    override fun getItemCount(): Int = serie.size

     fun add(series: List<Series>) {
         this.serie.clear()
         this.serie.addAll(series)
         notifyDataSetChanged()
     }

    inner class MyListViewHolder(Itemview: View) : RecyclerView.ViewHolder(Itemview) {

        private lateinit var serie: Series
        private val campoTitulo by lazy { itemView.nome_serie_my_list }
//        private val campoData by lazy { itemView.lancamento_my_list }
        private val delbtn by lazy {itemView.deleteBtn}

        init {
            itemView.setOnClickListener {
                if (::serie.isInitialized) {
                    onItemClickListener(serie)
                }
            }
        }

        init {
            delbtn.setOnClickListener() {
                if (::serie.isInitialized) {
                    onDeleta(serie)
                }
            }
        }

        fun bind(series: Series) {
            this.serie = series
            campoTitulo.text = series.name
//            campoData.text = series.release
            Glide.with(itemView).load(IMAGE_BASE + series.poster).into(itemView.serie_poster_my_list)
        }
    }
     fun deletaSerie(series: Series) {
         this.serie.remove(series)
         notifyItemRemoved(serie.indexOf(series))
     }
}