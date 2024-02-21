package ru.serbin.features.data.model.response

data class FeaturesDataResponse(
    val featuresResponse: List<FeatureDataResponse>
)

data class FeatureDataResponse(
    val name: String,
    val isEnabled: Boolean,
)