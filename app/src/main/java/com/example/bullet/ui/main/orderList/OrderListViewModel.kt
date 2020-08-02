package com.example.bullet.ui.main.orderList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bullet.extensions.default
import com.example.bullet.helpers.OrderListState

class OrderListViewModel : ViewModel() {
    val state = MutableLiveData<OrderListState>().default(initialValue = OrderListState.LoadingState)


}