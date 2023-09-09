package com.ib.textileecommerce.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import com.ib.textileecommerce.BR
import com.ib.textileecommerce.R
import com.ib.textileecommerce.customViews.CustomTextView
import com.ib.textileecommerce.databinding.FragmentManageAddressBinding
import com.ib.textileecommerce.module.ViewModelFactory
import com.ib.textileecommerce.utils.SessionManager
import com.ib.textileecommerce.viewModel.ManageAddressViewModel
import com.ib.textileecommerce.views.ManageAddressView
import javax.inject.Inject

class ManageAddressFragment : BaseFragment<FragmentManageAddressBinding>(), ManageAddressView {

    private var tag = ManageAddressFragment::class.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var manageAddressViewModel: ManageAddressViewModel
    private lateinit var fragmentManageAddressBinding: FragmentManageAddressBinding

    override fun getViewModel() = manageAddressViewModel

    override fun getLayoutId() = R.layout.fragment_manage_address

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
            ManageAddressFragment().apply {
                layoutToolBar.text = "Manage Address"
                ivBack.setOnClickListener {
                    requireActivity().onBackPressed()
                }
            }
    }

    private fun initView() {
    }

    private fun setViewModel() {
        manageAddressViewModel =
            ViewModelProviders.of(this, viewModelFactory)[ManageAddressViewModel::class.java]
        manageAddressViewModel.setViewInterface(this)
        fragmentManageAddressBinding = getViewBinding()
        fragmentManageAddressBinding.viewModel = manageAddressViewModel
    }
}