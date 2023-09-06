package com.ib.textileecommerce.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ib.textileecommerce.BR
import com.ib.textileecommerce.R
import com.ib.textileecommerce.application.TextileApplication
import com.ib.textileecommerce.databinding.ActivityDashboardBinding
import com.ib.textileecommerce.fragments.BagFragment
import com.ib.textileecommerce.fragments.FavouriteFragment
import com.ib.textileecommerce.fragments.HomeFragment
import com.ib.textileecommerce.fragments.SettingsFragment
import com.ib.textileecommerce.module.ViewModelFactory
import com.ib.textileecommerce.utils.Constants
import com.ib.textileecommerce.utils.SessionManager
import com.ib.textileecommerce.viewModel.DashBoardViewModel
import com.ib.textileecommerce.views.DashBoardView
import javax.inject.Inject

class DashBoardActivity : BaseActivity<ActivityDashboardBinding>(), DashBoardView {

    var tag = DashBoardActivity::class.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var dashBoardViewModel: DashBoardViewModel
    private lateinit var activityDashBoardBinding: ActivityDashboardBinding

    override fun getViewModel() = dashBoardViewModel

    override fun getLayoutId() = R.layout.activity_dashboard

    override fun getBindingVariable() = BR.viewModel

    var context = TextileApplication.getContext()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent().inject(this)
        setViewModel()
        initView()

        setHomeToolBar()
        addFragment(HomeFragment.getInstance(), Constants.HOME_FRAGMENT)
        setBottomView()
        /*val bagFragment = BagFragment()
        val favouriteFragment = FavouriteFragment(this)
        val settingsFragment = SettingsFragment()*/

    }

    private fun setBottomView() {
        activityDashBoardBinding.bottomNavigation.itemIconTintList = null
        activityDashBoardBinding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    addFragment(HomeFragment.getInstance(), Constants.HOME_FRAGMENT)
                    setHomeToolBar()
                    Log.e(tag, "Home Selected")
                }

                R.id.nav_bag -> {
                    loadFragment(BagFragment.getInstance())
                    setBagToolBar()
                    Log.e(tag, "My Bag Selected")
                }

                R.id.nav_favourite -> {
                    loadFragment(FavouriteFragment.getInstance())
                    setFavouriteToolBar()
                    Log.e(tag, "Favourite Selected")
                }

                R.id.nav_settings -> {
                    loadFragment(SettingsFragment.getInstance())
                    setSettingsToolBar()
                    Log.e(tag, "Settings Selected")
                }
            }
            true
        }
    }

    private fun initView() {
        activityDashBoardBinding.ivProfile.setOnClickListener {
            overridePendingTransition(0, 0)
            startActivity(Intent(context, ProfileActivity::class.java))
        }
    }

    private fun setViewModel() {
        dashBoardViewModel =
            ViewModelProviders.of(this, viewModelFactory)[DashBoardViewModel::class.java]
        activityDashBoardBinding = bindViewData()
        activityDashBoardBinding.viewModel = dashBoardViewModel
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frameContainer, fragment)
                addToBackStack("fragHome")
                commit()
            }
            return true
        }
        return false
    }

    private fun setHomeToolBar() {
        activityDashBoardBinding.layoutHomeTopBar.visibility = View.VISIBLE
        activityDashBoardBinding.layoutTopBar.visibility = View.GONE
        activityDashBoardBinding.bottomNavigation.menu.findItem(R.id.nav_home).isChecked = true
    }

    private fun setBagToolBar() {
        activityDashBoardBinding.layoutHomeTopBar.visibility = View.GONE
        activityDashBoardBinding.layoutTopBar.visibility = View.VISIBLE
        activityDashBoardBinding.tvTitle.text = "Shopping"
        activityDashBoardBinding.bottomNavigation.menu.findItem(R.id.nav_bag).isChecked = true
    }

    private fun setFavouriteToolBar() {
        activityDashBoardBinding.layoutHomeTopBar.visibility = View.GONE
        activityDashBoardBinding.layoutTopBar.visibility = View.VISIBLE
        activityDashBoardBinding.tvTitle.text = "Favourite"
        activityDashBoardBinding.bottomNavigation.menu.findItem(R.id.nav_favourite).isChecked = true
    }

    private fun setSettingsToolBar() {
        activityDashBoardBinding.layoutHomeTopBar.visibility = View.GONE
        activityDashBoardBinding.layoutTopBar.visibility = View.VISIBLE
        activityDashBoardBinding.tvTitle.text = "Settings"
        activityDashBoardBinding.bottomNavigation.menu.findItem(R.id.nav_settings).isChecked = true
    }
}