package com.gomdolstudio.sellers.di.module

import com.gomdolstudio.musicapp_assistedinjection.di.scope.ActivityScope
import com.gomdolstudio.sellers.ui.SignupActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [(SignupModule::class)])
    abstract fun getSignupActivity(): SignupActivity

}