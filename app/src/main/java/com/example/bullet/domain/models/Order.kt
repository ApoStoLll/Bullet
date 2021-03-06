package com.example.bullet.domain.models

data class Order(
    var id : String,
    var title : String,
    var from : String,
    var to : Destination,
    var customerId : String,
    var description : String,
    var orderPrice : Int
)