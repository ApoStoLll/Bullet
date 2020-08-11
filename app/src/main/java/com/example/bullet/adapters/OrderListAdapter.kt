package com.example.bullet.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bullet.R
import com.example.bullet.domain.models.Order

interface OrderClickHandler {
    fun onItemClick(item: Int)
}

class OrderListAdapter : RecyclerView.Adapter<OrderListAdapter.OrderListViewHolder>(), AdapterData<Order> {
    private val data: MutableList<Order> = ArrayList()
    private var orderClickHandler: OrderClickHandler? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListViewHolder {
        return OrderListViewHolder(itemView = LayoutInflater.from(parent.context).inflate(R.layout.order, parent, false),
            orderClickHandler = orderClickHandler)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: OrderListViewHolder, position: Int) {
        holder.bind(source = data[position])
    }

    override fun setData(items: List<Order>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    fun attachClickHandler(orderClickHandler: OrderClickHandler) {
        this.orderClickHandler = orderClickHandler
    }

    class OrderListViewHolder(itemView: View, private val orderClickHandler: OrderClickHandler?) : RecyclerView.ViewHolder(itemView) {
        private val orderTitle = itemView.findViewById<TextView>(R.id.order_name)
        private val orderDistance = itemView.findViewById<TextView>(R.id.order_distance)

        fun bind(source: Order) {
            orderTitle.text = source.title
            orderDistance.text = "24"
        }
    }
}