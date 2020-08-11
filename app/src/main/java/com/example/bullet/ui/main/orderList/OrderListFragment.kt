package com.example.bullet.ui.main.orderList

import android.graphics.ColorSpace.Model
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.bullet.R
import com.example.bullet.adapters.OrderClickHandler
import com.example.bullet.adapters.OrderListAdapter
import com.example.bullet.domain.models.Order
import com.example.bullet.helpers.OrderListState
import com.firebase.ui.database.FirebaseListOptions
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.firebase.ui.database.SnapshotParser
import com.google.firebase.database.DataSnapshot
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
        adapter = OrderListAdapter()
        val orderList = listOf(
            Order(1,"seseg","ege","segeg",52,"seesfe",56),
            Order(1,"seseg","ege","segeg",52,"seesfe",56)
        )
        adapter.setData(orderList)
        adapter.attachClickHandler(object : OrderClickHandler {
            override fun onItemClick(item: Int) {
                val bundle = Bundle()
                bundle.putInt("number",item)
//                recyclerCarries.findNavController().navigate(R.id.carryAntipickFragment, bundle)
            }
        })
        Log.e("tab","works1")
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
        Log.e("tab","works2")
        RecycleOrders.layoutManager = GridLayoutManager(context,1) // Grid
        RecycleOrders.adapter = adapter
        RecycleOrders.recycledViewPool.setMaxRecycledViews(0, 0)
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
                return object : ViewHolder(view)
            }

            override fun onBindViewHolder(
                holder: ViewHolder,
                position: Int,
                model: Order
            ) {
                holder.setTxtTitle(model.getmTitle())
                holder.setTxtDesc(model.getmDesc())
                holder.root.setOnClickListener(View.OnClickListener {
                    Toast.makeText(this@MainActivity, position.toString(), Toast.LENGTH_SHORT)
                        .show()
                })
            }

        }
        recyclerView.setAdapter(adapter)
    }




}