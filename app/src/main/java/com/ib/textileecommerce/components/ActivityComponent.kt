package com.ib.textileecommerce.components

import com.ib.textileecommerce.activities.DashBoardActivity
import com.ib.textileecommerce.activities.ForgotPasswordActivity
import com.ib.textileecommerce.activities.LoginActivity
import com.ib.textileecommerce.activities.NewPasswordActivity
import com.ib.textileecommerce.activities.ProfileActivity
import com.ib.textileecommerce.activities.SignUpActivity
import com.ib.textileecommerce.activities.SplashActivity
import com.ib.textileecommerce.activities.VerificationActivity
import com.ib.textileecommerce.fragments.BagFragment
import com.ib.textileecommerce.fragments.FavouriteFragment
import com.ib.textileecommerce.fragments.HomeFragment
import com.ib.textileecommerce.fragments.OrderHistoryFragment
import com.ib.textileecommerce.fragments.SettingsFragment
import com.ib.textileecommerce.fragments.TrackYourOrderFragment
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
    fun inject(forgotPasswordActivity: ForgotPasswordActivity)
    fun inject(newPasswordActivity: NewPasswordActivity)
    fun inject(signUpActivity: SignUpActivity)
    fun inject(verificationActivity: VerificationActivity)

    // Fragments
    fun inject(homeFragment: HomeFragment)
    fun inject(bagFragment: BagFragment)
    fun inject(favouriteFragment: FavouriteFragment)
    fun inject(settingsFragment: SettingsFragment)
    fun inject(orderHistoryFragment: OrderHistoryFragment)
    fun inject(trackYourOrderFragment: TrackYourOrderFragment)
}