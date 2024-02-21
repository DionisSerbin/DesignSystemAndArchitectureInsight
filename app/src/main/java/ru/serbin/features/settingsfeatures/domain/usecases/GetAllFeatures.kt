package ru.serbin.features.settingsfeatures.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import ru.serbin.features.domain.repository.FeaturesRepository
import ru.serbin.utils.Logger
import javax.inject.Inject

class GetAllFeatures @Inject constructor(
    private val featuresRepository: FeaturesRepository
) {
    private val logger = Logger("GetAllFeatures")

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    operator fun invoke() = featuresRepository
        .getAllFeatures()
        .also {
            logger.d("invoke")
        }
        .flowOn(dispatcher)
}