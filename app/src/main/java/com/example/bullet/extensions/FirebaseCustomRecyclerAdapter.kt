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

abstract class FirebaseCustomRecyclerAdapter<T : Order, VH : RecyclerView.ViewHolder>(options: FirebaseRecyclerOptions<T>) :
    FirebaseRecyclerAdapter<T, VH>(options) {
    init {

    }

    override fun getSnapshots(): ObservableSnapshotArray<T> {
        val snapshots = super.getSnapshots()
        val sorted  = snapshots.sortedBy { it.orderPrice }
//        ObservableSnapshotArray<>(SnapshotParser<> { it.child() })
        //snapshots
        return sorted
    }

    override fun getItem(position: Int): T {
        return snapshots.get(position)
    }
}