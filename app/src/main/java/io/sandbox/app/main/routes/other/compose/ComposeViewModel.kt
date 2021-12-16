package io.sandbox.app.main.routes.other.compose

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.sandbox.app.base.vm.BaseViewModel
import javax.inject.Inject

@HiltViewModel
internal class ComposeViewModel @Inject constructor() : BaseViewModel() {

    val timer: LiveData<Int> = TimerLiveData(viewModelScope.coroutineContext)
    val isUserBored: LiveData<Boolean> = timer.map { it >= USER_BORED_SECONDS }

    private companion object {
        const val USER_BORED_SECONDS = 5
    }
}