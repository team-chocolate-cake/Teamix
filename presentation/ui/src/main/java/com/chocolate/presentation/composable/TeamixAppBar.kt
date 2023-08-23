package com.chocolate.presentation.composable

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.LocalNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamixAppBar(
    title: String,
    containerColor: Color = MaterialTheme.customColors().primary,
    actions: @Composable RowScope.() -> Unit = {},
) {
    val colors = MaterialTheme.customColors()
    val navController = LocalNavController.current
    TopAppBar(
        title = {
            Text(
                text = title,
                color = colors.onBackground87,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {navController.popBackStack()}
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.alt_arrow_left),
                    tint = colors.onBackground87,
                    contentDescription = ""
                )
            }
        },
        actions = actions,
        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = containerColor)
    )
}