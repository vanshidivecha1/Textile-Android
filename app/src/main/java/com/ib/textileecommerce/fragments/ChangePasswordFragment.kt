package com.ib.textileecommerce.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import com.ib.textileecommerce.BR
import com.ib.textileecommerce.R
import com.ib.textileecommerce.customViews.CustomTextView
import com.ib.textileecommerce.databinding.FragmentChangePasswordBinding
import com.ib.textileecommerce.module.ViewModelFactory
import com.ib.textileecommerce.utils.SessionManager
import com.ib.textileecommerce.viewModel.ChangePasswordViewModel
import com.ib.textileecommerce.views.ChangePasswordView
import com.ib.textileecommerce.views.OrderHistoryView
import javax.inject.Inject

class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding>(), ChangePasswordView {

    private var tag = ChangePasswordFragment::class.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var changePasswordViewModel: ChangePasswordViewModel
    private lateinit var fragmentChangePasswordBinding: FragmentChangePasswordBinding

    override fun getViewModel() = changePasswordViewModel

    override fun getLayoutId() = R.layout.fragment_change_password

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
            ChangePasswordFragment().apply {
                layoutToolBar.text = "Change Password"
                ivBack.setOnClickListener {
                    requireActivity().onBackPressed()
                }
            }
    }

    private fun initView() {
    }

    private fun setViewModel() {
        changePasswordViewModel =
            ViewModelProviders.of(this, viewModelFactory)[ChangePasswordViewModel::class.java]
        changePasswordViewModel.setViewInterface(this)
        fragmentChangePasswordBinding = getViewBinding()
        fragmentChangePasswordBinding.viewModel = changePasswordViewModel
    }
}