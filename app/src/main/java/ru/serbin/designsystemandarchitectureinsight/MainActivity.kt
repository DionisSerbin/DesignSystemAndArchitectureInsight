package ru.serbin.designsystemandarchitectureinsight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.defaultComponentContext
import dagger.hilt.android.AndroidEntryPoint
import ru.serbin.designsystemandarchitectureinsight.ui.theme.DesignSystemAndArchitectureInsightTheme
import ru.serbin.features.ui.view.FeaturesLayout
import ru.serbin.features.ui.viewmodel.FeaturesComponentImpl

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponent = FeaturesComponentImpl(
            applicationContext = applicationContext,
            componentContext = defaultComponentContext()
        )
        setContent {
            DesignSystemAndArchitectureInsightTheme {
                FeaturesLayout(
                    component = rootComponent
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DesignSystemAndArchitectureInsightTheme {
        Greeting("Android")
    }
}