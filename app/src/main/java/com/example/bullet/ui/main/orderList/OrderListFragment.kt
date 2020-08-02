package com.example.bullet.ui.main.orderList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.bullet.R
import com.example.bullet.helpers.OrderListState


class OrderListFragment : Fragment() {

    private lateinit var viewModel: OrderListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(OrderListViewModel::class.java)
        // Inflate the layout for this fragment
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
    }


}