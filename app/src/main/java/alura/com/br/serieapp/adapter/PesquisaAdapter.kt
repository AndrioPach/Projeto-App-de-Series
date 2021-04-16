package alura.com.br.serieapp.adapter
import alura.com.br.serieapp.R
import alura.com.br.serieapp.models.Series
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.lista_layout.view.serie_poster
import kotlinx.android.synthetic.main.pesquisa_item.view.*

class PesquisaAdapter(private val context: Context,
                      private val series: MutableList<Series> = mutableListOf(),
                      var onItemClickListener: (series:Series) -> Unit = {}
) : RecyclerView.Adapter<PesquisaAdapter.PesquisaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PesquisaViewHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.pesquisa_item, parent, false)
        return PesquisaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PesquisaViewHolder, position: Int) {
        holder.bind(series[position])
    }

    override fun getItemCount(): Int = series.size

    fun append(serie: List<Series>) {
        this.series.clear()
        this.series.addAll(serie)
        notifyDataSetChanged()
    }

    inner class PesquisaViewHolder(view: View): RecyclerView.ViewHolder(view){
        private lateinit var series: Series

        private val seriePoster by lazy{
            view.serie_poster
        }

        private val serieTitulo by lazy{
            view.serie_nome_pesquisa
        }
        init {
            view.setOnClickListener {
                if(::series.isInitialized) {
                    onItemClickListener(series)
                }
            }
        }

        fun bind(series: Series){
            this.series = series
            serieTitulo.text = series.name
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500"+series.poster)
                .into(seriePoster)
        }
    }


}