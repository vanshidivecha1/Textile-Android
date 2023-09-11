package com.ib.textileecommerce.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import com.ib.textileecommerce.BR
import com.ib.textileecommerce.R
import com.ib.textileecommerce.customViews.CustomTextView
import com.ib.textileecommerce.databinding.FragmentAddAddressBinding
import com.ib.textileecommerce.module.ViewModelFactory
import com.ib.textileecommerce.utils.SessionManager
import com.ib.textileecommerce.viewModel.AddAddressViewModel
import com.ib.textileecommerce.views.AddAddressView
import javax.inject.Inject

class AddAddressFragment : BaseFragment<FragmentAddAddressBinding>(), AddAddressView {

    private var tag = AddAddressFragment::class.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var addAddressViewModel: AddAddressViewModel
    private lateinit var fragmentAddAddressBinding: FragmentAddAddressBinding

    override fun getViewModel() = addAddressViewModel

    override fun getLayoutId() = R.layout.fragment_add_address

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
            AddAddressFragment().apply {
                layoutToolBar.text = "Add Address"
                ivBack.setOnClickListener {
                    requireActivity().onBackPressed()
                }
            }
    }

    private fun initView() {
    }

    private fun setViewModel() {
        addAddressViewModel =
            ViewModelProviders.of(this, viewModelFactory)[AddAddressViewModel::class.java]
        addAddressViewModel.setViewInterface(this)
        fragmentAddAddressBinding = getViewBinding()
        fragmentAddAddressBinding.viewModel = addAddressViewModel
    }
}