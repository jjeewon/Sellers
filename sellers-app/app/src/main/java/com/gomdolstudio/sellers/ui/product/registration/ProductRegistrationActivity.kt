package com.gomdolstudio.sellers.ui.product.registration

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.gomdolstudio.sellers.databinding.ActivityProductmainBinding
import com.gomdolstudio.sellers.databinding.ActivityProductregistrationBinding
import com.gomdolstudio.sellers.ui.product.ProductMainViewModel
import com.gomdolstudio.sellers.ui.signup.SignupActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ProductRegistrationActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var binding: ActivityProductregistrationBinding
    @Inject
    lateinit var viewModelProvider: ViewModelProvider
    private lateinit var productRegistraionViewModel: ProductRegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productRegistraionViewModel = viewModelProvider.get(ProductRegistrationViewModel::class.java)
        binding.lifecycleOwner = this
        binding.productRegistrationViewModel = productRegistraionViewModel

    }
}