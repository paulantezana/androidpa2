package paulantezana.com.pa2_app

import com.android.volley.Response
import retrofit2.http.GET

interface CustomerAPI {
    @GET("all")
    suspend fun getAll():Response<CustomerResponse>;
}