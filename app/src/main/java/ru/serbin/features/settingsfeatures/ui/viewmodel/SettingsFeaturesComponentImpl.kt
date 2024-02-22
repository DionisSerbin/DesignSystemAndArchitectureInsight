package ru.serbin.features.settingsfeatures.ui.viewmodel

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.serbin.features.data.model.Feature
import ru.serbin.features.settingsfeatures.domain.usecases.SettingsUseCases
import ru.serbin.features.settingsfeatures.ui.state.SettingsFeaturesState
import ru.serbin.features.settingsfeatures.ui.state.SettingsFeaturesStateImpl
import ru.serbin.features.utils.componentCoroutineScope
import ru.serbin.features.utils.toError
import ru.serbin.features.utils.toFeatures
import ru.serbin.features.utils.toLoading
import ru.serbin.utils.Logger
import ru.serbin.utils.ResponseStatus
import javax.inject.Inject

class SettingsFeaturesComponentImpl @Inject constructor(
    componentContext: ComponentContext,
    private val coroutineScope: CoroutineScope,
    private val settingsUseCases: SettingsUseCases,
    onEnabledFeaturesOpen: (List<Feature>) -> Unit
) : ComponentContext by componentContext, SettingsFeaturesComponent {

    init {
        getInitFeatures()
    }

    private val logger = Logger("SettingsFeaturesComponentImpl")

    private val featuresStateFlow = MutableStateFlow<List<Feature>>(emptyList())
    private val isLoadingStateFlow = MutableStateFlow(true)
    private val errorStateFlow = MutableStateFlow<String?>(null)

    override val settingsFeaturesState: SettingsFeaturesState = SettingsFeaturesStateImpl(
        isLoading = isLoadingStateFlow,
        features = featuresStateFlow,
        error = errorStateFlow,
        onEnabledFeaturesOpen = {
            logger.d("open enabled features")
            onEnabledFeaturesOpen(featuresStateFlow.value)
        },
        onFeatureEnabledChanged = ::onFeatureEnabledChanged
    )

    private fun onFeatureEnabledChanged(name: String) {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                settingsUseCases.changeFeatureStateByName(
                    featuresStateFlow,
                    name
                )
            }
        }
    }

    private fun getInitFeatures() {
        coroutineScope.launch {
            settingsUseCases.getAllFeatures().collect { featuresResponse ->
                logger.d("collect features ${featuresResponse.data}")

                featuresStateFlow.emit(featuresResponse.toFeatures())
                isLoadingStateFlow.value = featuresResponse.toLoading()
                errorStateFlow.value = featuresResponse.toError()
            }
        }
    }
}
