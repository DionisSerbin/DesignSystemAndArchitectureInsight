package ru.serbin.features.data.mockApi

import ru.serbin.features.data.model.Feature
import ru.serbin.features.data.model.FeatureNames
import ru.serbin.features.data.model.response.FeatureDataResponse

object MockFeatures {
    val features = listOf(
        Feature(
            isEnabled = false,
            name = FeatureNames.SNACKBAR,
        ),
        Feature(
            isEnabled = true,
            name = FeatureNames.NEOMPORPH,
        ),
        Feature(
            isEnabled = false,
            name = FeatureNames.ANIMATIONS,
        ),
        Feature(
            isEnabled = true,
            name = FeatureNames.ANIMATEDBACKGROUND,
        ),
        Feature(
            isEnabled = false,
            name = FeatureNames.BOTTOMBAR,
        ),
        Feature(
            isEnabled = true,
            name = FeatureNames.BOTTOMSHEET,
        ),
        Feature(
            isEnabled = false,
            name = FeatureNames.ZOOM,
        ),
    )

    val enabledFeatures = listOf(
        Feature(
            isEnabled = true,
            name = FeatureNames.SNACKBAR,
        ),
        Feature(
            isEnabled = true,
            name = FeatureNames.NEOMPORPH,
        ),
        Feature(
            isEnabled = true,
            name = FeatureNames.ANIMATIONS,
        ),
        Feature(
            isEnabled = true,
            name = FeatureNames.ANIMATEDBACKGROUND,
        ),
        Feature(
            isEnabled = true,
            name = FeatureNames.BOTTOMBAR,
        ),
        Feature(
            isEnabled = true,
            name = FeatureNames.BOTTOMSHEET,
        ),
        Feature(
            isEnabled = true,
            name = FeatureNames.ZOOM,
        ),
    )
}

fun List<Feature>.toFeatureDataReponse() = this.map {
    FeatureDataResponse(
        name = it.name,
        isEnabled = it.isEnabled
    )
}