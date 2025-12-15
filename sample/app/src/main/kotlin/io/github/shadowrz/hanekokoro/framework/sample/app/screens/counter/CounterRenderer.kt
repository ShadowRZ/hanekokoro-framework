package io.github.shadowrz.hanekokoro.framework.sample.app.screens.counter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Inject
import io.github.shadowrz.hanekokoro.framework.annotations.HanekokoroInject
import io.github.shadowrz.hanekokoro.framework.runtime.component.onBackPressed
import io.github.shadowrz.hanekokoro.framework.runtime.renderer.compose.ComposeRenderer
import io.github.shadowrz.hanekokoro.framework.sample.app.screens.counter.icons.Back

@Inject
@HanekokoroInject.ContributesRenderer(AppScope::class)
class CounterRenderer : ComposeRenderer<CounterComponent>() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(
        component: CounterComponent,
        modifier: Modifier,
    ) {
        val counter by component.counter.subscribeAsState()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("Counter")
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = component::onBackPressed,
                        ) {
                            Icon(
                                Back,
                                contentDescription = "Back",
                            )
                        }
                    },
                )
            },
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Text(
                    counter.toString(),
                )
                Row {
                    Button(onClick = component::increment) {
                        Text("+1")
                    }
                    Button(onClick = component::decrement) {
                        Text("-1")
                    }
                }
            }
        }
    }
}
