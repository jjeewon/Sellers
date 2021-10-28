package com.gomdolstudio.sellers.di.module

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gomdolstudio.covidinfoapp.di.factory.InjectingSavedStateViewModelFactory
import com.gomdolstudio.musicapp_assistedinjection.di.scope.ActivityContext
import com.gomdolstudio.musicapp_assistedinjection.di.scope.ActivityScope
import com.gomdolstudio.sellers.R
import com.gomdolstudio.sellers.databinding.ActivitySignupBinding
import com.gomdolstudio.sellers.databinding.ActivitySplashBinding
import com.gomdolstudio.sellers.ui.SplashActivity
import com.gomdolstudio.sellers.ui.signup.SignupActivity
import dagger.Module
import dagger.Provides

@Module
class SplashModule {
    @Module
    companion object {
        @JvmStatic
        @Provides
        @ActivityScope
        fun provideSplashActivityBinding(activity: SplashActivity): ActivitySplashBinding {
            return DataBindingUtil.setContentView(activity, R.layout.activity_splash)
        }
    }
}