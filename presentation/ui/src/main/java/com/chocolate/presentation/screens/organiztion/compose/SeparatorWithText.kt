package com.chocolate.presentation.screens.organiztion.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space8

@Composable
fun SeparatorWithText(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Space16),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(Color.Gray)
                .padding(horizontal = Space8)
        )
        Text(
            text = "OR",
            color = Color.Gray,
            modifier = Modifier
                .background(Color.Transparent, shape = RoundedCornerShape(4.dp))
                .padding(horizontal = Space8)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(Color.Gray)
                .padding(horizontal = Space8)
        )
    }
}