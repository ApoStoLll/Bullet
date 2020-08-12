package com.example.bullet.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.bullet.MainActivity
import com.example.bullet.R
import com.example.bullet.adapters.OrderListAdapter
import com.example.bullet.domain.models.Order
import com.example.bullet.helpers.Keys
import com.example.bullet.ui.order.AddOrderFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_order_list.*

class MainScreenFragment : Fragment() {

    private lateinit var viewModel: MainScreenVM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainScreenVM::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sectionsPagerAdapter = SectionsPagerAdapter(activity as MainActivity, childFragmentManager)
        val viewPager: ViewPager = (activity as MainActivity).findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = (activity as MainActivity).findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        val fab: FloatingActionButton = (activity as MainActivity).findViewById(R.id.fab)
        fab.setOnClickListener {
//            viewModel.addOrder()
            Navigation.findNavController(fab).navigate(R.id.addOrderFragment)

        }

    }


}