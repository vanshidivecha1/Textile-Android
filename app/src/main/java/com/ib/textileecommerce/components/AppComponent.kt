package com.ib.textileecommerce.components

import android.content.Context
import com.ib.textileecommerce.application.TextileApplication
import com.ib.textileecommerce.module.NetworkModule
import com.ib.textileecommerce.module.ViewModule
import com.ib.textileecommerce.networking.NetworkService
import com.ib.textileecommerce.scopes.PerApplication
import com.ib.textileecommerce.utils.SessionManager
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@PerApplication
@Singleton
@Component(modules = [AndroidInjectionModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(textileApplication: TextileApplication)
    fun activityComponent(viewModule: ViewModule): ActivityComponent
    fun networkService(): NetworkService
    fun sessionManager(): SessionManager
    fun context(): Context
}