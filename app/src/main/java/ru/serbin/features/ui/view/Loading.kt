package ru.serbin.features.ui.view

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.serbin.features.utils.MyColor

@Composable
internal fun Loading(
    modifier: Modifier,
    isLoading: Boolean
) {
    if (!isLoading) return

    CircularProgressIndicator(
        modifier = modifier.wrapContentSize(),
        color = MyColor.Orange
    )
}