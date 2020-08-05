package com.example.bullet.ui.order

import androidx.lifecycle.ViewModel
import com.example.bullet.domain.models.Order
import com.example.bullet.domain.repositories.order.OrderRepository
import com.example.bullet.domain.repositories.order.OrderRepositoryImpl

class AddOrderViewModel : ViewModel(){

    private var orderRepository : OrderRepository = OrderRepositoryImpl()

    fun sendOrder(title : String, description : String, from : String, to : String){
        val order = Order(title = title, description = description, from = from, to = to,
            customerId = 1, id = 0, orderPrice = 3)
        orderRepository.addOrder(order)

    }
}