package com.ib.textileecommerce.viewModel

import com.ib.textileecommerce.networking.NetworkService
import com.ib.textileecommerce.views.AddAddressView
import com.ib.textileecommerce.views.BagView
import com.ib.textileecommerce.views.BaseView

class AddAddressViewModel constructor(
    private val networkService: NetworkService,
    baseView: BaseView
) : BaseViewModel(networkService, baseView) {

    private var bagView: AddAddressView = baseView as AddAddressView

}