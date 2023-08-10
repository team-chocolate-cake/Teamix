package com.chocolate.presentation.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.customColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    title:String,
    navigationBack: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
){
    TopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.customColors().onBackground87,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        modifier = Modifier.border(BorderStroke(1.dp , MaterialTheme.customColors().lightGray)),
        navigationIcon = {
            IconButton(
                onClick = navigationBack
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.alt_arrow_left),
                    tint = MaterialTheme.customColors().onBackground87,
                    contentDescription = "Back button"
                )
            }
        },
        actions = actions
    )
}