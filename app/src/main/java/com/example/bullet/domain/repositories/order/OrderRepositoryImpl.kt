package com.example.bullet.domain.repositories.order

import androidx.lifecycle.MutableLiveData
import com.example.bullet.domain.models.Order
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Deferred

class OrderRepositoryImpl() : OrderRepository  {

    private var dbRef : DatabaseReference

    init {
        val db = FirebaseDatabase.getInstance()
        dbRef = db.getReference("orders")
    }

    override fun fetchOrders() : MutableLiveData<List<Order>> {
        TODO("Not yet implemented")
    }

    override fun addOrder(order : Order) {
        //dbRef.setValue("hello")
        dbRef.child(order.customerId.toString()).setValue(order)
    }


}