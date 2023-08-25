package com.chocolate.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.ButtonSize18
import com.chocolate.presentation.theme.customColors

@Composable
fun CircularButton(
    containerColor: Color,
    modifier: Modifier = Modifier,
    size: Dp = ButtonSize18,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit
) {

    Column(
        modifier = modifier
            .size(size)
            .background(containerColor, shape = CircleShape)
            .clickable { onClick() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}

@Preview
@Composable
private fun CircularButtonPreview() {
    CircularButton(containerColor = MaterialTheme.customColors().red60, onClick = {}) {
        Icon(
            painter = painterResource(id = R.drawable.ic_cancel),
            tint = MaterialTheme.customColors().onPrimary,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
    }
}