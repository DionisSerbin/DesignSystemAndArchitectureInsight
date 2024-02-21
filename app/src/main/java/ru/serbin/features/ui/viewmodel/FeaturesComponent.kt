package ru.serbin.features.ui.viewmodel

import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.StateFlow
import ru.serbin.features.enabledfeature.ui.viewmodel.EnabledFeaturesComponent
import ru.serbin.features.settingsfeatures.ui.viewmodel.SettingsFeaturesComponent

interface FeaturesComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    sealed interface Child {
        class Enabled(val component: EnabledFeaturesComponent) : Child
        class Settings(val component: SettingsFeaturesComponent) : Child
    }
}