package com.example.bullet.screens.addOrder

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.bullet.MainActivity
import com.example.bullet.R
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.android.synthetic.main.fragment_add_order.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [AddOrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddOrderFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var viewModel: AddOrderViewModel
    var from : LatLng = LatLng(0.0,0.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddOrderViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_add_order.setOnClickListener {
            viewModel.sendOrder(title = order_title.text.toString(),
                description =  order_description.text.toString(),
                from = "hz",
                to = order_to.text.toString(),
                price = order_price.text.toString().toInt())
        }

        val place = (activity as MainActivity).place

        if (place != null){
            order_from.setText(place.name)
            from = place.position!!
        }

        button_choose_on_map_from.setOnClickListener{
            val bundle = Bundle()
            bundle.putDouble("Lat",from.latitude)
            bundle.putDouble("Lng",from.longitude)
            button_choose_on_map_from.findNavController().navigate(R.id.chooseMapFragment,bundle)
        }
        button_choose_on_map_to.setOnClickListener{
            order_to.text = "to map fragment"
        }
//        viewModel.updateData()
    }

//    fun setCoorditanes(place : Place?){
//        Log.e("COORD", "RABOTAET BLYAT")
//        if (place != null){
//            order_to.text = place.name
//        }
//    }


}