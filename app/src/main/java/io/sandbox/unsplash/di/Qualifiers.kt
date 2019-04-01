package io.sandbox.unsplash.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import javax.inject.Scope
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass

@Scope
@Retention
annotation class ActivityScope

@Target(FUNCTION, PROPERTY_GETTER, PROPERTY_SETTER)
@Retention
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)