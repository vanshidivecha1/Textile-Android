package com.ib.textileecommerce.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.ib.textileecommerce.R
import com.ib.textileecommerce.customViews.CustomTextView

class ManageAddressFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manage_address, container, false)
    }

    companion object {
        fun getInstance(layoutToolBar: CustomTextView, ivBack: ImageView) =
            ManageAddressFragment().apply {
                layoutToolBar.text = "Manage Address"
                ivBack.setOnClickListener {
                    requireActivity().onBackPressed()
                }
            }
    }
}