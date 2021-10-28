package com.gomdolstudio.sellers.ui.signin

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gomdolstudio.sellers.databinding.ActivitySigninBinding
import com.gomdolstudio.sellers.databinding.ActivitySignupBinding
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

    }
}