package com.example.bullet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.bullet.domain.models.Destination
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    var place : Destination? = null
    var user : FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        user = Firebase.auth.currentUser
        if (user != null) {
            navController.navigate(R.id.mainScreenFragment)
        } else {
            navController.navigate(R.id.authFragment)
        }

    }


//    fun setCoorditanesTo(place : Place?){
//        val addOrder = supportFragmentManager.findFragmentById(R.id.addOrderFragment) as? AddOrderFragment
//        //addOrder?.setCoorditanes(place)
//    }

}