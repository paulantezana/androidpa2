package paulantezana.com.pa2_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RevistaAdapter (private val revistas:List<Revista>) : RecyclerView.Adapter<RevistaViewHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RevistaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_revista, parent, false)
        return  RevistaViewHolder(view)
    }

    override fun getItemCount() = revistas.size

    override fun onBindViewHolder(holder: RevistaViewHolder, position: Int) {
        holder.render(revistas[position])
    }
}