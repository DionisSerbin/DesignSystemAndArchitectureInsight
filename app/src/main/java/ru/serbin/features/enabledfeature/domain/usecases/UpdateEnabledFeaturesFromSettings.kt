package ru.serbin.features.enabledfeature.domain.usecases

import kotlinx.coroutines.flow.MutableStateFlow
import ru.serbin.features.data.model.Feature
import ru.serbin.utils.Logger

class UpdateEnabledFeaturesFromSettings {
    private val logger = Logger("UpdateEnabledFeaturesFromSettings")

    suspend operator fun invoke(
        enabledFeaturesState: MutableStateFlow<List<Feature>>,
        fromSettingsFeaturesState: MutableStateFlow<List<Feature>>
    ) {
        logger.d("invoke")
        fromSettingsFeaturesState.collect {
            enabledFeaturesState.value = emptyList()
            enabledFeaturesState.value += it

            logger.d("state: ${enabledFeaturesState.value}")
        }
    }
}