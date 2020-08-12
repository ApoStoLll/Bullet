package com.example.bullet.screens.orderList

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bullet.R
import com.example.bullet.adapters.OrderClickHandler
import com.example.bullet.adapters.OrderListAdapter
import com.example.bullet.domain.models.Order
import com.firebase.ui.database.FirebaseRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_order_list.*


class OrderListFragment : Fragment() {

    private lateinit var viewModel: OrderListViewModel
    private lateinit var adapter: FirebaseRecyclerAdapter<Order, OrderListAdapter.ViewHolder>

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(OrderListViewModel::class.java)
        return inflater.inflate(R.layout.fragment_order_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.state.observe(viewLifecycleOwner, Observer<OrderListState>{ state ->
//            when (state) {
//                is OrderListState.LoadingState -> {
//                    //Загрузка
//                }
//                is OrderListState.LoadedState<*> -> {
//                    //Отобразить данные (какие? * - вот такие )
//                    //recyclerView.setData ( * )
//                }
//                is OrderListState.ErrorState -> {
//                    //Сказать соре
//                }
//                is OrderListState.NoItemState -> {
//                    //Сказать соре нет итемов
//                }
//            }
//
//        })
        val linearLayoutManager =  LinearLayoutManager(context)
        RecycleOrders.layoutManager = linearLayoutManager
        RecycleOrders.setHasFixedSize(true)
        adapter = OrderListAdapter(viewModel.getFirebaseOptions())
        (adapter as OrderListAdapter).attachClickHandler(object : OrderClickHandler{
            @SuppressLint("ShowToast")
            override fun onItemClick(item: Order) {
                Log.e("KEK", item.title)
            }
        })
        RecycleOrders.adapter = adapter
    }


}