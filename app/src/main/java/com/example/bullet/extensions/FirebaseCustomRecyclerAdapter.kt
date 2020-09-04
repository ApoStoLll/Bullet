package com.example.bullet.extensions

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.bullet.domain.models.Destination
import com.example.bullet.domain.models.Order
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.firebase.ui.database.ObservableSnapshotArray
import com.firebase.ui.database.SnapshotParser
import com.google.android.gms.maps.model.LatLng
import java.util.*
import kotlin.collections.ArrayList

abstract class FirebaseCustomRecyclerAdapter<T : Order, VH : RecyclerView.ViewHolder>(options: FirebaseRecyclerOptions<T>) :
    FirebaseRecyclerAdapter<T, VH>(options) {
    init {

    }

    override fun getSnapshots(): ObservableSnapshotArray<T> {
        val snapshots = super.getSnapshots()
        val sorted = snapshots.sortedBy { it.orderPrice }
        for(i in 0 until sorted.size){
            snapshots[i].orderPrice = sorted[i].orderPrice
            Log.e("For", sorted[i].title)
            snapshots[i].title = sorted[i].title
            //snapshots[i].orderPrice = sorted[i].orderPrice
        }
        return snapshots
    }

    override fun getItem(position: Int): T {
        return snapshots.get(position)
    }
}