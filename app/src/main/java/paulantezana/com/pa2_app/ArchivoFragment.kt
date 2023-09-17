package paulantezana.com.pa2_app

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ArchivoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArchivoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val revistas = mutableListOf<Revista>()

    private lateinit var rootView:View
    private lateinit var rvRevistas:RecyclerView
    private lateinit var fabAddRevista:FloatingActionButton

    private lateinit var revistaAdapter: RevistaAdapter
    private var nombreArchivo:String = "archivo_pa2.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private fun  initComponent(){
        this.rvRevistas = this.rootView.findViewById(R.id.rvRevistas)
        rvRevistas.setHasFixedSize(true)

        this.fabAddRevista = this.rootView.findViewById(R.id.fabAddRevista)
    }

    private fun  initUI(){
        this.revistaAdapter = RevistaAdapter(this.revistas)
        rvRevistas.layoutManager= LinearLayoutManager(this.rootView.context,LinearLayoutManager.VERTICAL, false)
        rvRevistas.adapter = this.revistaAdapter
    }

    private fun initListeners(){
        this.fabAddRevista.setOnClickListener{
            showRevistaDialog()
        }
    }
    private fun initData(){
        val listaRevistas: List<Revista> = this.recuperarRevistas()
        for (revis in listaRevistas) {
            this.revistas.add(revis)
        }

        revistaAdapter.notifyDataSetChanged()
    }

    private fun showRevistaDialog(){
        val dialog = Dialog(this.rootView.context)
        dialog.setContentView(R.layout.dialog_revista)

        val button: Button = dialog.findViewById(R.id.btnAddRevista)

        val etCodigoRevista: EditText = dialog.findViewById(R.id.etCodigoRevista)
        val etTituloRevista: EditText = dialog.findViewById(R.id.etTituloRevista)
        val etDescripcionRevista: EditText = dialog.findViewById(R.id.etDescripcionRevista)
        val rgTipoRevista: RadioGroup = dialog.findViewById(R.id.rgTipoRevista)

        button.setOnClickListener{
            val RevistaCodigo =    etCodigoRevista.text
            val RevistaTitulo =    etTituloRevista.text
            val RevistaDescripcion =    etDescripcionRevista.text

            val selectedId = rgTipoRevista.checkedRadioButtonId
            val SelectedRadioButton:RadioButton = rgTipoRevista.findViewById(selectedId)

            if (
                RevistaCodigo.isNullOrEmpty() && RevistaTitulo.isNullOrEmpty() && RevistaDescripcion.isNullOrEmpty()
            ){
                Toast.makeText(this.rootView.context, "Todo los campos son requeridos", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val nuevoRevista = Revista(
                RevistaCodigo.toString(),
                RevistaTitulo.toString(),
                RevistaDescripcion.toString(),
                SelectedRadioButton.text.toString()
            )

            this.guardarRevista(nuevoRevista)

            dialog.hide()
        }

        dialog.show()
    }

    private fun guardarRevista(revista:Revista){
        try {
            val linea = "${revista.codigo}|${revista.titulo}|${revista.descripcion}|${revista.tipo}\n"
            val archivo = context?.applicationContext?.openFileOutput(nombreArchivo, Context.MODE_APPEND)
            archivo?.write(linea.toByteArray())
            archivo?.flush()
            archivo?.close()

            this.revistas.add(revista)

            revistaAdapter.notifyDataSetChanged()

            Toast.makeText(this.rootView.context, "El registro se guardo exitosamente", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun recuperarRevistas():List<Revista>{
        val listaRevistas: MutableList<Revista> = mutableListOf()

        try {
            val fileInput = context?.applicationContext?.openFileInput(nombreArchivo)

            val inputStreamReader = InputStreamReader(fileInput, Charset.forName("UTF-8"))
            val lector = BufferedReader(inputStreamReader)

            var linea: String? = lector.readLine()
            while (linea != null) {
                val partes = linea.split("|")
                if (partes.size == 4) {
                    val codigo = partes[0]
                    val titulo = partes[1]
                    val descripcion = partes[2]
                    val tipo = partes[3]
                    listaRevistas.add(Revista(codigo, titulo, descripcion, tipo))
                }
                linea = lector.readLine()
            }

            lector.close()
            inputStreamReader.close()
            fileInput?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return listaRevistas
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        this.rootView = inflater.inflate(R.layout.fragment_archivo, container, false)

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
         * @return A new instance of fragment ArchivoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ArchivoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}