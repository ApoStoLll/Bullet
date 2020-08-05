package com.example.bullet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.bullet.ui.main.MainScreenFragment
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.navigate(R.id.mainScreenFragment)
        //start()
    }

//    private fun start(){
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragment_holder, MainScreenFragment())
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }
}