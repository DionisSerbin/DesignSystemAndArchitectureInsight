package ru.serbin.features.settingsfeatures.ui.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.serbin.features.data.mockApi.MockFeatures
import ru.serbin.features.settingsfeatures.ui.state.SettingsFeaturesState

interface SettingsFeaturesComponent {
    val settingsFeaturesState: SettingsFeaturesState
}