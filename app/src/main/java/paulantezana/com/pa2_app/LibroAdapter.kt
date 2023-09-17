package paulantezana.com.pa2_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class LibroAdapter (private val libros:List<Libro>) : RecyclerView.Adapter<LibroViewHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_libro, parent, false)
        return  LibroViewHolder(view)
    }

    override fun getItemCount() = libros.size

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        holder.render(libros[position])
    }
}