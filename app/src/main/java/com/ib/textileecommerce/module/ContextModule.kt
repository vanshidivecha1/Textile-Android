package com.ib.textileecommerce.module

import android.content.Context
import com.ib.textileecommerce.scopes.PerApplication
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val context: Context) {
    @Provides
    @PerApplication
    fun providesContext(): Context {
        return context
    }
}