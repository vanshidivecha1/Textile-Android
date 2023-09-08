package com.ib.textileecommerce.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ib.textileecommerce.R

class FavouriteAdapter(context: Context) :
    RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolderClass>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolderClass {
        return FavouriteViewHolderClass(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_favourite_items, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: FavouriteViewHolderClass, position: Int) {
    }

    class FavouriteViewHolderClass(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}