package paulantezana.com.pa2_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CustomerAdapter (private val customers:List<Customer>) : RecyclerView.Adapter<CustomerViewHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_customer, parent, false)
        return  CustomerViewHolder(view)
    }

    override fun getItemCount() = customers.size

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        holder.render(customers[position])
    }

}