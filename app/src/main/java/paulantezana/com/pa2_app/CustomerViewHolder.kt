package paulantezana.com.pa2_app

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomerViewHolder (view: View): RecyclerView.ViewHolder(view) {

    private  val tvId: TextView = view.findViewById(R.id.tvId)
    private  val tvFullName: TextView = view.findViewById(R.id.tvFullName)
    private  val tvEmail: TextView = view.findViewById(R.id.tvEmail)
    private  val tvPhone: TextView = view.findViewById(R.id.tvPhone)

    fun render(customer: Customer){
        tvId.text = customer.id.toString()
        tvFullName.text = customer.fullName
        tvEmail.text = customer.email
        tvPhone.text = customer.phone
    }
}