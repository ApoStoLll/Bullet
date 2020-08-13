package com.example.bullet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.navigate(R.id.mainScreenFragment)
    }

}