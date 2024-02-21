package ru.serbin.features.utils

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

internal fun getRandomColor(): Color = when (Random.nextInt(0, 3)) {
    1 -> MyColor.Pink
    2 -> MyColor.Blue
    3 -> MyColor.Orange
    else -> MyColor.Violet
}