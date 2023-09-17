package paulantezana.com.pa2_app

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LibroViewHolder (view:View):RecyclerView.ViewHolder(view) {
    private  val tvTitulo:TextView = view.findViewById(R.id.tvTitulo)
    private  val tvDescripcion:TextView = view.findViewById(R.id.tvDescripcion)

    fun render(libro: Libro){
        tvTitulo.text = libro.titulo
        tvDescripcion.text = libro.descripcion
    }
}