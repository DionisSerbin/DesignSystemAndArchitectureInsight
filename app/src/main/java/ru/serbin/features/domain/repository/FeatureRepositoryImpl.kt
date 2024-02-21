package ru.serbin.features.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.serbin.features.data.mapper.toFeatures
import ru.serbin.features.data.mockApi.FeaturesInteractor
import ru.serbin.features.data.model.Feature
import ru.serbin.utils.Logger
import ru.serbin.utils.ResponseStatus
import java.io.IOException
import javax.inject.Inject

class FeaturesRepositoryImpl @Inject constructor(
    private val featuresInteractor: FeaturesInteractor
) : FeaturesRepository {

    private companion object {
        private const val NO_INTERNET =
            "Не обнаружено соединение с сервером. Проверьте интернет подключение"
    }

    private val logger = Logger("FeaturesRepositoryImpl")

    override fun getAllFeatures(): Flow<ResponseStatus<List<Feature>>> = flow {
        try {
            logger.d("getAllFeatures loading...")
            emit(ResponseStatus.Loading())

            featuresInteractor.getAllFeatures().collect { featuresDataResponse ->
                val features = featuresDataResponse.toFeatures()
                logger.d("success")
                emit(ResponseStatus.Success(features))
            }
        } catch (e: IOException) {
            logger.d("error")
            emit(ResponseStatus.Error(e.message ?: NO_INTERNET))
        }
    }

    override fun getEnabledFeatures(): Flow<ResponseStatus<List<Feature>>> = flow {
        try {
            logger.d("getEnabledFeatures loading...")
            emit(ResponseStatus.Loading())

            featuresInteractor.getEnabledFeatures().collect { featuresDataResponse ->
                val features = featuresDataResponse.toFeatures()
                logger.d("success")
                emit(ResponseStatus.Success(features))
            }
        } catch (e: IOException) {
            logger.d("error")
            emit(ResponseStatus.Error(e.message ?: NO_INTERNET))
        }
    }
}