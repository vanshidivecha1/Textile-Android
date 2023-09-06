package com.ib.textileecommerce.components

import com.ib.textileecommerce.activities.DashBoardActivity
import com.ib.textileecommerce.activities.LoginActivity
import com.ib.textileecommerce.activities.ProfileActivity
import com.ib.textileecommerce.activities.SplashActivity
import com.ib.textileecommerce.fragments.BagFragment
import com.ib.textileecommerce.fragments.FavouriteFragment
import com.ib.textileecommerce.fragments.HomeFragment
import com.ib.textileecommerce.fragments.SettingsFragment
import com.ib.textileecommerce.module.ActivityModule
import com.ib.textileecommerce.module.ViewModule
import com.ib.textileecommerce.scopes.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [ViewModule::class, ActivityModule::class])
interface ActivityComponent {
    // Activities
    fun inject(splashActivity: SplashActivity)
    fun inject(loginActivity: LoginActivity)
    fun inject(dashBoardActivity: DashBoardActivity)
    fun inject(profileActivity: ProfileActivity)

    // Fragments
    fun inject(homeFragment: HomeFragment)
    fun inject(bagFragment: BagFragment)
    fun inject(favouriteFragment: FavouriteFragment)
    fun inject(settingsFragment: SettingsFragment)
}