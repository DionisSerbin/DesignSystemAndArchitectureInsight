package ru.serbin.features.settingsfeatures.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.serbin.designsystemandarchitectureinsight.ui.theme.DesignSystemAndArchitectureInsightTheme
import ru.serbin.features.data.model.Feature
import ru.serbin.features.settingsfeatures.ui.state.SettingsFeaturesState
import ru.serbin.features.settingsfeatures.ui.state.SettingsFeaturesStateStub
import ru.serbin.features.ui.view.ErrorBox
import ru.serbin.features.ui.view.FeatureCardText
import ru.serbin.features.ui.view.Loading
import ru.serbin.features.ui.view.Space
import ru.serbin.features.utils.MyColor
import ru.serbin.features.utils.StringsConstants

@Composable
fun SettingsFeaturesLayout(state: SettingsFeaturesState) {
    val features = state.features.collectAsState().value
    val isLoading = state.isLoading.collectAsState().value
    val errorText = state.error.collectAsState().value
    ErrorBox(
        errorText = errorText
    )
    Column {
        BackTopPanel(
            modifier = Modifier.fillMaxWidth(),
            onReturnClick = state.onEnabledFeaturesOpen
        )
        Space(value = 40)
        FeatureList(
            modifier = Modifier.fillMaxSize(),
            shouldShow = !isLoading && errorText == null,
            features = features,
            onFeatureEnablingChanged = state.onFeatureEnabledChanged
        )
    }
    Loading(
        modifier = Modifier.fillMaxSize(),
        isLoading = isLoading
    )
}

@Composable
fun BackTopPanel(
    modifier: Modifier,
    onReturnClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .background(MyColor.CardColor)
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        OutlinedButton(
            modifier = Modifier,
            border = BorderStroke(2.dp, MyColor.OutlineButtonColor),
            onClick = onReturnClick
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "arrow back",
                tint = MyColor.OutlineButtonTextColor
            )
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = StringsConstants.FEATURE_SETTINGS,
                color = MyColor.White,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
            )
        }
    }
}

@Composable
private fun FeatureList(
    modifier: Modifier,
    shouldShow: Boolean,
    features: List<Feature>,
    onFeatureEnablingChanged: (String) -> Unit
) {
    if (!shouldShow) return

    LazyColumn(
        modifier = modifier,
        userScrollEnabled = true
    ) {
        items(features) { feature ->
            FeatureCard(
                modifier = Modifier.fillMaxWidth(),
                name = feature.name,
                isEnabled = feature.isEnabled,
                onFeatureStateChanged = onFeatureEnablingChanged
            )
        }
    }
}

@Composable
private fun FeatureCard(
    modifier: Modifier,
    name: String,
    isEnabled: Boolean,
    onFeatureStateChanged: (String) -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 32.dp
        ),
        colors = CardColors(
            contentColor = MyColor.CardColor,
            containerColor = MyColor.CardColor,
            disabledContainerColor = MyColor.Background,
            disabledContentColor = MyColor.Background,
        ),
        modifier = modifier
            .padding(16.dp)
            .background(MyColor.CardColor, shape = RoundedCornerShape(16.dp)),
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 28.dp, horizontal = 20.dp),
            horizontalAlignment = Alignment.Start
        ) {
            FeatureCardText(
                text = name,
                size = 28.sp
            )
            Space(value = 15)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = isEnabled.toString(),
                    color = if (isEnabled) MyColor.Green else MyColor.Red,
                    fontSize = 20.sp
                )
                Space(value = 12)
                Switch(
                    checked = isEnabled,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MyColor.Green,
                        checkedTrackColor = MyColor.DarkGreen,
                        uncheckedThumbColor = MyColor.Red,
                        uncheckedTrackColor = MyColor.DarkRed
                    ),
                    onCheckedChange = { onFeatureStateChanged(name) }
                )
            }
        }
    }
}

@Preview(device = "spec:id=reference_phone,shape=Normal,width=411,height=891,unit=dp,dpi=420")
@Composable
fun FeatureListLayoutPreview() {
    DesignSystemAndArchitectureInsightTheme {
        Surface(color = MyColor.Background) {
            SettingsFeaturesLayout(state = SettingsFeaturesStateStub())
        }
    }
}
