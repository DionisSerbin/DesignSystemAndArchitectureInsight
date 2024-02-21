package ru.serbin.features.data.mapper

import ru.serbin.features.data.model.Feature
import ru.serbin.features.data.model.response.FeaturesDataResponse

fun FeaturesDataResponse.toFeatures(): List<Feature> = this
    .featuresResponse
    .map { featureDataResponse ->
        Feature(
            name = featureDataResponse.name,
            isEnabled = featureDataResponse.isEnabled
        )
    }