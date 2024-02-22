package ru.serbin.features.ui.viewmodel

import android.content.Context
import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.parcelize.Parcelize
import ru.serbin.features.data.model.Feature
import ru.serbin.features.enabledfeature.ui.viewmodel.EnabledFeaturesComponentProvider
import ru.serbin.features.settingsfeatures.ui.viewmodel.SettingsFeatureComponentsProvider
import ru.serbin.features.utils.toStateFlow

class FeaturesComponentImpl(
    private val applicationContext: Context,
    private val coroutineScope: CoroutineScope,
    componentContext: ComponentContext,
) : ComponentContext by componentContext, FeaturesComponent {

    private val navigation = StackNavigation<ChildConfig>()

    private val fromSettingsFeaturesStateFlow = MutableStateFlow<List<Feature>>(emptyList())

    override val childStack: StateFlow<ChildStack<*, FeaturesComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Enabled,
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ) = when (config) {
        is ChildConfig.Settings -> {
            FeaturesComponent.Child.Settings(
                SettingsFeatureComponentsProvider(
                    context = applicationContext,
                    coroutineScope = coroutineScope,
                    componentContext = componentContext,
                    onEnabledFeaturesOpen = { features ->
                        updateFromFeaturesState(features)
                        navigateTo(NavigateScreen.ENABLED)
                    }
                ).provide()
            )
        }

        is ChildConfig.Enabled -> {
            FeaturesComponent.Child.Enabled(
                EnabledFeaturesComponentProvider(
                    context = applicationContext,
                    coroutineScope = coroutineScope,
                    componentContext = componentContext,
                    fromSettingsFeaturesStateFlow = fromSettingsFeaturesStateFlow,
                    onSettingsClicked = {
                        navigateTo(NavigateScreen.SETTINGS)
                    }
                ).provide()
            )
        }
    }

    private fun updateFromFeaturesState(features: List<Feature>) {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                fromSettingsFeaturesStateFlow.value = emptyList()
                fromSettingsFeaturesStateFlow.value += features.toEnabled()
            }
        }
    }
    private fun navigateTo(screen: NavigateScreen) = when (screen) {
        NavigateScreen.ENABLED -> navigation.bringToFront(ChildConfig.Enabled)
        NavigateScreen.SETTINGS -> navigation.bringToFront(ChildConfig.Settings)
    }

    private fun List<Feature>.toEnabled() = this.filter { it.isEnabled }

    private sealed interface ChildConfig : Parcelable {
        @Parcelize
        object Enabled : ChildConfig

        @Parcelize
        object Settings : ChildConfig
    }

    private enum class NavigateScreen {
        ENABLED,
        SETTINGS
    }
}