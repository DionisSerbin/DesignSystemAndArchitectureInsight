package ru.serbin.features.settingsfeatures.ui.state

import ru.serbin.features.data.model.Feature

data class SettingsFeaturesState(
    val isLoading: Boolean = true,
    val features: List<Feature> = emptyList(),
    val error: String? = null
)
