package com.example.bullet.domain.repositories.order

import androidx.lifecycle.MutableLiveData
import com.example.bullet.domain.models.Order
import kotlinx.coroutines.Deferred

interface OrderRepository{
     fun fetchOrders() : MutableLiveData<List<Order>>
     fun addOrder(title : String, description : String, from : String, to : String, customer : String)
}