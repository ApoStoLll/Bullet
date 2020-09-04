package com.example.bullet

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.Navigation
import com.example.bullet.domain.models.Destination
import com.example.bullet.helpers.GetLocationCallback
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    var place : Destination? = null
    var user : FirebaseUser? = null

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        user = Firebase.auth.currentUser
        checkPerm()
        getLocation(object : GetLocationCallback{
            override fun setLocation(latLng: LatLng) {
                Log.e("Location", latLng.toString())
            }
        })
        //Log.e("Location", LatLng(mFusedLocationProviderClient.lastLocation.result!!.latitude, mFusedLocationProviderClient.lastLocation.result!!.longitude).toString())
        if (user != null) {
            navController.navigate(R.id.mainScreenFragment)
        } else {
            navController.navigate(R.id.authFragment)
        }

    }

    @SuppressLint("MissingPermission")
    fun getLocation(callback: GetLocationCallback){
        val mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationProviderClient.lastLocation.addOnCompleteListener {
            if (it.isSuccessful) {
                val res = it.result
                if(res != null){
                    val latlng = LatLng(res.latitude, res.longitude)
                    callback.setLocation(latlng)
                }
                //Log.e("Location", LatLng(mFusedLocationProviderClient.lastLocation.result!!.latitude, mFusedLocationProviderClient.lastLocation.result!!.longitude).toString())
            }
        }
    }

    fun checkPerm(){
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                200
            )
        }
    }


//    fun setCoorditanesTo(place : Place?){
//        val addOrder = supportFragmentManager.findFragmentById(R.id.addOrderFragment) as? AddOrderFragment
//        //addOrder?.setCoorditanes(place)
//    }

}