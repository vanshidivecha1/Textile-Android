package com.ib.textileecommerce.application

import android.app.Activity
import android.app.Application
import com.ib.textileecommerce.components.AppComponent
import com.ib.textileecommerce.components.DaggerAppComponent
import com.ib.textileecommerce.module.ContextModule
import com.ib.textileecommerce.module.NetworkModule
import com.ib.textileecommerce.utils.InternetConnectionMonitor
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TextileApplication: Application(), HasActivityInjector {

    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    companion object {
        private lateinit var appComponent: AppComponent

        fun appComponent() = appComponent

        fun getContext() = appComponent.context()
    }

    override fun onCreate() {
        super.onCreate()
        buildAppComponent()
        InternetConnectionMonitor(this).enable()
    }

    private fun buildAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .networkModule(NetworkModule())
            .build()
    }
}