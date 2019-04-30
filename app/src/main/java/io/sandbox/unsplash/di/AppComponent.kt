package io.sandbox.unsplash.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.sandbox.unsplash.app.App
import io.sandbox.unsplash.di.module.ActivityModule
import io.sandbox.unsplash.di.module.AppModule
import io.sandbox.unsplash.di.module.ClientModule
import io.sandbox.unsplash.di.module.DataSourceFactoryModule
import io.sandbox.unsplash.di.module.FragmentModule
import io.sandbox.unsplash.di.module.NetworkModule
import io.sandbox.unsplash.di.module.ViewModelModule
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