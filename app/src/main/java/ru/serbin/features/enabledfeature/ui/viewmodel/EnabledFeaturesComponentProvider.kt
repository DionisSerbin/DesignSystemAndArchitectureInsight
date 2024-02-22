package ru.serbin.features.enabledfeature.ui.viewmodel

import android.content.Context
import com.arkivanov.decompose.ComponentContext
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.serbin.features.data.model.Feature
import ru.serbin.features.enabledfeature.domain.usecases.EnabledUsecases

class EnabledFeaturesComponentProvider(
    @ApplicationContext private val context: Context,
    private val coroutineScope: CoroutineScope,
    private val componentContext: ComponentContext,
    private val fromSettingsFeaturesStateFlow: MutableStateFlow<List<Feature>>,
    private val onSettingsClicked: () -> Unit
) {

    @InstallIn(SingletonComponent::class)
    @EntryPoint
    interface EnabledFeatureComponentsProviderEntryPoint {
        fun provideEnabledUseCases(): EnabledUsecases
    }

    fun provide(): EnabledFeaturesComponent = EnabledFeaturesComponentImpl(
        componentContext = componentContext,
        coroutineScope = coroutineScope,
        enabledUsecases = EntryPointAccessors
            .fromApplication(
                context,
                EnabledFeatureComponentsProviderEntryPoint::class.java
            ).provideEnabledUseCases(),
        fromSettingsFeaturesStateFlow = fromSettingsFeaturesStateFlow,
        onSettingsClicked = onSettingsClicked
    )
}