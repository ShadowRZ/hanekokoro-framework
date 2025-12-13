package io.github.shadowrz.hanekokoro.framework.sample.app.screens.root

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import dev.zacsweers.metro.AppScope
import io.github.shadowrz.hanekokoro.framework.annotations.HanekokoroInject
import io.github.shadowrz.hanekokoro.framework.integration.HanekokoroContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@HanekokoroInject(AppScope::class)
fun RootRenderer(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    Children(
        component.childStack,
        modifier = modifier,
        animation = stackAnimation { fade() + slide() },
    ) {
        when (val child = it.instance) {
            RootComponent.Resolved.Root -> RootUI(
                onNavTarget = component::onNavTarget,
            )

            is RootComponent.Resolved.HasComponent -> HanekokoroContent(
                component = child.component,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootUI(
    modifier: Modifier = Modifier,
    onNavTarget: (RootComponent.NavTarget) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text("Hanekokoro Framework Sample App")
                },
            )
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            ListItem(
                modifier = Modifier.clickable {
                    onNavTarget(RootComponent.NavTarget.Counter)
                },
                headlineContent = {
                    Text("Counter")
                },
            )
        }
    }
}
