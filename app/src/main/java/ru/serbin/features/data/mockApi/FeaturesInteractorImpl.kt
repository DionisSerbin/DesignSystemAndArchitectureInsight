package ru.serbin.features.data.mockApi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.serbin.features.data.model.response.FeaturesDataResponse
import ru.serbin.utils.Logger

class FeaturesInteractorImpl(
    private val featuresDataSource: FeaturesDataSource = FeaturesDataSource
) : FeaturesInteractor {

    private val logger = Logger("FeaturesInteractorImpl")

    override fun getAllFeatures(): Flow<FeaturesDataResponse> = flow {
        logger.d("getAllFeatures")
        val response = featuresDataSource.getAllFeatures()
        logger.d("emit")
        emit(response)
    }

    override fun getEnabledFeatures(): Flow<FeaturesDataResponse> = flow {
        logger.d("getEnabledFeatures")
        val response = featuresDataSource.getEnabledFeatures()
        logger.d("emit")
        emit(response)
    }
}