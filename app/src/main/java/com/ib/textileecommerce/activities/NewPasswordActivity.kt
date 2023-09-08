package com.ib.textileecommerce.activities

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.ib.textileecommerce.BR
import com.ib.textileecommerce.R
import com.ib.textileecommerce.databinding.ActivityNewPasswordBinding
import com.ib.textileecommerce.module.ViewModelFactory
import com.ib.textileecommerce.utils.SessionManager
import com.ib.textileecommerce.viewModel.NewPasswordViewModel
import com.ib.textileecommerce.views.NewPasswordView
import javax.inject.Inject

class NewPasswordActivity : BaseActivity<ActivityNewPasswordBinding>(), NewPasswordView {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var newPasswordViewModel: NewPasswordViewModel
    private lateinit var activityNewPasswordBinding: ActivityNewPasswordBinding

    override fun getViewModel() = newPasswordViewModel

    override fun getLayoutId() = R.layout.activity_new_password

    override fun getBindingVariable() = BR.viewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent().inject(this)
        setViewModel()
        initView()
    }

    private fun initView() {
    }

    private fun setViewModel() {
        newPasswordViewModel =
            ViewModelProviders.of(this, viewModelFactory)[NewPasswordViewModel::class.java]
        activityNewPasswordBinding = bindViewData()
        activityNewPasswordBinding.viewModel = newPasswordViewModel
    }
}