package com.example.bullet.ui.main.orderList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bullet.R
import com.example.bullet.domain.models.Order
import com.example.bullet.helpers.OrderListState
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import kotlinx.android.synthetic.main.fragment_order_list.*


class OrderListFragment : Fragment() {

    private lateinit var viewModel: OrderListViewModel
    private lateinit var adapter: FirebaseRecyclerAdapter<Order, ViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(OrderListViewModel::class.java)
        // Inflate the layout for this fragment
//        adapter = OrderListAdapter()
//        val orderList = listOf(
//            Order(1,"seseg","ege","segeg",52,"seesfe",56),
//            Order(1,"seseg","ege","segeg",52,"seesfe",56)
//        )
//        adapter.setData(orderList)
//        adapter.attachClickHandler(object : OrderClickHandler {
//            override fun onItemClick(item: Int) {
//                val bundle = Bundle()
//                bundle.putInt("number",item)
////                recyclerCarries.findNavController().navigate(R.id.carryAntipickFragment, bundle)
//            }
//        })
//        Log.e("tab","works1")

        return inflater.inflate(R.layout.fragment_order_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(this@OrderListFragment, Observer<OrderListState>{ state ->
            when (state) {
                is OrderListState.LoadingState -> {
                    //Загрузка
                }
                is OrderListState.LoadedState<*> -> {
                    //Отобразить данные (какие? * - вот такие )
                    //recyclerView.setData ( * )
                }
                is OrderListState.ErrorState -> {
                    //Сказать соре
                }
                is OrderListState.NoItemState -> {
                    //Сказать соре нет итемов
                }
            }

        })
//        Log.e("tab","works2")
//        RecycleOrders.layoutManager = GridLayoutManager(context,1) // Grid
//        RecycleOrders.adapter = adapter
//        RecycleOrders.recycledViewPool.setMaxRecycledViews(0, 0)
        val linearLayoutManager =  LinearLayoutManager(context)
        RecycleOrders1.layoutManager = linearLayoutManager
        RecycleOrders1.setHasFixedSize(true);
        fetch()
    }

    private fun fetch() {
        val query: Query = FirebaseDatabase.getInstance()
            .reference
            .child("orders")
        val options = FirebaseRecyclerOptions.Builder<Order>()
            .setQuery(query
            ) { snapshot ->
                Order(
                    snapshot.child("id").value.toString().toInt(),
                    snapshot.child("title").value.toString(),
                    snapshot.child("from").value.toString(),
                    snapshot.child("to").value.toString(),
                    snapshot.child("customerId").value.toString().toInt(),
                    snapshot.child("description").value.toString(),
                    snapshot.child("orderPrice").value.toString().toInt()
                )
            }
            .build()

        adapter = object : FirebaseRecyclerAdapter<Order, ViewHolder>(options) {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): ViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.order, parent, false)
                return ViewHolder(view)
            }



            override fun onBindViewHolder(
                holder: ViewHolder,
                position: Int,
                model: Order
            ) {
                holder.setTitle(model.title)
                holder.setDistance(model.id.toString())


            }

        }
        RecycleOrders1.adapter = adapter
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var root: LinearLayout
        var orderTitle: TextView
        var orderDistance: TextView
        fun setTitle(string: String?) {
            orderTitle.text = string
        }

        fun setDistance(string: String?) {
            orderDistance.text = string
        }

        init {
//            root = itemView.findViewById(R.id.order_name)
            orderTitle = itemView.findViewById(R.id.order_name)
            orderDistance = itemView.findViewById(R.id.order_distance)
        }
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }


}