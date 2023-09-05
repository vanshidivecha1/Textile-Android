package com.ib.textileecommerce.viewModel

import android.os.Handler
import com.ib.textileecommerce.activities.SplashActivity
import com.ib.textileecommerce.networking.NetworkService
import com.ib.textileecommerce.views.BaseView
import com.ib.textileecommerce.views.SplashView

class SplashViewModel constructor(private val networkService: NetworkService, baseView: BaseView) :
    BaseViewModel(networkService, baseView) {

    private var splashView: SplashView = baseView as SplashView

    fun nextScreen(splashActivity: SplashActivity, isLogin: Boolean) {
        Handler(splashActivity.mainLooper).postDelayed({
            if (isLogin) {
                splashView.openHomeActivity()
            } else {
                splashView.openLoginActivity()
            }
        }, 2000)
    }
}