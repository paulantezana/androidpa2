package paulantezana.com.pa2_app

import com.google.gson.annotations.SerializedName

data class CustomerResponse (
    @SerializedName("success") var success:Boolean,
    @SerializedName("message") var message:String,
    @SerializedName("result") var result:List<Customer>
)