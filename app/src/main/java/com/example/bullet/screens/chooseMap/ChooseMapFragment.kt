package com.example.bullet.screens.chooseMap

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.bullet.MainActivity
import com.example.bullet.R
import com.example.bullet.domain.models.Destination
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode


import kotlinx.android.synthetic.main.fragment_choose_map.*


class ChooseMapFragment : Fragment() {

    var googleMap : GoogleMap? = null
    var choosenPlace : Destination? = null
    var startPlace : LatLng? = null
    //Если вдруг почему-то карта не успеет загрузится до выбора локации, то локация не выберется, странно (смотри в он активити резалт)

    private val callback = OnMapReadyCallback { googleMap ->
        this.googleMap = googleMap

        if(startPlace != LatLng(0.0,0.0)){
            googleMap?.apply {
                addMarker(MarkerOptions().position(startPlace!!).title("From"))
                moveCamera(CameraUpdateFactory.newLatLng(startPlace))
                animateCamera(CameraUpdateFactory.zoomTo(14.04f))
            }
        }


        googleMap.setOnMapLongClickListener {
            googleMap.apply {
                clear()
                addMarker(MarkerOptions().position(it).title("CustomMarker"))
                choosenPlace = Destination("On map",it)
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            startPlace = LatLng(it.getDouble("Lat"),it.getDouble("Lng"))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.choose_map_fragment) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        val apiKey = "AIzaSyDPDGhff4wHV49OwPG2T8zRZ9-uHseLEZw"

        if (!Places.isInitialized()) {
            Places.initialize((activity as MainActivity), apiKey)
        }
        val placesClient = Places.createClient(activity as MainActivity)

        choose_map_search_button.setOnClickListener {
            onSearchCalled()
        }

        button_ready.setOnClickListener{
//            (activity as MainActivity).setCoorditanesTo(place = choosenPlace)
            (activity as MainActivity).place = choosenPlace
            button_ready.findNavController().popBackStack()
        }

    }

    fun onSearchCalled() {
        val fields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)
        val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
            .build(activity as MainActivity)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    data?.let {
                        val place = Autocomplete.getPlaceFromIntent(data)
                        googleMap?.apply {
                            clear()
                            addMarker(MarkerOptions().position(place.latLng!!).title("From"))
                            moveCamera(CameraUpdateFactory.newLatLng(place.latLng))
                            animateCamera(CameraUpdateFactory.zoomTo(14.04f))
                        }
                        choosenPlace = Destination(place.name,place.latLng)
                        Log.e("TAG", "Place: ${place.name}, ${place.id}, ${place.latLng}")
                    }
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    // TODO: Handle the error.
                    data?.let {
                        val status = Autocomplete.getStatusFromIntent(data)
                        status.statusMessage?.let { it1 -> Log.i("TAG", it1) }
                    }
                }
                Activity.RESULT_CANCELED -> {
                    // The user canceled the operation.
                }
            }
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}