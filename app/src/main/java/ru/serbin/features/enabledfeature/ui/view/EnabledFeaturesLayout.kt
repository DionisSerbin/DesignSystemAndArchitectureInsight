package ru.serbin.features.enabledfeature.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import kotlinx.coroutines.Dispatchers
import ru.serbin.designsystemandarchitectureinsight.ui.theme.DesignSystemAndArchitectureInsightTheme
import ru.serbin.features.data.model.Feature
import ru.serbin.features.enabledfeature.ui.viewmodel.EnabledFeaturesComponent
import ru.serbin.features.enabledfeature.ui.viewmodel.EnabledFeaturesComponentStub
import ru.serbin.features.ui.view.ErrorBox
import ru.serbin.features.ui.view.FeatureCardText
import ru.serbin.features.ui.view.Loading
import ru.serbin.features.ui.view.Space
import ru.serbin.features.utils.MyColor
import ru.serbin.features.utils.StringsConstants

@Composable
fun EnabledFeaturesLayout(component: EnabledFeaturesComponent) {
    val state = component.enabledFeaturesState.collectAsState(Dispatchers.Main.immediate)
    val features = state.value.features
    val isLoading = state.value.isLoading
    val errorText = state.value.error

    ErrorBox(
        errorText = errorText
    )
    Column {
        SettingsFeaturesButton(
            modifier = Modifier.padding(16.dp),
            onClick = component::onFeatureSettingsClicked
        )
        Space(value = 40)
        Tittle(modifier = Modifier.fillMaxWidth())
        Space(value = 40)
        FeaturesGrid(
            modifier = Modifier.fillMaxSize(),
            features = features,
            shouldShow = !isLoading && errorText == null,
            onFeatureClick = component::onFeatureClicked
        )
    }

    Loading(
        modifier = Modifier.fillMaxSize(),
        isLoading = isLoading
    )
}

@Composable
private fun FeaturesGrid(
    modifier: Modifier,
    shouldShow: Boolean,
    features: List<Feature>,
    onFeatureClick: (String) -> Unit,
) {
    if (!shouldShow) return

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        userScrollEnabled = true
    ) {
        items(features) { feature ->
            FeatureGridCard(
                modifier = modifier.size(120.dp),
                name = feature.name,
                onClick = onFeatureClick
            )
        }
    }
}

@Composable
private fun FeatureGridCard(
    modifier: Modifier,
    name: String,
    onClick: (String) -> Unit,
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
            .clickable { onClick(name) }
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            FeatureCardText(
                text = name,
                size = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SettingsFeaturesButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        border = BorderStroke(2.dp, MyColor.OutlineButtonColor),
        onClick = onClick
    ) {
        Text(
            text = StringsConstants.CHOOSE_FEATURE_SETTINGS,
            color = MyColor.OutlineButtonTextColor,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
        )
    }
}

@Composable
fun Tittle(modifier: Modifier) {
    Text(
        modifier = modifier,
        text = StringsConstants.ENABLED_FEATURES,
        color = MyColor.White,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        textAlign = TextAlign.Center,
    )
}

@Preview
@Composable
fun preview() {
    DesignSystemAndArchitectureInsightTheme() {
        Surface(color = MyColor.Background) {
            EnabledFeaturesLayout(component = EnabledFeaturesComponentStub())
        }
    }
}