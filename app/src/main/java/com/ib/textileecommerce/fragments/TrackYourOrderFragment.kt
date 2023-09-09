package com.ib.textileecommerce.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import com.ib.textileecommerce.BR
import com.ib.textileecommerce.R
import com.ib.textileecommerce.customViews.CustomTextView
import com.ib.textileecommerce.databinding.FragmentTrackYourOrderBinding
import com.ib.textileecommerce.module.ViewModelFactory
import com.ib.textileecommerce.utils.SessionManager
import com.ib.textileecommerce.viewModel.TrackYourOrderViewModel
import com.ib.textileecommerce.views.TrackYourOrderView
import javax.inject.Inject

class TrackYourOrderFragment : BaseFragment<FragmentTrackYourOrderBinding>(), TrackYourOrderView {

    private var tag = TrackYourOrderFragment::class.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var trackYourOrderViewModel: TrackYourOrderViewModel
    private lateinit var fragmentTrackYourOrderBinding: FragmentTrackYourOrderBinding

    override fun getViewModel() = trackYourOrderViewModel

    override fun getLayoutId() = R.layout.fragment_track_your_order

    override fun getBindingVariable() = BR.viewModel

    private lateinit var layoutView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutView = view
        setViewModel()
        initView()
        Log.e(tag, "--")
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

    private fun initView() {
    }

    private fun setViewModel() {
        trackYourOrderViewModel =
            ViewModelProviders.of(this, viewModelFactory)[TrackYourOrderViewModel::class.java]
        trackYourOrderViewModel.setViewInterface(this)
        fragmentTrackYourOrderBinding = getViewBinding()
        fragmentTrackYourOrderBinding.viewModel = trackYourOrderViewModel
    }
}