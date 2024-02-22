package ru.serbin.features.settingsfeatures.ui.state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.serbin.features.data.mockApi.MockFeatures
import ru.serbin.features.data.model.Feature

interface SettingsFeaturesState {
    val isLoading: StateFlow<Boolean>
    val features: StateFlow<List<Feature>>
    val error: StateFlow<String?>

    val onEnabledFeaturesOpen: () -> Unit
    val onFeatureEnabledChanged: (String) -> Unit
}

data class SettingsFeaturesStateImpl(
    override val isLoading: StateFlow<Boolean> = MutableStateFlow(true),
    override val features: StateFlow<List<Feature>> = MutableStateFlow(emptyList()),
    override val error: StateFlow<String?> = MutableStateFlow(null),

    override val onEnabledFeaturesOpen: () -> Unit,
    override val onFeatureEnabledChanged: (String) -> Unit
) : SettingsFeaturesState

data class SettingsFeaturesStateStub(
    override val isLoading: StateFlow<Boolean> = MutableStateFlow(true),
    override val features: StateFlow<List<Feature>> = MutableStateFlow(MockFeatures.features),
    override val error: StateFlow<String?> = MutableStateFlow(null),
    override val onFeatureEnabledChanged: (String) -> Unit = {},
    override val onEnabledFeaturesOpen: () -> Unit = {}
) : SettingsFeaturesState