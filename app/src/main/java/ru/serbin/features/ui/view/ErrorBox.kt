package ru.serbin.features.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ErrorOutline
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.serbin.features.utils.MyColor

@Composable
internal fun ErrorBox(
    errorText: String?
) {
    if (errorText == null) return

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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
            modifier = Modifier
                .wrapContentSize()
                .background(MyColor.CardColor, shape = RoundedCornerShape(16.dp)),
            ) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 50.dp, top = 40.dp, end = 50.dp, bottom = 40.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.TwoTone.ErrorOutline,
                    contentDescription = "arrow back",
                    tint = MaterialTheme.colorScheme.error
                )
                Space(value = 40)
                Text(
                    text = errorText,
                    color = MaterialTheme.colorScheme.error,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }
        }
    }
}