package com.ib.textileecommerce.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.ib.textileecommerce.BR
import com.ib.textileecommerce.R
import com.ib.textileecommerce.activities.DashBoardActivity
import com.ib.textileecommerce.application.TextileApplication
import com.ib.textileecommerce.databinding.FragmentHomeBinding
import com.ib.textileecommerce.module.ViewModelFactory
import com.ib.textileecommerce.utils.SessionManager
import com.ib.textileecommerce.viewModel.HomeViewModel
import com.ib.textileecommerce.views.HomeView
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeView {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var activityHomeBinding: FragmentHomeBinding

    override fun getViewModel() = homeViewModel

    override fun getLayoutId() = R.layout.fragment_home

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
    }

    private fun initView() {

    }

    private fun setViewModel() {
        homeViewModel =
            ViewModelProviders.of(this, viewModelFactory)[HomeViewModel::class.java]
        homeViewModel.setViewInterface(this)
        activityHomeBinding = getViewBinding()
        activityHomeBinding.viewModel = homeViewModel
    }

}