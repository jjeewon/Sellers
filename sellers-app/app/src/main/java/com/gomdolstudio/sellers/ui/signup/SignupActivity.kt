package com.gomdolstudio.sellers.ui.signup

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gomdolstudio.sellers.databinding.ActivitySignupBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SignupActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var binding: ActivitySignupBinding
    @Inject
    lateinit var viewModelProvider: ViewModelProvider
    private lateinit var signupViewModel: SignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupViewModel = viewModelProvider.get(SignupViewModel::class.java)
        binding.lifecycleOwner = this
        binding.signupViewModel = signupViewModel

        signupViewModel.getShowToast().observe(this,
            Observer {text -> Toast.makeText(this, text, Toast.LENGTH_LONG).show()})


    }
}