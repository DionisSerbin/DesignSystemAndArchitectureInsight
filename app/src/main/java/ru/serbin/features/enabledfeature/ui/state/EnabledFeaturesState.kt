package ru.serbin.features.enabledfeature.ui.state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.serbin.features.data.mockApi.MockFeatures
import ru.serbin.features.data.model.Feature

interface EnabledFeaturesState {
    val isLoading: StateFlow<Boolean>
    val features: StateFlow<List<Feature>>
    val error: StateFlow<String?>
    val onFeatureClicked: (name: String) -> Unit
    val onFeatureSettingsClicked: () -> Unit
}

data class EnabledFeaturesStateImpl(
    override val isLoading: StateFlow<Boolean> = MutableStateFlow(true),
    override val features: StateFlow<List<Feature>> = MutableStateFlow(emptyList()),
    override val error: StateFlow<String?> = MutableStateFlow(null),
    override val onFeatureClicked: (name: String) -> Unit,
    override val onFeatureSettingsClicked: () -> Unit
) : EnabledFeaturesState

data class EnabledFeaturesStateStub(
    override val isLoading: StateFlow<Boolean> = MutableStateFlow(true),
    override val features: StateFlow<List<Feature>> = MutableStateFlow(MockFeatures.enabledFeatures),
    override val error: StateFlow<String?> = MutableStateFlow(null),
    override val onFeatureClicked: (name: String) -> Unit = {},
    override val onFeatureSettingsClicked: () -> Unit = {}
) : EnabledFeaturesState