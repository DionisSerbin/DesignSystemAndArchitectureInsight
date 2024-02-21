package ru.serbin.features.data.mockApi

import kotlinx.coroutines.flow.Flow
import ru.serbin.features.data.model.response.FeaturesDataResponse

interface FeaturesInteractor {
    fun getAllFeatures(): Flow<FeaturesDataResponse>
    fun getEnabledFeatures(): Flow<FeaturesDataResponse>
}