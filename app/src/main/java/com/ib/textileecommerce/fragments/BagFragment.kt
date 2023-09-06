package com.ib.textileecommerce.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.ib.textileecommerce.BR
import com.ib.textileecommerce.R
import com.ib.textileecommerce.activities.DashBoardActivity
import com.ib.textileecommerce.databinding.FragmentBagBinding
import com.ib.textileecommerce.databinding.FragmentHomeBinding
import com.ib.textileecommerce.module.ViewModelFactory
import com.ib.textileecommerce.utils.SessionManager
import com.ib.textileecommerce.viewModel.BagViewModel
import com.ib.textileecommerce.views.BagView
import javax.inject.Inject


class BagFragment : BaseFragment<FragmentBagBinding>(), BagView {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var bagViewModel: BagViewModel
    private lateinit var activityBagBinding: FragmentBagBinding

    override fun getViewModel() = bagViewModel

    override fun getLayoutId() = R.layout.fragment_bag

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
    }

    companion object {
        fun getInstance() = BagFragment().apply { }
    }

    private fun initView() {
        setViewModel()
        initView()
        Log.e("BagFragment --", "--")
    }

    private fun setViewModel() {
        bagViewModel =
            ViewModelProviders.of(this, viewModelFactory)[BagViewModel::class.java]
        bagViewModel.setViewInterface(this)
        activityBagBinding = bindViewData()
        activityBagBinding.viewModel = bagViewModel
    }
}