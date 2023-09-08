package com.ib.textileecommerce.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import com.ib.textileecommerce.BR
import com.ib.textileecommerce.R
import com.ib.textileecommerce.customViews.CustomTextView
import com.ib.textileecommerce.databinding.FragmentSettingsBinding
import com.ib.textileecommerce.module.ViewModelFactory
import com.ib.textileecommerce.utils.Constants
import com.ib.textileecommerce.utils.SessionManager
import com.ib.textileecommerce.viewModel.SettingsViewModel
import com.ib.textileecommerce.views.SettingsView
import javax.inject.Inject


class SettingsFragment : BaseFragment<FragmentSettingsBinding>(), SettingsView {

    private var tag = SettingsFragment::class.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var settingsViewModel: SettingsViewModel
    private lateinit var fragmentSettingsBinding: FragmentSettingsBinding

    override fun getViewModel() = settingsViewModel

    override fun getLayoutId() = R.layout.fragment_settings

    override fun getBindingVariable() = BR.viewModel

    private lateinit var layoutView: View
    private lateinit var layoutToolBar: CustomTextView
    private lateinit var ivBack: ImageView

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
        fun getInstance(layoutTopBar: CustomTextView, back: ImageView) = SettingsFragment().apply {
            layoutToolBar = layoutTopBar
            ivBack = back
        }
    }

    private fun initView() {
        fragmentSettingsBinding.tvOrderHistory.setOnClickListener {
            addFragment(
                OrderHistoryFragment.getInstance(layoutToolBar, ivBack),
                Constants.ORDER_HISTORY
            )
        }
        fragmentSettingsBinding.tvTrackYouOrder.setOnClickListener {
            addFragment(
                TrackYourOrderFragment.getInstance(layoutToolBar, ivBack),
                Constants.TRACK_YOUR_ORDER
            )
        }
        fragmentSettingsBinding.tvManageAddress.setOnClickListener {
            addFragment(
                ManageAddressFragment.getInstance(layoutToolBar, ivBack),
                Constants.MANAGE_ADDRESS
            )
        }
        fragmentSettingsBinding.tvChangePassword.setOnClickListener {
            addFragment(
                ChangePasswordFragment.getInstance(layoutToolBar, ivBack),
                Constants.CHANGE_PASSWORD
            )
        }
        fragmentSettingsBinding.tvGiveFeedback.setOnClickListener {
            addFragment(
                GiveFeedbackFragment.getInstance(layoutToolBar, ivBack),
                Constants.GIVE_FEEDBACK
            )
        }
        fragmentSettingsBinding.tvTermsAndCondition.setOnClickListener {
            addFragment(
                TermsAndConditionFragment.getInstance(layoutToolBar, ivBack),
                Constants.TERMS_AND_CONDITION
            )
        }
    }

    private fun setViewModel() {
        settingsViewModel =
            ViewModelProviders.of(this, viewModelFactory)[SettingsViewModel::class.java]
        settingsViewModel.setViewInterface(this)
        fragmentSettingsBinding = getViewBinding()
        fragmentSettingsBinding.viewModel = settingsViewModel
    }
}