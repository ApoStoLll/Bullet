package com.example.bullet.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.bullet.MainActivity
import com.example.bullet.R
import com.example.bullet.domain.models.Order
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

interface OrderClickHandler{
    fun onItemClick(item : Order)
}

class OrderListAdapter(options: FirebaseRecyclerOptions<Order>) : FirebaseRecyclerAdapter<Order, OrderListAdapter.ViewHolder>(
    options
) {

    private var orderClickHandler : OrderClickHandler? = null

    fun attachClickHandler(orderClickHandler: OrderClickHandler){
        this.orderClickHandler = orderClickHandler
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.order, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Order) {

        holder.setTitle(model.title)
        holder.root.setOnClickListener{
            orderClickHandler?.onItemClick(item = model)
        }
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var root : ConstraintLayout = itemView.findViewById(R.id.item_root)
        private var orderTitle: TextView =  itemView.findViewById(R.id.order_name)
        private var orderDistance: TextView = itemView.findViewById(R.id.order_distance)

        fun setTitle(string: String?) {
            orderTitle.text = string
        }

        fun setDistance(string: String?) {
            orderDistance.text = string
        }

    }



}

//interface OrderClickHandler {
//    fun onItemClick(item: Int)
//}
//
//class OrderListAdapter : RecyclerView.Adapter<OrderListAdapter.OrderListViewHolder>(), AdapterData<Order> {
//    private val data: MutableList<Order> = ArrayList()
//    private var orderClickHandler: OrderClickHandler? = null
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListViewHolder {
//        return OrderListViewHolder(itemView = LayoutInflater.from(parent.context).inflate(R.layout.order, parent, false),
//            orderClickHandler = orderClickHandler)
//    }
//
//    override fun getItemCount(): Int {
//        return data.size
//    }
//
//    override fun onBindViewHolder(holder: OrderListViewHolder, position: Int) {
//        holder.bind(source = data[position])
//    }
//
//    override fun setData(items: List<Order>) {
//        data.clear()
//        data.addAll(items)
//        notifyDataSetChanged()
//    }
//
//    fun attachClickHandler(orderClickHandler: OrderClickHandler) {
//        this.orderClickHandler = orderClickHandler
//    }
//
//    class OrderListViewHolder(itemView: View, private val orderClickHandler: OrderClickHandler?) : RecyclerView.ViewHolder(itemView) {
//        private val orderTitle = itemView.findViewById<TextView>(R.id.order_name)
//        private val orderDistance = itemView.findViewById<TextView>(R.id.order_distance)
//
//        fun bind(source: Order) {
//            orderTitle.text = source.title
//            orderDistance.text = "24"
//        }
//    }
//}