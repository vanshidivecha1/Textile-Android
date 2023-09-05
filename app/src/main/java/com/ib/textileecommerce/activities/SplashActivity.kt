package com.ib.textileecommerce.activities

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.lifecycle.ViewModelProviders
import com.ib.textileecommerce.BR
import com.ib.textileecommerce.R
import com.ib.textileecommerce.databinding.ActivitySplashBinding
import com.ib.textileecommerce.module.ViewModelFactory
import com.ib.textileecommerce.utils.SessionManager
import com.ib.textileecommerce.viewModel.SplashViewModel
import com.ib.textileecommerce.views.SplashView
import javax.inject.Inject

class SplashActivity : BaseActivity<ActivitySplashBinding>(), SplashView {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var splashViewModel: SplashViewModel
    private lateinit var activitySplashBinding: ActivitySplashBinding

    override fun getViewModel() = splashViewModel

    override fun getLayoutId() = R.layout.activity_splash

    override fun getBindingVariable() = BR.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent().inject(this)
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setViewModel()
    }

    /**
     * set View model
     */
    private fun setViewModel() {
        splashViewModel =
            ViewModelProviders.of(this, viewModelFactory)[SplashViewModel::class.java]
        activitySplashBinding = bindViewData()
        activitySplashBinding.viewModel = splashViewModel

        splashViewModel.nextScreen(this@SplashActivity, sessionManager.isLoginUser())
    }

    override fun openLoginActivity() {
        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        overridePendingTransition(R.anim.anim_screen_enter, R.anim.anim_screen_exit)
        finish()
    }

    override fun openHomeActivity() {
    }
}