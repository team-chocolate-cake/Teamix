package com.chocolate.presentation.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chocolate.presentation.R
import com.chocolate.presentation.Screen
import com.chocolate.presentation.composable.Button
import com.chocolate.presentation.composable.FitImage
import com.chocolate.presentation.theme.OnLightPrimary
import com.chocolate.presentation.theme.Typography

@Composable
fun WelcomeScreen(
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.padding(top = 64.dp, start = 16.dp, end = 16.dp),
            text = buildAnnotatedString {
                append("Messaging App\n")
                withStyle(style = SpanStyle(color = OnLightPrimary)) {
                    append("but build for \nboost Productivity")
                }
            },
            style = Typography.titleLarge,
            fontSize = 24.sp
        )
        FitImage(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            drawableResource = R.drawable.img_welcome,
            contentDescription = "Welcome image"
        )
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Button(onClick = { navController.navigate(Screen.OnBoarding.route)},
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 28.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Get Stated", style = Typography.bodyLarge, color = Color.White)
            }
        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(navController = rememberNavController())
}
