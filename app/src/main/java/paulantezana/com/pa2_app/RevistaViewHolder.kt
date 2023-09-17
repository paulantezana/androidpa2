package paulantezana.com.pa2_app

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RevistaViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private  val tvCodio: TextView = view.findViewById(R.id.tvCodigo)
    private  val tvTitulo: TextView = view.findViewById(R.id.tvTitulo)
    private  val tvDescripcion: TextView = view.findViewById(R.id.tvDescripcion)
    private  val tvTipo: TextView = view.findViewById(R.id.tvTipo)

    fun render(revista: Revista){
        tvCodio.text = revista.codigo
        tvTitulo.text = revista.titulo
        tvDescripcion.text = revista.descripcion
        tvTipo.text = revista.tipo
    }
}