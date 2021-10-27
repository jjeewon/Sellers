package com.gomdolstudio.sellers.di.module

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gomdolstudio.covidinfoapp.di.factory.InjectingSavedStateViewModelFactory
import com.gomdolstudio.musicapp_assistedinjection.di.scope.ActivityContext
import com.gomdolstudio.musicapp_assistedinjection.di.scope.ActivityScope
import com.gomdolstudio.sellers.R
import com.gomdolstudio.sellers.databinding.ActivitySignupBinding
import com.gomdolstudio.sellers.ui.SignupActivity
import dagger.Module
import dagger.Provides

@Module
class SignupModule {
    @Module
    companion object{
        @JvmStatic
        @Provides
        @ActivityScope
        fun provideSignupActivityBinding(activity: SignupActivity): ActivitySignupBinding{
            return DataBindingUtil.setContentView(activity, R.layout.activity_signup)
        }
        @JvmStatic
        @Provides
        @ActivityScope
        fun provideViewModelProvider(activity:SignupActivity, viewModelFactory: InjectingSavedStateViewModelFactory): ViewModelProvider {
            return ViewModelProvider(activity, viewModelFactory.create(activity))
        }

        @ActivityContext
        @JvmStatic
        @Provides
        fun provideContext(activity: SignupActivity): Context{
            return activity
        }
    }
}