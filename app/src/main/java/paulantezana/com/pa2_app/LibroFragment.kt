package paulantezana.com.pa2_app

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LibroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LibroFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val libros = mutableListOf<Libro>()

    private lateinit var rootView:View
    private lateinit var rvLibros: RecyclerView
    private lateinit var fabAddLibro: FloatingActionButton

    private lateinit var libroAdapter: LibroAdapter
    private var nombreMemoria:String = "libro_memoria"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private fun  initComponent(){
        this.rvLibros = this.rootView.findViewById(R.id.rvListadoLibros)
        rvLibros.setHasFixedSize(true)

        this.fabAddLibro = this.rootView.findViewById(R.id.fabAddLibro)
    }

    private fun  initUI(){
        this.libroAdapter = LibroAdapter(this.libros)
        rvLibros.layoutManager= LinearLayoutManager(this.rootView.context,LinearLayoutManager.VERTICAL, false)
        rvLibros.adapter = this.libroAdapter
    }

    private fun initListeners(){
        this.fabAddLibro.setOnClickListener{
            showLibroDialog()
        }
    }

    private fun initData(){
        Toast.makeText(this.rootView.context, "NANI!!!!!!!!!!! DATA", Toast.LENGTH_LONG).show()

        val listaLibros: List<Libro> = this.recuperarLibros()
        for (lib in listaLibros) {
            this.libros.add(lib)
        }

        libroAdapter.notifyDataSetChanged()
    }

    private fun showLibroDialog(){
        val dialog = Dialog(this.rootView.context)
        dialog.setContentView(R.layout.dialog_libro)

        val button: Button = dialog.findViewById(R.id.btnAddLibro)

        val etCodigoLibro: EditText = dialog.findViewById(R.id.etCodigoLibro)
        val etTituloLibro: EditText = dialog.findViewById(R.id.etTituloLibro)
        val etDescripcionLibro: EditText = dialog.findViewById(R.id.etDescripcionLibro)
        val soCategoriaLibro: Spinner = dialog.findViewById(R.id.spCategoriaLibro)

        var categorias = arrayOf("terror","novela","terror")

        soCategoriaLibro.adapter = ArrayAdapter<String>(dialog.context, android.R.layout.simple_list_item_1, categorias )

        button.setOnClickListener{
            val libroCodigo =    etCodigoLibro.text
            val libroTitulo =    etTituloLibro.text
            val libroDescripcion =    etDescripcionLibro.text
            val libroCategoria = soCategoriaLibro.selectedItem.toString()

            if (
                libroCodigo.isNullOrEmpty() && libroTitulo.isNullOrEmpty() && libroDescripcion.isNullOrEmpty()
                && libroCategoria.isNullOrEmpty()
            ){
                Toast.makeText(this.rootView.context, "Todo los campos son requeridos", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val nuevoLibro = Libro(
                libroCodigo.toString(),
                libroTitulo.toString(),
                libroDescripcion.toString(),
                libroCategoria
            )

            this.guardarLibro(nuevoLibro)

            dialog.hide()
        }

        dialog.show()
    }

    private fun guardarLibro(libro:Libro){
        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences(this.nombreMemoria, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        val listaLibros: MutableList<Libro> = recuperarLibros().toMutableList()
        listaLibros.add(libro)

        val gson = Gson()
        val json = gson.toJson(listaLibros)

        editor.putString("libros", json)
        editor.apply()

        this.libros.add(libro)

        libroAdapter.notifyDataSetChanged()
    }

    private fun recuperarLibros():List<Libro>{
        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences(this.nombreMemoria, Context.MODE_PRIVATE)
        val json = sharedPreferences.getString("libros", "[]") ?: "[]"

        val gson = Gson()
        val tipoLista = object : TypeToken<List<Libro>>() {}.type
        val listaLibros: List<Libro> = gson.fromJson(json, tipoLista)

        return listaLibros
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        this.rootView = inflater.inflate(R.layout.fragment_libro, container, false)

        Toast.makeText(this.rootView.context, "NANI!!!!!!!!!!!", Toast.LENGTH_LONG).show()

        initComponent()
        initUI()
        initListeners()
        initData()

        return this.rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LibroFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LibroFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}