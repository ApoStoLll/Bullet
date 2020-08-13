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
import com.example.bullet.domain.models.Destination
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
    var to : Destination = Destination("name",LatLng(0.0,0.0))

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
            lateinit var id : String
            val user = (activity as MainActivity).user
            if (user == null)
                id = "WTF"
            else id = user.uid
            viewModel.sendOrder(title = order_title.text.toString(),
                description =  order_description.text.toString(),
                from = order_from.text.toString(),
                to = to,
                price = order_price.text.toString().toInt(),
                id = id)
            button_add_order.findNavController().navigate(R.id.mainScreenFragment)
        }

        val place = (activity as MainActivity).place

        if (place != null){
            order_to.text = place.name
            to = place
        }

        button_choose_on_map_to.setOnClickListener{
            val bundle = Bundle()
            bundle.putDouble("Lat",to.position!!.latitude)
            bundle.putDouble("Lng",to.position!!.longitude)
            button_choose_on_map_to.findNavController().navigate(R.id.chooseMapFragment,bundle)
        }

    }




}