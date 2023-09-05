package com.ib.textileecommerce.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.ib.textileecommerce.BR
import com.ib.textileecommerce.R
import com.ib.textileecommerce.application.TextileApplication
import com.ib.textileecommerce.databinding.ActivityProfileBinding
import com.ib.textileecommerce.module.ViewModelFactory
import com.ib.textileecommerce.utils.SessionManager
import com.ib.textileecommerce.viewModel.ProfileViewModel
import com.ib.textileecommerce.views.ProfileView
import javax.inject.Inject

class ProfileActivity : BaseActivity<ActivityProfileBinding>(), ProfileView {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var activityProfileBinding: ActivityProfileBinding

    override fun getViewModel() = profileViewModel

    override fun getLayoutId() = R.layout.activity_profile

    override fun getBindingVariable() = BR.viewModel

    var context = TextileApplication.getContext()
    private val genderList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent().inject(this)
        setViewModel()
        initView()
    }

    private fun initView() {
        genderList.add("Male")
        genderList.add("Female")

        if (activityProfileBinding.genderSpinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, genderList
            )
            activityProfileBinding.genderSpinner.adapter = adapter

            activityProfileBinding.genderSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@ProfileActivity,
                        genderList[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        activityProfileBinding.ivBack.setOnClickListener {
            finish()
        }

        activityProfileBinding.btnSubmit.setOnClickListener {
            finish()
        }
    }

    private fun setViewModel() {
        profileViewModel =
            ViewModelProviders.of(this, viewModelFactory)[ProfileViewModel::class.java]
        activityProfileBinding = bindViewData()
        activityProfileBinding.viewModel = profileViewModel
    }
}