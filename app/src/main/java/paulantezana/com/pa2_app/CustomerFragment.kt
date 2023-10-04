package paulantezana.com.pa2_app

import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract.Profile
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CustomerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CustomerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var rootView:View
    private lateinit var rvCustomers: RecyclerView
    private lateinit var fabAddCustomer: FloatingActionButton

    private val customers = mutableListOf<Customer>()
    private lateinit var customerAdapter: CustomerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        this.rootView = inflater.inflate(R.layout.fragment_customer, container, false)

        initComponent()
        initUI()
        initListeners()
        initData()

        return this.rootView
    }

    private fun initData() {
//        when {
//            if (ContextCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION
//            ) == PackageManager.PERMISSION_GRANTED ){
//                ActivityCompat.requestPermissions(
//                    requireContext(),
//                    arrayOf(Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION), DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION)
//            }

//        }
//        this.loadCustomer()
//        val listaLibros: List<Libro> = this.recuperarLibros()
//        for (lib in listaLibros) {
//            this.libros.add(lib)
//        }
//
//        libroAdapter.notifyDataSetChanged()
    }

//    private fun hasInternetPermission(): Boolean {
//        return ContextCompat.checkSelfPermission(
//            requireContext(),
//            Manifest.permission.INTERNET
//        ) == PackageManager.PERMISSION_GRANTED
//    }

//    private fun requestInternetPermission() {
//        requestPermissions(arrayOf(Manifest.permission.INTERNET), REQUEST_INTERNET_PERMISSION)
//    }

    private fun initListeners() {
        this.fabAddCustomer.setOnClickListener{
            showCustomerDialog()
        }
    }

    private fun initUI() {
        this.customerAdapter = CustomerAdapter(this.customers)
        rvCustomers.layoutManager= LinearLayoutManager(this.rootView.context, LinearLayoutManager.VERTICAL, false)
        rvCustomers.adapter = this.customerAdapter
    }

    private fun initComponent() {
        this.rvCustomers = this.rootView.findViewById(R.id.rvCustomers)
        rvCustomers.setHasFixedSize(true)

        this.fabAddCustomer = this.rootView.findViewById(R.id.fabAddCustomer)
    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://localhost/ws_ap3/customer/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun loadCustomer(){
        CoroutineScope(Dispatchers.IO).launch {
            var call = getRetrofit().create(CustomerAPI::class.java).getAll()
            if (call.isSuccess){
                print(call.result)
            }
        }
    }

    private fun showCustomerDialog(){

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CustomerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CustomerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}