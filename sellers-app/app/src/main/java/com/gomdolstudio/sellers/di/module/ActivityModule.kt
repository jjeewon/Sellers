package com.gomdolstudio.sellers.di.module

import com.gomdolstudio.musicapp_assistedinjection.di.scope.ActivityScope
import com.gomdolstudio.sellers.ui.signin.SigninActivity
import com.gomdolstudio.sellers.ui.signup.SignupActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [(SignupModule::class)])
    abstract fun getSignupActivity(): SignupActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(SigninModule::class)])
    abstract fun getSigninActivity(): SigninActivity
}