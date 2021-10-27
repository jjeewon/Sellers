package com.gomdolstudio.sellers.di

import com.gomdolstudio.sellers.App
import com.gomdolstudio.sellers.di.module.ActivityModule
import com.gomdolstudio.sellers.di.module.AppModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (ActivityModule::class), (AppModule::class)])
interface AppComponent : AndroidInjector<App>{
    @Component.Factory
    abstract class Factory: AndroidInjector.Factory<App>{}
}