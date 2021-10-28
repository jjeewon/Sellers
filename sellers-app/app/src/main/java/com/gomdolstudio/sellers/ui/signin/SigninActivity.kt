package com.gomdolstudio.sellers.ui.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gomdolstudio.sellers.databinding.ActivitySigninBinding
import com.gomdolstudio.sellers.databinding.ActivitySignupBinding
import com.gomdolstudio.sellers.ui.product.ProductMainActivity
import com.gomdolstudio.sellers.ui.signup.SignupActivity
import com.gomdolstudio.sellers.ui.signup.SignupViewModel
import dagger.android.support.DaggerAppCompatActivity

import javax.inject.Inject

class SigninActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var binding: ActivitySigninBinding
    @Inject
    lateinit var viewModelProvider: ViewModelProvider
    private lateinit var signinViewModel: SigninViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signinViewModel = viewModelProvider.get(SigninViewModel::class.java)
        binding.lifecycleOwner = this
        binding.signinViewModel = signinViewModel

        signinViewModel.getShowToast().observe(this,
                Observer {text -> Toast.makeText(this, text, Toast.LENGTH_LONG).show()})
        signinViewModel.getSignupClickEvent().observe(this,
                Observer {
                    startActivity(Intent(this@SigninActivity, SignupActivity::class.java))
                    finish()})
        signinViewModel.getMoveToProductMainEvent().observe(this,
            Observer {
                startActivity(Intent(this@SigninActivity, ProductMainActivity::class.java))
                finish()})

    }
}