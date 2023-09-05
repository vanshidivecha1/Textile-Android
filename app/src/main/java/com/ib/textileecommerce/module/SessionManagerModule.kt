package com.ib.textileecommerce.module

import android.content.Context
import com.ib.textileecommerce.scopes.PerApplication
import com.ib.textileecommerce.utils.SessionManager
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModule::class])
class SessionManagerModule {

    @Provides
    @PerApplication
    fun provideSessionManager(context: Context) = SessionManager(context)
}