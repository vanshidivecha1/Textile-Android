package com.ib.textileecommerce.activities

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.ib.textileecommerce.BR
import com.ib.textileecommerce.R
import com.ib.textileecommerce.databinding.ActivityVerificationBinding
import com.ib.textileecommerce.module.ViewModelFactory
import com.ib.textileecommerce.utils.SessionManager
import com.ib.textileecommerce.viewModel.VerificationViewModel
import com.ib.textileecommerce.views.VerificationView
import javax.inject.Inject

class VerificationActivity : BaseActivity<ActivityVerificationBinding>(), VerificationView {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var verificationViewModel: VerificationViewModel
    private lateinit var activityVerificationBinding: ActivityVerificationBinding

    override fun getViewModel() = verificationViewModel

    override fun getLayoutId() = R.layout.activity_verification

    override fun getBindingVariable() = BR.viewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent().inject(this)
        setViewModel()
        initView()
    }

    private fun initView() {
        activityVerificationBinding.ivBack.setOnClickListener {
            finish()
        }

        activityVerificationBinding.btnVerify.setOnClickListener {
            startActivity(Intent(this@VerificationActivity, LoginActivity::class.java))
            overridePendingTransition(R.anim.anim_screen_enter, R.anim.anim_screen_exit)
        }
    }

    private fun setViewModel() {
        verificationViewModel =
            ViewModelProviders.of(this, viewModelFactory)[VerificationViewModel::class.java]
        activityVerificationBinding = bindViewData()
        activityVerificationBinding.viewModel = verificationViewModel
    }
}