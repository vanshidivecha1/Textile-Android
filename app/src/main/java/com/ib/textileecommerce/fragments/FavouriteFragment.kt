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
import com.ib.textileecommerce.databinding.FragmentFavouriteBinding
import com.ib.textileecommerce.databinding.FragmentHomeBinding
import com.ib.textileecommerce.module.ViewModelFactory
import com.ib.textileecommerce.utils.SessionManager
import com.ib.textileecommerce.viewModel.FavouriteViewModel
import com.ib.textileecommerce.viewModel.HomeViewModel
import com.ib.textileecommerce.views.FavouriteView
import javax.inject.Inject


class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>(), FavouriteView {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var favouriteViewModel: FavouriteViewModel
    private lateinit var fragmentFavouriteBinding: FragmentFavouriteBinding

    override fun getViewModel() = favouriteViewModel

    override fun getLayoutId() = R.layout.fragment_favourite

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
        Log.e("FavouriteFragment --", "--")
    }

    companion object {
        fun getInstance() = HomeFragment().apply { }
    }

    private fun initView() {

    }

    private fun setViewModel() {
        favouriteViewModel =
            ViewModelProviders.of(this, viewModelFactory)[FavouriteViewModel::class.java]
        favouriteViewModel.setViewInterface(this)
        fragmentFavouriteBinding = bindViewData()
        fragmentFavouriteBinding.viewModel = favouriteViewModel
    }
}