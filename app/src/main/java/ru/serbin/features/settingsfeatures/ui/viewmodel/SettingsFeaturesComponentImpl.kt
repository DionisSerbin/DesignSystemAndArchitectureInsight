package ru.serbin.features.settingsfeatures.ui.viewmodel

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.serbin.features.settingsfeatures.domain.usecases.SettingsUseCases
import ru.serbin.features.settingsfeatures.ui.state.SettingsFeaturesState
import ru.serbin.features.utils.componentCoroutineScope
import ru.serbin.utils.Logger
import ru.serbin.utils.ResponseStatus
import javax.inject.Inject

class SettingsFeaturesComponentImpl @Inject constructor(
    componentContext: ComponentContext,
    private val settingsUseCases: SettingsUseCases,
    private val onEnabledFeaturesOpen: () -> Unit
) : ComponentContext by componentContext, SettingsFeaturesComponent {

    init {
        getInitFeatures()
    }

    private val _settingsFeaturesState = MutableStateFlow(
        SettingsFeaturesState()
    )
    override val settingsFeaturesState = _settingsFeaturesState.asStateFlow()

    private val coroutineScope = componentCoroutineScope()

    private val logger = Logger("SettingsFeaturesComponentImpl")

    override fun onFeatureEnabledChanged(name: String) {
        TODO("Not yet implemented")
    }

    override fun onPreviousScreenReturn() {
        logger.d("open enabled features")
        onEnabledFeaturesOpen()
    }

    private fun getInitFeatures() {
        coroutineScope.launch {
            settingsUseCases.getAllFeatures().collect { featuresResponse ->
                logger.d("collect features $featuresResponse")

                _settingsFeaturesState.value = when (featuresResponse) {
                    is ResponseStatus.Success -> {
                        settingsFeaturesState.value.copy(
                            isLoading = false,
                            features = featuresResponse.data ?: emptyList(),
                            error = null
                        )
                    }

                    is ResponseStatus.Error -> {
                        settingsFeaturesState.value.copy(
                            isLoading = false,
                            features = emptyList(),
                            error = featuresResponse.message
                        )
                    }

                    is ResponseStatus.Loading -> {
                        settingsFeaturesState.value.copy(
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