package com.ib.textileecommerce.module

import com.ib.textileecommerce.networking.NetworkService
import com.ib.textileecommerce.scopes.PerActivity
import com.ib.textileecommerce.views.BaseView
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    @PerActivity
    fun getViewModuleFactory(networkService: NetworkService, baseView: BaseView) =
        ViewModelFactory(networkService, baseView)
}