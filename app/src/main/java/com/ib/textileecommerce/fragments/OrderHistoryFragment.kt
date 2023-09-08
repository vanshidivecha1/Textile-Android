package com.ib.textileecommerce.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import com.ib.textileecommerce.BR
import com.ib.textileecommerce.R
import com.ib.textileecommerce.customViews.CustomTextView
import com.ib.textileecommerce.databinding.FragmentOrderHistoryBinding
import com.ib.textileecommerce.module.ViewModelFactory
import com.ib.textileecommerce.utils.SessionManager
import com.ib.textileecommerce.viewModel.OrderHistoryViewModel
import com.ib.textileecommerce.views.OrderHistoryView
import javax.inject.Inject


class OrderHistoryFragment : BaseFragment<FragmentOrderHistoryBinding>(), OrderHistoryView {

    private var tag = OrderHistoryFragment::class.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var orderHistoryViewModel: OrderHistoryViewModel
    private lateinit var fragmentOrderHistoryBinding: FragmentOrderHistoryBinding

    override fun getViewModel() = orderHistoryViewModel

    override fun getLayoutId() = R.layout.fragment_order_history

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
        fun getInstance(layoutToolBar: CustomTextView, ivBack: ImageView) = OrderHistoryFragment().apply {
            layoutToolBar.text = "Order History"
            ivBack.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    private fun initView() {
    }

    private fun setViewModel() {
        orderHistoryViewModel =
            ViewModelProviders.of(this, viewModelFactory)[OrderHistoryViewModel::class.java]
        orderHistoryViewModel.setViewInterface(this)
        fragmentOrderHistoryBinding = getViewBinding()
        fragmentOrderHistoryBinding.viewModel = orderHistoryViewModel
    }
}