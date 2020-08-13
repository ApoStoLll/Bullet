package com.example.bullet.screens.orderList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bullet.domain.models.Order
import com.example.bullet.domain.repositories.order.OrderRepository
import com.example.bullet.domain.repositories.order.OrderRepositoryImpl
import com.example.bullet.extensions.default
import com.example.bullet.helpers.OrderListState
import com.firebase.ui.database.FirebaseRecyclerOptions

class OrderListViewModel : ViewModel() {

    val state = MutableLiveData<OrderListState>().default(initialValue = OrderListState.LoadingState)
    private var orderRepository : OrderRepository = OrderRepositoryImpl()

    fun getFirebaseOptions(): FirebaseRecyclerOptions<Order> {
        val query = orderRepository.dbRef
        val options = FirebaseRecyclerOptions.Builder<Order>()
            .setQuery(query
            ) { snapshot ->
                Order(
                    snapshot.child("id").value.toString(),
                    snapshot.child("title").value.toString(),
                    snapshot.child("from").value.toString(),
                    snapshot.child("to").value.toString(),
                    snapshot.child("customerId").value.toString(),
                    snapshot.child("description").value.toString(),
                    snapshot.child("orderPrice").value.toString().toInt()
                )
            }
            .build()
        return options
    }

}