package com.ib.textileecommerce.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ib.textileecommerce.networking.NetworkService
import com.ib.textileecommerce.viewModel.BaseViewModel
import com.ib.textileecommerce.viewModel.DashBoardViewModel
import com.ib.textileecommerce.viewModel.HomeViewModel
import com.ib.textileecommerce.viewModel.LoginViewModel
import com.ib.textileecommerce.viewModel.ProfileViewModel
import com.ib.textileecommerce.viewModel.SplashViewModel
import com.ib.textileecommerce.views.BaseView
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val networkService: NetworkService,
    private val baseView: BaseView
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> SplashViewModel(
                networkService, baseView
            ) as T

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(
                networkService, baseView
            ) as T

            modelClass.isAssignableFrom(DashBoardViewModel::class.java) -> DashBoardViewModel(
                networkService, baseView
            ) as T

            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(
                networkService, baseView
            ) as T

            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(
                networkService, baseView
            ) as T

            else -> BaseViewModel(networkService, baseView) as T
        }
    }
}