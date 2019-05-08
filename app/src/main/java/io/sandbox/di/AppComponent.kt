package io.sandbox.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.sandbox.app.App
import io.sandbox.di.module.ActivityModule
import io.sandbox.di.module.AppModule
import io.sandbox.di.module.ClientModule
import io.sandbox.di.module.DataSourceFactoryModule
import io.sandbox.di.module.FragmentModule
import io.sandbox.di.module.NetworkModule
import io.sandbox.di.module.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        ClientModule::class,
        DataSourceFactoryModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}