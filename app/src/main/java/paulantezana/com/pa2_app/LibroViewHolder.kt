package paulantezana.com.pa2_app

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LibroViewHolder (view:View):RecyclerView.ViewHolder(view) {
    private  val tvCodigo:TextView = view.findViewById(R.id.tvCodigo)
    private  val tvTitulo:TextView = view.findViewById(R.id.tvTitulo)
    private  val tvDescripcion:TextView = view.findViewById(R.id.tvDescripcion)
    private  val tvCategoria:TextView = view.findViewById(R.id.tvCategoria)

    fun render(libro: Libro){
        tvTitulo.text = libro.titulo
        tvDescripcion.text = libro.descripcion
        tvCodigo.text = libro.codigo
        tvCategoria.text = libro.categoria
    }
}