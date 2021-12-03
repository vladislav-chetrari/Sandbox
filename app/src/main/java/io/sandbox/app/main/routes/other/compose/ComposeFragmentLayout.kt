package io.sandbox.app.main.routes.other.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign

@Composable
internal fun ComposeFragmentLayout() {
    Column(verticalArrangement = Arrangement.Center) {
        Text(
            text = "Hello compose integration",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4
        )
    }
}