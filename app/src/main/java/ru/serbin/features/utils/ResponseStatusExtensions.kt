package ru.serbin.features.utils

import ru.serbin.features.data.model.Feature
import ru.serbin.utils.ResponseStatus

fun ResponseStatus<List<Feature>>.toFeatures() =
    if (this is ResponseStatus.Success && this.data != null) this.data else emptyList()

fun ResponseStatus<List<Feature>>.toLoading() = this is ResponseStatus.Loading

fun ResponseStatus<List<Feature>>.toError() =
    if (this is ResponseStatus.Error) this.message else null