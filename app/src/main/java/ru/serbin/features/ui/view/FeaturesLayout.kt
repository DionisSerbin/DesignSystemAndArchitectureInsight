package ru.serbin.features.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import ru.serbin.features.enabledfeature.ui.view.EnabledFeaturesLayout
import ru.serbin.features.settingsfeatures.ui.view.SettingsFeaturesLayout
import ru.serbin.features.ui.viewmodel.FeaturesComponent

@Composable
fun FeaturesLayout(component: FeaturesComponent) {
    val childStack by component.childStack.collectAsState()

    Children(stack = childStack) { child ->
        when (val instance = child.instance) {
            is FeaturesComponent.Child.Settings -> SettingsFeaturesLayout(component = instance.component)
            is FeaturesComponent.Child.Enabled -> EnabledFeaturesLayout(component = instance.component)
        }
    }
}