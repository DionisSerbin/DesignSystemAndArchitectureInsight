package ru.serbin.features.settingsfeatures.domain.usecases

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import ru.serbin.features.data.model.Feature
import ru.serbin.utils.Logger

class ChangeFeatureStateByName {
    private val logger = Logger("ChangeFeatureStateByName")

    operator fun invoke(
        featuresState: MutableStateFlow<List<Feature>>,
        featureName: String
    ) {
        logger.d("invoke")

        featuresState.update { features ->
            features.map {
                if (it.name == featureName) {
                    it.copy(
                        isEnabled = !it.isEnabled
                    )
                } else it
            }
        }
        logger.d("state: ${featuresState.value}")
    }
}