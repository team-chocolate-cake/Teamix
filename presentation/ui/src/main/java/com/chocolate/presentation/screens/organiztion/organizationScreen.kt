package com.chocolate.presentation.screens.organiztion

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.DarkPrimary
import com.chocolate.presentation.theme.LightBackground
import com.chocolate.presentation.theme.OnLightSecondary38
import com.chocolate.viewmodel.organization_name.OrganizationNameUiState
import com.chocolate.viewmodel.organization_name.OrganizationNameViewModel

@Composable
fun OrganizationScreen(
    navController: NavController,
    viewModel: OrganizationNameViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    OrganizationContent(updateOrganizationName = viewModel::updateOrganizationName, state)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrganizationContent(updateOrganizationName: (String) -> Unit, state: OrganizationNameUiState) {


    val buttonColor = if (state.nameOrganization.isNotEmpty()) DarkPrimary else OnLightSecondary38


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightBackground)
    ) {
        Column(


        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.start__5_),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(R.string.enter_your_name_organization),
                style = MaterialTheme.typography.labelMedium)


            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = state.nameOrganization,
                onValueChange = { nameorganiztion ->
                    updateOrganizationName(nameorganiztion)

                },
                placeholder = { Text("", color = Color.Black.copy(alpha = 0.6f)) },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 16.dp),
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor
                )
            ) {
                Text("Enter")
            }

            Spacer(modifier = Modifier.height(42.dp))
            SeparatorWithText()
            Spacer(modifier = Modifier.height(18.dp))
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    stringResource(R.string.create_new_organizat),
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.bodyMedium
                )
            }        }
    }
}

@Composable
fun SeparatorWithText(text: String = "OR") {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text,
            color = Color.Gray,
            modifier = Modifier
                .background(Color.Transparent, shape = RoundedCornerShape(4.dp))
                .padding(horizontal = 8.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(Color.Gray)
        )
    }
}
