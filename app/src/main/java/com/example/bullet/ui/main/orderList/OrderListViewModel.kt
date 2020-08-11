package com.example.bullet.ui.main.orderList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bullet.domain.models.Order
import com.example.bullet.domain.repositories.order.OrderRepository
import com.example.bullet.domain.repositories.order.OrderRepositoryImpl
import com.example.bullet.extensions.default
import com.example.bullet.helpers.OrderListState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class OrderListViewModel : ViewModel() {

    val state = MutableLiveData<OrderListState>().default(initialValue = OrderListState.LoadingState)
    private var orderRepository : OrderRepository = OrderRepositoryImpl()
    val orderList = listOf(
        Order(1,"seseg","ege","segeg",52,"seesfe",56),
        Order(1,"seseg","ege","segeg",52,"seesfe",56)
    )


    fun updateData(){
        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val order = dataSnapshot.value
                // orderList.add
                Log.e("ORDER", order.toString())
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        orderRepository.readData(listener)
    }

}