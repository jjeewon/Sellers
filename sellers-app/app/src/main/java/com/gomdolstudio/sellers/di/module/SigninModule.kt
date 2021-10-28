package com.gomdolstudio.sellers.di.module

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gomdolstudio.covidinfoapp.di.factory.InjectingSavedStateViewModelFactory
import com.gomdolstudio.musicapp_assistedinjection.di.scope.ActivityContext
import com.gomdolstudio.musicapp_assistedinjection.di.scope.ActivityScope
import com.gomdolstudio.sellers.R
import com.gomdolstudio.sellers.databinding.ActivitySigninBinding
import com.gomdolstudio.sellers.databinding.ActivitySignupBinding
import com.gomdolstudio.sellers.ui.signin.SigninActivity
import com.gomdolstudio.sellers.ui.signup.SignupActivity
import dagger.Module
import dagger.Provides

@Module
class SigninModule {
    @Module
    companion object{
        @JvmStatic
        @Provides
        @ActivityScope
        fun provideSignupActivityBinding(activity: SigninActivity): ActivitySigninBinding {
            return DataBindingUtil.setContentView(activity, R.layout.activity_signin)
        }
        @JvmStatic
        @Provides
        @ActivityScope
        fun provideViewModelProvider(activity: SigninActivity, viewModelFactory: InjectingSavedStateViewModelFactory): ViewModelProvider {
            return ViewModelProvider(activity, viewModelFactory.create(activity))
        }

        @ActivityContext
        @JvmStatic
        @Provides
        fun provideContext(activity: SigninActivity): Context {
            return activity
        }
    }
}