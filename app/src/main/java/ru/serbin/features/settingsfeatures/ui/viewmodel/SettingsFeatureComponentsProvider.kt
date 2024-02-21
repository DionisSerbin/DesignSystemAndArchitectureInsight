package ru.serbin.features.settingsfeatures.ui.viewmodel

import android.content.Context
import com.arkivanov.decompose.ComponentContext
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.serbin.features.settingsfeatures.domain.usecases.SettingsUseCases
import javax.inject.Inject

class SettingsFeatureComponentsProvider @Inject constructor(
    @ApplicationContext private val context: Context,
    private val componentContext: ComponentContext,
    private val onEnabledFeaturesOpen: () -> Unit
) {

    @InstallIn(SingletonComponent::class)
    @EntryPoint
    interface SettingsFeatureComponentsProviderEntryPoint {
        fun provideSettingsUseCases(): SettingsUseCases
    }


    fun provide(): SettingsFeaturesComponent = SettingsFeaturesComponentImpl(
        componentContext = componentContext,
        settingsUseCases = EntryPointAccessors
            .fromApplication(
                context,
                SettingsFeatureComponentsProviderEntryPoint::class.java
            ).provideSettingsUseCases(),
        onEnabledFeaturesOpen = onEnabledFeaturesOpen
    )
}