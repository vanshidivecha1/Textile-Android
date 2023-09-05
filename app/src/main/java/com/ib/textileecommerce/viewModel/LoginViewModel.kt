package com.ib.textileecommerce.viewModel

import com.ib.textileecommerce.networking.NetworkService
import com.ib.textileecommerce.views.BaseView
import com.ib.textileecommerce.views.LoginView

class LoginViewModel constructor(private val networkService: NetworkService, baseView: BaseView) :
    BaseViewModel(networkService, baseView) {

    private var loginView: LoginView = baseView as LoginView

}