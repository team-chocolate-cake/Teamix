package com.chocolate.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.Radius12
import com.chocolate.presentation.theme.Space16

@Composable
fun SwipeBackGround() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.Red,
                shape = RoundedCornerShape(Radius12)
            )
            .padding(Space16)
    ) {
        Icon(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.ic_remove),
            contentDescription = null,
        )
    }
}


@Preview
@Composable
private fun Preview() {
    SwipeBackGround()
}