package com.gomdolstudio.sellers.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.gomdolstudio.musicapp_assistedinjection.di.scope.ActivityContext
import com.gomdolstudio.sellers.databinding.ActivitySplashBinding
import com.gomdolstudio.sellers.ui.signin.SigninActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashActivity: DaggerAppCompatActivity() {
    @Inject
    lateinit var binding: ActivitySplashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this

        GlobalScope.launch {
            delay(1000)
            startActivity(Intent(this@SplashActivity, SigninActivity::class.java))
            finish()
        }
    }
}