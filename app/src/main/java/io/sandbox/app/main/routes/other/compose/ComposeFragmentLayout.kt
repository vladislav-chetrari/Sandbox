package io.sandbox.app.main.routes.other.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.sandbox.R

@Composable
internal fun ComposeFragmentLayout(viewModel: ComposeViewModel) {
    val viewMode by viewModel.mode.observeAsState()
    viewMode?.let {
        when (it) {
            ComposeViewMode.Greeting -> GreetingView()
            is ComposeViewMode.Timer -> TimerView(value = it.seconds)
        }
    }
}

@Composable
private fun GreetingView(contentBelow: @Composable () -> Unit = {}) {
    val transitionState = remember { MutableTransitionState(false).apply { targetState = true } }
    FadeVisibility(transitionState) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Column {
                Text(
                    text = stringResource(R.string.compose_greeting),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h4
                )
                contentBelow()
            }
        }
    }
}

@Composable
private fun TimerView(value: Int) {
    GreetingView {
        Text(
            text = stringResource(R.string.compose_timer_label, value),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun FadeVisibility(
    transitionState: MutableTransitionState<Boolean>,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visibleState = transitionState,
        enter = fadeIn(animationSpec = tween(1000)),
        exit = fadeOut(animationSpec = tween(1000))
    ) { content() }
}