package ru.serbin.designsystemandarchitectureinsight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import com.arkivanov.decompose.defaultComponentContext
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import ru.serbin.designsystemandarchitectureinsight.ui.theme.DesignSystemAndArchitectureInsightTheme
import ru.serbin.features.ui.view.FeaturesLayout
import ru.serbin.features.ui.viewmodel.FeaturesComponentImpl
import ru.serbin.features.utils.MyColor

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val coroutineScopeIO = getIOCoroutineScope()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponent = FeaturesComponentImpl(
            applicationContext = applicationContext,
            coroutineScope = coroutineScopeIO,
            componentContext = defaultComponentContext()
        )
        setContent {
            DesignSystemAndArchitectureInsightTheme {
                Surface(color = MyColor.Background) {
                    FeaturesLayout(
                        component = rootComponent
                    )
                }
            }
        }
    }

    private fun getIOCoroutineScope(): CoroutineScope {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        lifecycle
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            scope.cancel()
        }

        return scope
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