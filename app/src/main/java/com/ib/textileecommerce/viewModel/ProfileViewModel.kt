package com.ib.textileecommerce.viewModel

import com.ib.textileecommerce.networking.NetworkService
import com.ib.textileecommerce.views.BaseView
import com.ib.textileecommerce.views.ProfileView

class ProfileViewModel constructor(
    private val networkService: NetworkService,
    baseView: BaseView
) : BaseViewModel(networkService, baseView) {

    private var profileView: ProfileView = baseView as ProfileView

}