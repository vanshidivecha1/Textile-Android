package com.ib.textileecommerce.module

import com.ib.textileecommerce.scopes.PerActivity
import com.ib.textileecommerce.views.BaseView
import dagger.Module
import dagger.Provides

@Module
class ViewModule(private val view: BaseView) {
    @PerActivity
    @Provides
    fun provideView(): BaseView {
        return view
    }
}