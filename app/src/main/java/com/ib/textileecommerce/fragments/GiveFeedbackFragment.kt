package com.ib.textileecommerce.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ib.textileecommerce.BR
import com.ib.textileecommerce.R
import com.ib.textileecommerce.customViews.CustomTextView
import com.ib.textileecommerce.databinding.FragmentGiveFeedbackBinding
import com.ib.textileecommerce.databinding.FragmentOrderHistoryBinding
import com.ib.textileecommerce.module.ViewModelFactory
import com.ib.textileecommerce.utils.SessionManager
import com.ib.textileecommerce.viewModel.GiveFeedBackViewModel
import com.ib.textileecommerce.viewModel.OrderHistoryViewModel
import com.ib.textileecommerce.views.GiveFeedBackView
import com.ib.textileecommerce.views.OrderHistoryView
import javax.inject.Inject

class GiveFeedbackFragment : BaseFragment<FragmentGiveFeedbackBinding>(), GiveFeedBackView {
    private var tag = GiveFeedbackFragment::class.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var giveFeedBackViewModel: GiveFeedBackViewModel
    private lateinit var fragmentGiveFeedBackBinding: FragmentGiveFeedbackBinding

    override fun getViewModel() = giveFeedBackViewModel

    override fun getLayoutId() = R.layout.fragment_give_feedback

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
            GiveFeedbackFragment().apply {
                layoutToolBar.text = "Give Feedback"
                ivBack.setOnClickListener {
                    requireActivity().onBackPressed()
                }
            }
    }
    private fun initView() {
    }

    private fun setViewModel() {
        giveFeedBackViewModel =
            ViewModelProviders.of(this, viewModelFactory)[GiveFeedBackViewModel::class.java]
        giveFeedBackViewModel.setViewInterface(this)
        fragmentGiveFeedBackBinding = getViewBinding()
        fragmentGiveFeedBackBinding.viewModel = giveFeedBackViewModel
    }
}