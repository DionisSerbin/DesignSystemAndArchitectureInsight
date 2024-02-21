package ru.serbin.features.ui.viewmodel

import android.content.Context
import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import kotlinx.coroutines.flow.StateFlow
import kotlinx.parcelize.Parcelize
import ru.serbin.features.enabledfeature.ui.viewmodel.EnabledFeaturesComponentProvider
import ru.serbin.features.settingsfeatures.ui.viewmodel.SettingsFeatureComponentsProvider
import ru.serbin.features.utils.toStateFlow

class FeaturesComponentImpl(
    private val applicationContext: Context,
    componentContext: ComponentContext,
) : ComponentContext by componentContext, FeaturesComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack: StateFlow<ChildStack<*, FeaturesComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Settings,
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
                    componentContext = componentContext,
                    onEnabledFeaturesOpen = {
                        navigateTo(NavigateScreen.ENABLED)
                    }
                ).provide()
            )
        }

        is ChildConfig.Enabled -> {
            FeaturesComponent.Child.Enabled(
                EnabledFeaturesComponentProvider(
                    componentContext = componentContext,
                    onSettingsClicked = {
                        navigateTo(NavigateScreen.SETTINGS)
                    }
                ).provide()
            )
        }
    }

    private fun navigateTo(screen: NavigateScreen) = when (screen) {
        NavigateScreen.ENABLED -> navigation.push(ChildConfig.Enabled)
        NavigateScreen.SETTINGS -> navigation.push(ChildConfig.Settings)
    }

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