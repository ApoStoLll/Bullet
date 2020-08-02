package com.example.bullet.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.bullet.MainActivity
import com.example.bullet.R
import com.example.bullet.adapters.OrderClickHandler
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
    private lateinit var mAdapter: OrderListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mAdapter = OrderListAdapter()
        mAdapter.attachClickHandler(object : OrderClickHandler {
            override fun onItemClick(item: Int) {
                val bundle = Bundle()
                bundle.putInt("number",item)
//                recyclerCarries.findNavController().navigate(R.id.carryAntipickFragment, bundle)
            }
        })
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RecycleOrders.layoutManager = GridLayoutManager(context, 1)
        RecycleOrders.adapter = mAdapter
        RecycleOrders.recycledViewPool.setMaxRecycledViews(0, 0)


        val sectionsPagerAdapter = SectionsPagerAdapter(activity as MainActivity, (activity as MainActivity).supportFragmentManager)
        val viewPager: ViewPager = (activity as MainActivity).findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = (activity as MainActivity).findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = (activity as MainActivity).findViewById(R.id.fab)
        fab.setOnClickListener {
            viewModel.addOrder()
            val addFragment = AddOrderFragment.newInstance("HI", "HI")
            val transaction =  (activity as MainActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_holder,addFragment)
            transaction.addToBackStack(null)
            transaction.commit()

        }
    }

}