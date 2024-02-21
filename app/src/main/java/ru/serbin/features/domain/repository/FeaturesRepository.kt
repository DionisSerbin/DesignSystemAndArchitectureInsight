package ru.serbin.features.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.serbin.features.data.model.Feature
import ru.serbin.utils.ResponseStatus

interface FeaturesRepository {
    fun getAllFeatures(): Flow<ResponseStatus<List<Feature>>>

    fun getEnabledFeatures(): Flow<ResponseStatus<List<Feature>>>
}