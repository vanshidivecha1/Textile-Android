package com.ib.textileecommerce.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ib.textileecommerce.BR
import com.ib.textileecommerce.R
import com.ib.textileecommerce.application.TextileApplication
import com.ib.textileecommerce.databinding.ActivityDashboardBinding
import com.ib.textileecommerce.fragments.HomeFragment
import com.ib.textileecommerce.module.ViewModelFactory
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
    private val homeFragment = HomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent().inject(this)
        setViewModel()
        initView()

        loadFragment(homeFragment)
        /*val bagFragment = BagFragment()
        val favouriteFragment = FavouriteFragment(this)
        val settingsFragment = SettingsFragment()*/

        setBottomView()
    }

    private val navListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val fragment: Fragment
            when (item.itemId) {
                R.id.nav_home -> {
                    fragment = homeFragment
                    loadFragment(fragment)
                    Log.e(tag, "Home Selected")
                }
            }

            true
        }

    private fun setBottomView() {
        activityDashBoardBinding.bottomNavigation.itemIconTintList = null
        activityDashBoardBinding.bottomNavigation.setOnNavigationItemSelectedListener(navListener)

        activityDashBoardBinding.bottomNavigation.background = null
        activityDashBoardBinding.bottomNavigation.menu.getItem(2).isEnabled = false

        /*activityDashBoardBinding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    setCurrentFragment(homeFragment)
                    Log.e(tag, "Home Selected")
                }

               *//* R.id.nav_bag -> {
                    setCurrentFragment(bagfragments)
                    Log.i(TAG, "My Bag Selected")
                }

                R.id.nav_favourite -> {
                    setCurrentFragment(favouritefragments)
                    Log.i(TAG, "Favourite Selected")
                }

                R.id.nav_settings -> {
                    setCurrentFragment(settingsfragments)
                    Log.i(TAG, "Settings Selected")
                }*//*
            }
            true
        }*/
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
}