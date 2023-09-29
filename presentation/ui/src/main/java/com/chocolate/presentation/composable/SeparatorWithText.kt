package com.chocolate.presentation.composable

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
import androidx.compose.ui.res.stringResource
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.Float1
import com.chocolate.presentation.theme.Radius4
import com.chocolate.presentation.theme.SpacingMedium
import com.chocolate.presentation.theme.SpacingSmall
import com.chocolate.presentation.theme.SpacingXLarge

@Composable
fun SeparatorWithText(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = SpacingXLarge),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(Float1)
                .height(SpacingSmall)
                .background(Color.Gray)
                .padding(horizontal = SpacingMedium)
        )
        Text(
            text = stringResource(id = R.string.or),
            color = Color.Gray,
            modifier = Modifier
                .background(Color.Transparent, shape = RoundedCornerShape(Radius4))
                .padding(horizontal = SpacingMedium)
        )
        Box(
            modifier = Modifier
                .weight(Float1)
                .height(SpacingSmall)
                .background(Color.Gray)
                .padding(horizontal = SpacingMedium)
        )
    }
}