package ru.serbin.features.enabledfeature.ui.viewmodel

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.serbin.features.data.model.Feature
import ru.serbin.features.enabledfeature.domain.usecases.EnabledUsecases
import ru.serbin.features.enabledfeature.ui.state.EnabledFeaturesState
import ru.serbin.features.enabledfeature.ui.state.EnabledFeaturesStateImpl
import ru.serbin.features.utils.componentCoroutineScope
import ru.serbin.features.utils.toError
import ru.serbin.features.utils.toFeatures
import ru.serbin.features.utils.toLoading
import ru.serbin.utils.Logger
import ru.serbin.utils.ResponseStatus


class EnabledFeaturesComponentImpl(
    componentContext: ComponentContext,
    private val coroutineScope: CoroutineScope,
    private val enabledUsecases: EnabledUsecases,
    private val fromSettingsFeaturesStateFlow: MutableStateFlow<List<Feature>>,
    onSettingsClicked: () -> Unit
) : ComponentContext by componentContext, EnabledFeaturesComponent {

    private val logger = Logger("EnabledFeaturesComponentImpl")
    private val featuresStateFlow = MutableStateFlow<List<Feature>>(emptyList())
    private val isLoadingStateFlow = MutableStateFlow(true)
    private val errorStateFlow = MutableStateFlow<String?>(null)

    override val enabledFeaturesState: EnabledFeaturesState = EnabledFeaturesStateImpl(
        isLoading = isLoadingStateFlow,
        features = featuresStateFlow,
        error = errorStateFlow,
        onFeatureClicked = { name ->
            logger.d("open feature $name")
        },
        onFeatureSettingsClicked = onSettingsClicked.also { logger.d("open feature settings") }
    )

    init {
        getInitEnabledFeatures()

        updateFeaturesFromSettings()
    }

    private fun updateFeaturesFromSettings() {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                enabledUsecases.updateEnabledFeaturesFromSettings(
                    fromSettingsFeaturesState = fromSettingsFeaturesStateFlow,
                    enabledFeaturesState = featuresStateFlow
                )
            }
        }
    }

    private fun getInitEnabledFeatures() {
        coroutineScope.launch {
            enabledUsecases.getEnabledFeatures().collect { featuresResponse ->
                logger.d("collect features ${featuresResponse.data}")

                featuresStateFlow.value = featuresResponse.toFeatures()
                isLoadingStateFlow.value = featuresResponse.toLoading()
                errorStateFlow.value = featuresResponse.toError()
            }
        }
    }
}