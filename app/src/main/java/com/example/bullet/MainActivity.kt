package com.example.bullet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bullet.ui.main.MainScreenFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start()
    }

    private fun start(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_holder, MainScreenFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }
}