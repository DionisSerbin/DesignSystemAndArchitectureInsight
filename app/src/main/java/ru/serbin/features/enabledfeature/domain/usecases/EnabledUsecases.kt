package ru.serbin.features.enabledfeature.domain.usecases

import javax.inject.Inject

data class EnabledUsecases @Inject constructor(
    val getEnabledFeatures: GetEnabledFeatures
)