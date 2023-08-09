package com.chocolate.presentation.screens.organiztion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.customColors

@Composable
fun LoginScreen(
    navController: NavController,
) {
    LoginContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent() {
    val colors = MaterialTheme.customColors()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.background)
            .padding(horizontal = 16.dp),
    ) {
        Text(
            modifier = Modifier.padding(top = 42.dp),
            text = "Welcome To",
            style = MaterialTheme.typography.titleLarge,
            color = colors.onBackground87
        )
        Text(
            modifier = Modifier.padding(bottom = 48.dp),
            text = "Aboood world",
            style = MaterialTheme.typography.titleLarge,
            color = colors.primary
        )

        Text(
            modifier = Modifier,
            text = stringResource(R.string.enter_your_name_organization),
            style = MaterialTheme.typography.labelMedium
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = "",
            onValueChange = { nameorganiztion ->

            },
            placeholder = { Text("", color = Color.Black.copy(alpha = 0.6f)) },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = colors.card,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            )
        )

        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.enter_your_name_organization),
            style = MaterialTheme.typography.labelMedium
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = "",
            onValueChange = {

            },
            placeholder = { Text("", color = Color.Black.copy(alpha = 0.6f)) },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = colors.card,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 24.dp, end = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                stringResource(R.string.forget_password),
                fontSize = 14.sp,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp),
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.primary
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(stringResource(R.string.sign_in))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LogInPreview() {
    LoginContent()
}
