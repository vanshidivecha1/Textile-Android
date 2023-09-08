package com.ib.textileecommerce.activities

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.ib.textileecommerce.BR
import com.ib.textileecommerce.R
import com.ib.textileecommerce.databinding.ActivitySignUpBinding
import com.ib.textileecommerce.module.ViewModelFactory
import com.ib.textileecommerce.utils.SessionManager
import com.ib.textileecommerce.viewModel.SignUpViewModel
import com.ib.textileecommerce.views.SignUpView
import javax.inject.Inject

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(), SignUpView {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var activitySignUpBinding: ActivitySignUpBinding

    override fun getViewModel() = signUpViewModel

    override fun getLayoutId() = R.layout.activity_sign_up

    override fun getBindingVariable() = BR.viewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent().inject(this)
        setViewModel()
        initView()
    }

    private fun initView() {
        activitySignUpBinding.btnSignIn.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, VerificationActivity::class.java))
            overridePendingTransition(R.anim.anim_screen_enter, R.anim.anim_screen_exit)
        }

        activitySignUpBinding.llSignIn.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
            overridePendingTransition(R.anim.anim_screen_enter, R.anim.anim_screen_exit)
        }
    }

    private fun setViewModel() {
        signUpViewModel =
            ViewModelProviders.of(this, viewModelFactory)[SignUpViewModel::class.java]
        activitySignUpBinding = bindViewData()
        activitySignUpBinding.viewModel = signUpViewModel
    }
}