package ru.serbin.features.enabledfeature.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import ru.serbin.features.domain.repository.FeaturesRepository
import ru.serbin.utils.Logger
import javax.inject.Inject

class GetEnabledFeatures @Inject constructor(
    private val featuresRepository: FeaturesRepository
) {
    private val logger = Logger("GetEnabledFeatures")

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    operator fun invoke() = featuresRepository
        .getEnabledFeatures()
        .also {
            logger.d("invoke")
        }
        .flowOn(dispatcher)
}