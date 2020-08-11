package com.example.bullet.ui.main.orderList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bullet.R
import com.example.bullet.adapters.OrderClickHandler
import com.example.bullet.adapters.OrderListAdapter
import com.example.bullet.domain.models.Order
import com.example.bullet.helpers.OrderListState
import kotlinx.android.synthetic.main.fragment_order_list.*


class OrderListFragment : Fragment() {

    private lateinit var viewModel: OrderListViewModel
    private lateinit var mAdapter: OrderListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(OrderListViewModel::class.java)
        // Inflate the layout for this fragment
        mAdapter = OrderListAdapter()
        val orderList = listOf(
            Order(1,"seseg","ege","segeg",52,"seesfe",56),
            Order(1,"seseg","ege","segeg",52,"seesfe",56)
        )
        mAdapter.setData(orderList)
        mAdapter.attachClickHandler(object : OrderClickHandler {
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
        RecycleOrders.adapter = mAdapter
        RecycleOrders.recycledViewPool.setMaxRecycledViews(0, 0)
    }




}