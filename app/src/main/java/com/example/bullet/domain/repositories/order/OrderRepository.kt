package com.example.bullet.domain.repositories.order

import androidx.lifecycle.MutableLiveData
import com.example.bullet.domain.models.Order
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Deferred

interface OrderRepository{
     fun fetchOrders() : MutableLiveData<List<Order>>
     fun addOrder(order : Order)
     fun readData(listener : ValueEventListener)
}