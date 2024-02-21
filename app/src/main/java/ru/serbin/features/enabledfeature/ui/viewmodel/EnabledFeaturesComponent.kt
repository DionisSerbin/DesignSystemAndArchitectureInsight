package ru.serbin.features.enabledfeature.ui.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.serbin.features.data.mockApi.MockFeatures
import ru.serbin.features.enabledfeature.ui.state.EnabledFeaturesState

interface EnabledFeaturesComponent {

    val enabledFeaturesState: StateFlow<EnabledFeaturesState>

    fun onFeatureClicked(name: String)

    fun onFeatureSettingsClicked()
}

class EnabledFeaturesComponentStub : EnabledFeaturesComponent {
    override val enabledFeaturesState = MutableStateFlow(
        EnabledFeaturesState(
            isLoading = false,
            features = MockFeatures.enabledFeatures,
            error = "Все хреново"
        )
    )

    override fun onFeatureClicked(name: String) = Unit

    override fun onFeatureSettingsClicked() = Unit
}