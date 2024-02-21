package ru.serbin.features.enabledfeature.ui.viewmodel

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.serbin.features.enabledfeature.domain.usecases.EnabledUsecases
import ru.serbin.features.enabledfeature.ui.state.EnabledFeaturesState
import ru.serbin.features.utils.componentCoroutineScope
import ru.serbin.utils.Logger
import ru.serbin.utils.ResponseStatus


class EnabledFeaturesComponentImpl(
    componentContext: ComponentContext,
    private val enabledUsecases: EnabledUsecases,
    private val onSettingsClicked: () -> Unit
) : ComponentContext by componentContext, EnabledFeaturesComponent {

    init {
        getInitEnabledFeatures()
    }

    private val _enabledFeaturesState = MutableStateFlow(
        EnabledFeaturesState()
    )
    override val enabledFeaturesState = _enabledFeaturesState.asStateFlow()

    private val coroutineScope = componentCoroutineScope()

    private val logger = Logger("EnabledFeaturesComponentImpl")

    override fun onFeatureClicked(name: String) {
        logger.d("open feature $name")
    }

    override fun onFeatureSettingsClicked() {
        logger.d("open feature settings")
        onSettingsClicked()
    }

    private fun getInitEnabledFeatures() {
        coroutineScope.launch {
            enabledUsecases.getEnabledFeatures().collect { featuresResponse ->
                logger.d("collect features $featuresResponse")

                _enabledFeaturesState.value = when (featuresResponse) {
                    is ResponseStatus.Success -> {
                        enabledFeaturesState.value.copy(
                            isLoading = false,
                            features = featuresResponse.data ?: emptyList(),
                            error = null
                        )
                    }

                    is ResponseStatus.Error -> {
                        enabledFeaturesState.value.copy(
                            isLoading = false,
                            features = emptyList(),
                            error = featuresResponse.message
                        )
                    }

                    is ResponseStatus.Loading -> {
                        enabledFeaturesState.value.copy(
                            isLoading = true,
                            features = emptyList(),
                            error = null
                        )
                    }
                }
            }
        }
    }
}