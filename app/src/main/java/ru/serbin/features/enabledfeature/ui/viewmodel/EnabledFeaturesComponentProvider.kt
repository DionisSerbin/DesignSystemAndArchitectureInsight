package ru.serbin.features.enabledfeature.ui.viewmodel

import com.arkivanov.decompose.ComponentContext
import ru.serbin.features.enabledfeature.domain.usecases.EnabledUsecases
import javax.inject.Inject

class EnabledFeaturesComponentProvider(
    private val componentContext: ComponentContext,
    private val onSettingsClicked: () -> Unit
) {

    @Inject
    lateinit var enabledUsecases: EnabledUsecases

    fun provide(): EnabledFeaturesComponent = EnabledFeaturesComponentImpl(
        componentContext = componentContext,
        enabledUsecases = enabledUsecases,
        onSettingsClicked = onSettingsClicked
    )
}