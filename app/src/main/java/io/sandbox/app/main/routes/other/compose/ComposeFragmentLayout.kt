package io.sandbox.app.main.routes.other.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.sandbox.R

@Composable
internal fun ComposeFragmentLayout(viewModel: ComposeViewModel) {
    Column(modifier = Modifier.padding(all = 16.dp)) {
        Text(
            text = stringResource(R.string.compose_greeting),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4
        )
        Spacer(Modifier.weight(1f))

        val timerState by viewModel.timer.observeAsState()
        if (timerState != null) {
            Text(
                text = stringResource(R.string.compose_timer_label, timerState!!),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5
            )
            Spacer(Modifier.weight(1f))
        }

        val showBtn by viewModel.isUserBored.observeAsState()
        if (showBtn == true) {
            Row(
                modifier = Modifier.padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "You better check out some kittens:",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                FloatingActionButton(onClick = {/*TODO navigate to compose list of kittens or something*/ }) {
                    Icon(Icons.Filled.ArrowForward, "")
                }
            }
        }
    }
}