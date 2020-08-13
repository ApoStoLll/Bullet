package com.example.bullet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.bullet.domain.models.NewPlace
import com.example.bullet.screens.addOrder.AddOrderFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient

class MainActivity : AppCompatActivity() {

    var place : NewPlace? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.navigate(R.id.mainScreenFragment)
    }


//    fun setCoorditanesTo(place : Place?){
//        val addOrder = supportFragmentManager.findFragmentById(R.id.addOrderFragment) as? AddOrderFragment
//        //addOrder?.setCoorditanes(place)
//    }

}