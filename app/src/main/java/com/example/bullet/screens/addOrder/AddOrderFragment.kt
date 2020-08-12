package com.example.bullet.screens.addOrder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.bullet.R
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
                from = order_from.text.toString(),
                to = order_to.text.toString(),
                price = order_price.text.toString().toInt())
        }

        button_choose_on_map_from.setOnClickListener{
            order_from.text = "from map fragment"
        }
        button_choose_on_map_to.setOnClickListener{
            order_to.text = "to map fragment"
        }
//        viewModel.updateData()
    }


}