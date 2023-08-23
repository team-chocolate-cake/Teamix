package com.chocolate.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.OnLightBackground87
import com.chocolate.presentation.theme.OnLightPrimary
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space56
import com.chocolate.presentation.theme.Typography
import com.chocolate.presentation.theme.customColors

@Composable
fun SecondaryAppBar(
    textAppBar: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(Space56)
            .background(OnLightPrimary),
        horizontalArrangement = Arrangement.spacedBy(Space16),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(start = Space16),
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_back),
            contentDescription = stringResource(R.string.arrow_back),
        )
        Text(
            textAppBar,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.customColors().onBackground87
        )
    }
}