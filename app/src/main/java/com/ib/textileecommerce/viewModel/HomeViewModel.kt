package com.ib.textileecommerce.viewModel

import com.ib.textileecommerce.networking.NetworkService
import com.ib.textileecommerce.views.BaseView
import com.ib.textileecommerce.views.DashBoardView
import com.ib.textileecommerce.views.HomeView

class HomeViewModel constructor(
    private val networkService: NetworkService,
    baseView: BaseView
) : BaseViewModel(networkService, baseView) {

    private var homeView: HomeView = baseView as HomeView

}