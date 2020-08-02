package com.example.bullet.domain.models

data class Order(
    var id : Int,
    var title : String,
    var from : String,
    var to : String,
    var customerId : Int,
    var description : String,
    var orderPrice : Int
)