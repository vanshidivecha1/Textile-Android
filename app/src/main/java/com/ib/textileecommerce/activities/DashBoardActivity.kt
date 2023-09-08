package com.ib.textileecommerce.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.ib.textileecommerce.BR
import com.ib.textileecommerce.R
import com.ib.textileecommerce.application.TextileApplication
import com.ib.textileecommerce.databinding.ActivityDashboardBinding
import com.ib.textileecommerce.fragments.BagFragment
import com.ib.textileecommerce.fragments.ChangePasswordFragment
import com.ib.textileecommerce.fragments.FavouriteFragment
import com.ib.textileecommerce.fragments.GiveFeedbackFragment
import com.ib.textileecommerce.fragments.HomeFragment
import com.ib.textileecommerce.fragments.ManageAddressFragment
import com.ib.textileecommerce.fragments.OrderHistoryFragment
import com.ib.textileecommerce.fragments.SettingsFragment
import com.ib.textileecommerce.fragments.TermsAndConditionFragment
import com.ib.textileecommerce.fragments.TrackYourOrderFragment
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

        activityDashBoardBinding.bottomNavigation.itemIconTintList = null
        setHomeToolBar()
        setBottomView()
        addFragment(HomeFragment.getInstance(), Constants.HOME_FRAGMENT)
    }

    private fun setBottomView() {
        activityDashBoardBinding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    setHomeToolBar()
                    addFragment(HomeFragment.getInstance(), Constants.HOME_FRAGMENT)
                    Log.e(tag, "Home Selected")
                }

                R.id.nav_bag -> {
                    setBagToolBar()
                    addFragment(BagFragment.getInstance(), Constants.BAG_FRAGMENT)
                    Log.e(tag, "My Bag Selected")
                }

                R.id.nav_favourite -> {
                    setFavouriteToolBar()
                    addFragment(FavouriteFragment.getInstance(), Constants.FAVOURITE_FRAGMENT)
                    Log.e(tag, "Favourite Selected")
                }

                R.id.nav_settings -> {
                    setSettingsToolBar()
                    addFragment(
                        SettingsFragment.getInstance(
                            activityDashBoardBinding.tvTitle,
                            activityDashBoardBinding.ivBack
                        ), Constants.SETTINGS_FRAGMENT
                    )
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

    private fun setHomeToolBar() {
        activityDashBoardBinding.layoutHomeTopBar.visibility = View.VISIBLE
        activityDashBoardBinding.layoutTopBar.visibility = View.GONE
        activityDashBoardBinding.bottomNavigation.menu.findItem(R.id.nav_home).isChecked = true
    }

    private fun setBagToolBar() {
        activityDashBoardBinding.layoutHomeTopBar.visibility = View.GONE
        activityDashBoardBinding.layoutTopBar.visibility = View.VISIBLE
        activityDashBoardBinding.tvTitle.text = "Shopping"
        activityDashBoardBinding.ivBack.setOnClickListener {
            onBackPressed()
        }
        activityDashBoardBinding.bottomNavigation.menu.findItem(R.id.nav_bag).isChecked = true
    }

    private fun setFavouriteToolBar() {
        activityDashBoardBinding.layoutHomeTopBar.visibility = View.GONE
        activityDashBoardBinding.layoutTopBar.visibility = View.VISIBLE
        activityDashBoardBinding.tvTitle.text = "Favourite"
        activityDashBoardBinding.ivBack.setOnClickListener {
            onBackPressed()
        }
        activityDashBoardBinding.bottomNavigation.menu.findItem(R.id.nav_favourite).isChecked = true
    }

    private fun setSettingsToolBar() {
        activityDashBoardBinding.layoutHomeTopBar.visibility = View.GONE
        activityDashBoardBinding.layoutTopBar.visibility = View.VISIBLE
        activityDashBoardBinding.tvTitle.text = "Settings"
        activityDashBoardBinding.ivBack.setOnClickListener {
            onBackPressed()
        }
        activityDashBoardBinding.bottomNavigation.menu.findItem(R.id.nav_settings).isChecked = true
    }

    private fun loadFragment(fragment: Fragment, tag: String): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frameContainer, fragment, tag)
                addToBackStack(Constants.HOME_FRAGMENT)
                commit()
            }
            return true
        }
        return false
    }

    override fun onBackPressed() {
        val manager: FragmentManager = supportFragmentManager
        val backStackCount = manager.backStackEntryCount
        if (backStackCount <= 1) {
            finishAffinity()
        } else {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.frameContainer)
            Log.e(
                tag,
                "Current Fragment == $currentFragment \nCurrentFragment Tag == ${currentFragment!!.tag}"
            )

            if (currentFragment is HomeFragment && currentFragment.tag == Constants.HOME_FRAGMENT) {
                finishAffinity()
            } else if (currentFragment.tag == Constants.BAG_FRAGMENT ||
                currentFragment.tag == Constants.FAVOURITE_FRAGMENT ||
                currentFragment.tag == Constants.SETTINGS_FRAGMENT
            ) {
                setHomeToolBar()
                addFragment(HomeFragment.getInstance(), Constants.HOME_FRAGMENT)
            } else if (currentFragment is OrderHistoryFragment && currentFragment.tag == Constants.ORDER_HISTORY ||
                currentFragment is TrackYourOrderFragment && currentFragment.tag == Constants.TRACK_YOUR_ORDER ||
                currentFragment is ManageAddressFragment && currentFragment.tag == Constants.MANAGE_ADDRESS ||
                currentFragment is ChangePasswordFragment && currentFragment.tag == Constants.CHANGE_PASSWORD ||
                currentFragment is GiveFeedbackFragment && currentFragment.tag == Constants.GIVE_FEEDBACK ||
                currentFragment is TermsAndConditionFragment && currentFragment.tag == Constants.TERMS_AND_CONDITION
            ) {
                setSettingsToolBar()
                addFragment(
                    SettingsFragment.getInstance(
                        activityDashBoardBinding.tvTitle,
                        activityDashBoardBinding.ivBack
                    ), Constants.SETTINGS_FRAGMENT
                )
            }
        }
    }
}