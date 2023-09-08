package com.ib.textileecommerce.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.ib.textileecommerce.BR
import com.ib.textileecommerce.R
import com.ib.textileecommerce.activities.DashBoardActivity
import com.ib.textileecommerce.adapters.FavouriteAdapter
import com.ib.textileecommerce.databinding.FragmentFavouriteBinding
import com.ib.textileecommerce.module.ViewModelFactory
import com.ib.textileecommerce.utils.SessionManager
import com.ib.textileecommerce.viewModel.FavouriteViewModel
import com.ib.textileecommerce.views.FavouriteView
import javax.inject.Inject


class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>(), FavouriteView {

    private var tag = FavouriteFragment::class.simpleName

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
    private lateinit var favouriteAdapter: FavouriteAdapter
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent().inject(this)
        activity = DashBoardActivity()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutView = view
        setViewModel()
        initView(view)
        Log.e(tag, "--")
    }

    companion object {
        fun getInstance() = FavouriteFragment().apply { }
    }

    private fun initView(view: View) {
        fragmentFavouriteBinding.rvFavourite.setHasFixedSize(true)

        progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Loading...")
        progressDialog.show()
    }

    private fun setViewModel() {
        favouriteViewModel =
            ViewModelProviders.of(this, viewModelFactory)[FavouriteViewModel::class.java]
        favouriteViewModel.setViewInterface(this)
        fragmentFavouriteBinding = getViewBinding()
        fragmentFavouriteBinding.viewModel = favouriteViewModel
    }
}