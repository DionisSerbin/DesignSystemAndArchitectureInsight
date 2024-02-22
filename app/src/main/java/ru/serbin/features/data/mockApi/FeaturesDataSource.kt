package ru.serbin.features.data.mockApi

import kotlinx.coroutines.delay
import ru.serbin.features.data.model.response.FeaturesDataResponse

object FeaturesDataSource {
    private const val DELAY_MS = 2_000L

    suspend fun getAllFeatures(): FeaturesDataResponse {
        delay(DELAY_MS)
        return FeaturesDataResponse(
            featuresResponse = MockFeatures.features.toFeatureDataReponse()
        )
    }

    suspend fun getEnabledFeatures(): FeaturesDataResponse {
        delay(DELAY_MS)
        return FeaturesDataResponse(
            featuresResponse = MockFeatures.features
                .toFeatureDataReponse()
                .filter { it.isEnabled }
        )
    }
}