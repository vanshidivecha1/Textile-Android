package com.ib.textileecommerce.activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.lifecycle.ViewModelProviders
import com.ib.textileecommerce.BR
import com.ib.textileecommerce.R
import com.ib.textileecommerce.databinding.ActivityLoginBinding
import com.ib.textileecommerce.module.ViewModelFactory
import com.ib.textileecommerce.utils.SessionManager
import com.ib.textileecommerce.viewModel.LoginViewModel
import com.ib.textileecommerce.views.LoginView
import javax.inject.Inject

class LoginActivity : BaseActivity<ActivityLoginBinding>(), LoginView {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var activityLoginBinding: ActivityLoginBinding

    override fun getViewModel() = loginViewModel

    override fun getLayoutId() = R.layout.activity_login

    override fun getBindingVariable() = BR.viewModel

    private var isValidate = false
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        getActivityComponent().inject(this)
        setViewModel()
        initView()
    }

    private fun initView() {
        activityLoginBinding.btnSignIn.setOnClickListener {
            isValidate = checkAllFields()

            startActivity(Intent(this@LoginActivity, DashBoardActivity::class.java))
            overridePendingTransition(R.anim.anim_screen_enter, R.anim.anim_screen_exit)
            finish()
            /*if (isValidate) {

                val email = activityLoginBinding.etEmail.text.toString()
                val password = activityLoginBinding.etPassword.text.toString()

                progressDialog = ProgressDialog(this)
                progressDialog.setMessage("Logging In...")
                progressDialog.show()

                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                overridePendingTransition(R.anim.anim_screen_enter, R.anim.anim_screen_exit)
                finish()
            }*/
        }

        activityLoginBinding.llSignUpForFree.setOnClickListener {

        }
    }

    private fun setViewModel() {
        loginViewModel =
            ViewModelProviders.of(this, viewModelFactory)[LoginViewModel::class.java]
        activityLoginBinding = bindViewData()
        activityLoginBinding.viewModel = loginViewModel
    }

    private fun checkAllFields(): Boolean {
        if (activityLoginBinding.etEmail.length() == 0) {
            activityLoginBinding.etEmail.error = "Email is required"
            activityLoginBinding.etEmail.requestFocus()
            return false
        }
        if (activityLoginBinding.etPassword.length() == 0) {
            activityLoginBinding.etPassword.error = "Password is required"
            activityLoginBinding.etPassword.requestFocus()
            return false
        }
        return true
    }
}