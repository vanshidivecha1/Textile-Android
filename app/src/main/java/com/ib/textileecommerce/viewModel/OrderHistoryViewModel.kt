package com.ib.textileecommerce.viewModel

import com.ib.textileecommerce.networking.NetworkService
import com.ib.textileecommerce.views.BagView
import com.ib.textileecommerce.views.BaseView
import com.ib.textileecommerce.views.OrderHistoryView

class OrderHistoryViewModel constructor(
    private val networkService: NetworkService,
    baseView: BaseView
) : BaseViewModel(networkService, baseView) {

    private var bagView: OrderHistoryView = baseView as OrderHistoryView

}