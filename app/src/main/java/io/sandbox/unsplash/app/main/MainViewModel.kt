package io.sandbox.unsplash.app.main

import io.sandbox.unsplash.app.base.BaseViewModel
import io.sandbox.unsplash.app.base.JobDispatcher
import javax.inject.Inject

class MainViewModel @Inject constructor(dispatcher: JobDispatcher) : BaseViewModel(dispatcher)