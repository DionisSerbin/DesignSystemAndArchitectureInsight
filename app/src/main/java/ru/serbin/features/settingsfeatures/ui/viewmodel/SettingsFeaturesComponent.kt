package ru.serbin.features.settingsfeatures.ui.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.serbin.features.data.mockApi.MockFeatures
import ru.serbin.features.settingsfeatures.ui.state.SettingsFeaturesState

interface SettingsFeaturesComponent {

    val settingsFeaturesState: StateFlow<SettingsFeaturesState>

    fun onFeatureEnabledChanged(name: String)

    fun onPreviousScreenReturn()
}

class SettingsFeaturesComponentStub : SettingsFeaturesComponent {
    override val settingsFeaturesState = MutableStateFlow(
        SettingsFeaturesState(
            isLoading = false,
            features = MockFeatures.features,
            error = null,
        )
    )
    override fun onFeatureEnabledChanged(name: String) = Unit
    override fun onPreviousScreenReturn() = Unit
}