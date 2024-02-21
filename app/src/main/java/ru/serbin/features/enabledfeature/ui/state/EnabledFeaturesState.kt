package ru.serbin.features.enabledfeature.ui.state

import ru.serbin.features.data.model.Feature

data class EnabledFeaturesState(
    val isLoading: Boolean = true,
    val features: List<Feature> = emptyList(),
    val error: String? = null
)
