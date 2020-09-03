package com.example.bullet.helpers

import com.google.android.gms.maps.model.LatLng

interface GetLocationCallback {
    fun setLocation(latLng: LatLng)
}