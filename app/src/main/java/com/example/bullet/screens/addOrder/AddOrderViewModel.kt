package com.example.bullet.screens.addOrder

import androidx.lifecycle.ViewModel
import com.example.bullet.domain.models.Destination
import com.example.bullet.domain.models.Order
import com.example.bullet.domain.repositories.order.OrderRepository
import com.example.bullet.domain.repositories.order.OrderRepositoryImpl

class AddOrderViewModel : ViewModel(){

    private var orderRepository : OrderRepository = OrderRepositoryImpl()

    // id = 0  - bazovoe, change in repository
    fun sendOrder(title : String, description : String, from : String, to : Destination, price : Int, id : String){
        val order = Order(title = title, description = description, from = from, to = to,
            customerId = id, id = "0", orderPrice = price)
        orderRepository.addOrder(order)
    }

//    fun updateData(){
//        val listener = object : ValueEventListener{
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // Get Post object and use the values to update the UI
//                val order = dataSnapshot.value
//                Log.e("ORDER", order.toString())
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Getting Post failed, log a message
//                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
//                // ...
//            }
//        }
//        orderRepository.readData(listener)
//    }
}