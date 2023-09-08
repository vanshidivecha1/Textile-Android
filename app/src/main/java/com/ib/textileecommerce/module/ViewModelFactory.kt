package com.ib.textileecommerce.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ib.textileecommerce.networking.NetworkService
import com.ib.textileecommerce.viewModel.BagViewModel
import com.ib.textileecommerce.viewModel.BaseViewModel
import com.ib.textileecommerce.viewModel.DashBoardViewModel
import com.ib.textileecommerce.viewModel.FavouriteViewModel
import com.ib.textileecommerce.viewModel.ForgotPasswordViewModel
import com.ib.textileecommerce.viewModel.HomeViewModel
import com.ib.textileecommerce.viewModel.LoginViewModel
import com.ib.textileecommerce.viewModel.NewPasswordViewModel
import com.ib.textileecommerce.viewModel.OrderHistoryViewModel
import com.ib.textileecommerce.viewModel.ProfileViewModel
import com.ib.textileecommerce.viewModel.SettingsViewModel
import com.ib.textileecommerce.viewModel.SignUpViewModel
import com.ib.textileecommerce.viewModel.SplashViewModel
import com.ib.textileecommerce.viewModel.VerificationViewModel
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

            modelClass.isAssignableFrom(BagViewModel::class.java) -> BagViewModel(
                networkService, baseView
            ) as T

            modelClass.isAssignableFrom(FavouriteViewModel::class.java) -> FavouriteViewModel(
                networkService, baseView
            ) as T

            modelClass.isAssignableFrom(SettingsViewModel::class.java) -> SettingsViewModel(
                networkService, baseView
            ) as T

            modelClass.isAssignableFrom(ForgotPasswordViewModel::class.java) -> ForgotPasswordViewModel(
                networkService, baseView
            ) as T

            modelClass.isAssignableFrom(NewPasswordViewModel::class.java) -> NewPasswordViewModel(
                networkService, baseView
            ) as T

            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> SignUpViewModel(
                networkService, baseView
            ) as T

            modelClass.isAssignableFrom(VerificationViewModel::class.java) -> VerificationViewModel(
                networkService, baseView
            ) as T

            modelClass.isAssignableFrom(OrderHistoryViewModel::class.java) -> OrderHistoryViewModel(
                networkService, baseView
            ) as T

            else -> BaseViewModel(networkService, baseView) as T
        }
    }
}