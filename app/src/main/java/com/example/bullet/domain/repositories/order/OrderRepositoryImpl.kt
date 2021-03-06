package com.example.bullet.domain.repositories.order

import androidx.lifecycle.MutableLiveData
import com.example.bullet.domain.models.Order
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Deferred
import java.util.*
import javax.inject.Inject


class OrderRepositoryImpl : OrderRepository  {

    override lateinit var dbRef : DatabaseReference

    init {
        val db = FirebaseDatabase.getInstance()
        dbRef = db.getReference("orders")
    }

    override fun fetchOrders() : MutableLiveData<List<Order>> {
        TODO("Not yet implemented")
    }

    override fun addOrder(order : Order) {
        //dbRef.setValue("hello")
        val idLog = dbRef.push().key
        if (idLog != null) {
            order.id = idLog
        }
        dbRef.child(order.id).setValue(order)
    }

//    override fun readData(listener : ValueEventListener){
//        dbRef.addValueEventListener(listener)
//    }


}