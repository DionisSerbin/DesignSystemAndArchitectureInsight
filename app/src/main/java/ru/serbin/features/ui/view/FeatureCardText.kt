package ru.serbin.features.ui.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import ru.serbin.features.utils.getRandomColor

@Composable
internal fun FeatureCardText(text: String, size: TextUnit, textAlign: TextAlign? = null) {
    Text(
        text = text,
        color = getRandomColor(),
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold,
        fontSize = size,
        textAlign = textAlign
    )
}