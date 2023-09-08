package com.ib.textileecommerce.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.ib.textileecommerce.R
import com.ib.textileecommerce.customViews.CustomTextView

class TrackYourOrderFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_track_your_order, container, false)
    }

    companion object {
        fun getInstance(layoutToolBar: CustomTextView, ivBack: ImageView) =
            TrackYourOrderFragment().apply {
                layoutToolBar.text = "Track Your Order"
                ivBack.setOnClickListener {
                    requireActivity().onBackPressed()
                }
            }
    }
}