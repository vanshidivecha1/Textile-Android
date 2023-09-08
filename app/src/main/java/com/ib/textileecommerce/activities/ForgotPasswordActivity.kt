package com.ib.textileecommerce.activities

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.ib.textileecommerce.BR
import com.ib.textileecommerce.R
import com.ib.textileecommerce.databinding.ActivityForgotPasswordBinding
import com.ib.textileecommerce.module.ViewModelFactory
import com.ib.textileecommerce.utils.SessionManager
import com.ib.textileecommerce.viewModel.ForgotPasswordViewModel
import com.ib.textileecommerce.views.ForgotPasswordView
import javax.inject.Inject

class ForgotPasswordActivity : BaseActivity<ActivityForgotPasswordBinding>(), ForgotPasswordView {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var forgotPasswordViewModel: ForgotPasswordViewModel
    private lateinit var activityForgotPasswordBinding: ActivityForgotPasswordBinding

    override fun getViewModel() = forgotPasswordViewModel

    override fun getLayoutId() = R.layout.activity_forgot_password

    override fun getBindingVariable() = BR.viewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent().inject(this)
        setViewModel()
        initView()
    }

    private fun initView() {

        activityForgotPasswordBinding.ivBack.setOnClickListener {
            finish()
        }

        activityForgotPasswordBinding.tvSignIn.setOnClickListener {
            finish()
        }

        activityForgotPasswordBinding.btnSend.setOnClickListener {
            startActivity(Intent(this@ForgotPasswordActivity, VerificationActivity::class.java))
            overridePendingTransition(R.anim.anim_screen_enter, R.anim.anim_screen_exit)
        }
    }

    private fun setViewModel() {
        forgotPasswordViewModel =
            ViewModelProviders.of(this, viewModelFactory)[ForgotPasswordViewModel::class.java]
        activityForgotPasswordBinding = bindViewData()
        activityForgotPasswordBinding.viewModel = forgotPasswordViewModel
    }
}