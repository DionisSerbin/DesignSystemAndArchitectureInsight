package ru.serbin.features.settingsfeatures.domain.usecases

import javax.inject.Inject

data class SettingsUseCases @Inject constructor(
    val getAllFeatures: GetAllFeatures,
    val changeFeatureStateByName: ChangeFeatureStateByName
)