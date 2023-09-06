package com.ib.textileecommerce.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.ib.textileecommerce.BR
import com.ib.textileecommerce.R
import com.ib.textileecommerce.activities.DashBoardActivity
import com.ib.textileecommerce.databinding.FragmentSettingsBinding
import com.ib.textileecommerce.module.ViewModelFactory
import com.ib.textileecommerce.utils.SessionManager
import com.ib.textileecommerce.viewModel.SettingsViewModel
import com.ib.textileecommerce.views.SettingsView
import javax.inject.Inject


class SettingsFragment : BaseFragment<FragmentSettingsBinding>(), SettingsView {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var settingsViewModel: SettingsViewModel
    private lateinit var fragmentSettingsBinding: FragmentSettingsBinding

    override fun getViewModel() = settingsViewModel

    override fun getLayoutId() = R.layout.fragment_settings

    override fun getBindingVariable() = BR.viewModel

    lateinit var activity: DashBoardActivity
    private lateinit var layoutView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent().inject(this)
        activity = DashBoardActivity()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutView = view
        setViewModel()
        initView()
        Log.e("SettingsFragment --", "--")
    }

    companion object {
        fun getInstance() = HomeFragment().apply { }
    }

    private fun initView() {

    }

    private fun setViewModel() {
        settingsViewModel =
            ViewModelProviders.of(this, viewModelFactory)[SettingsViewModel::class.java]
        settingsViewModel.setViewInterface(this)
        fragmentSettingsBinding = bindViewData()
        fragmentSettingsBinding.viewModel = settingsViewModel
    }
}