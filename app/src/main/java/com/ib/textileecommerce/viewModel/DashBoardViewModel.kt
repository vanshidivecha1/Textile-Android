package com.ib.textileecommerce.viewModel

import com.ib.textileecommerce.networking.NetworkService
import com.ib.textileecommerce.views.BaseView
import com.ib.textileecommerce.views.DashBoardView

class DashBoardViewModel constructor(
    private val networkService: NetworkService,
    baseView: BaseView
) :
    BaseViewModel(networkService, baseView) {

    private var dashBoardView: DashBoardView = baseView as DashBoardView

}