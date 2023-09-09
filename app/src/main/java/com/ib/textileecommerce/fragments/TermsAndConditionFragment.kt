package com.ib.textileecommerce.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import com.ib.textileecommerce.BR
import com.ib.textileecommerce.R
import com.ib.textileecommerce.customViews.CustomTextView
import com.ib.textileecommerce.databinding.FragmentTermsAndConditionBinding
import com.ib.textileecommerce.module.ViewModelFactory
import com.ib.textileecommerce.utils.SessionManager
import com.ib.textileecommerce.viewModel.TermsAndConditionViewModel
import com.ib.textileecommerce.views.OrderHistoryView
import com.ib.textileecommerce.views.TermsAndConditionView
import javax.inject.Inject

class TermsAndConditionFragment : BaseFragment<FragmentTermsAndConditionBinding>(),
    TermsAndConditionView {

    private var tag = TermsAndConditionFragment::class.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var termsAndConditionViewModel: TermsAndConditionViewModel
    private lateinit var fragmentTermsAndConditionBinding: FragmentTermsAndConditionBinding

    override fun getViewModel() = termsAndConditionViewModel

    override fun getLayoutId() = R.layout.fragment_terms_and_condition

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
            TermsAndConditionFragment().apply {
                layoutToolBar.text = "Terms and Condition"
                ivBack.setOnClickListener {
                    requireActivity().onBackPressed()
                }
            }
    }

    private fun initView() {
    }

    private fun setViewModel() {
        termsAndConditionViewModel =
            ViewModelProviders.of(this, viewModelFactory)[TermsAndConditionViewModel::class.java]
        termsAndConditionViewModel.setViewInterface(this)
        fragmentTermsAndConditionBinding = getViewBinding()
        fragmentTermsAndConditionBinding.viewModel = termsAndConditionViewModel
    }
}