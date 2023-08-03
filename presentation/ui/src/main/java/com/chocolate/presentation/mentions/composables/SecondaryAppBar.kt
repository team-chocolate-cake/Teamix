package com.chocolate.presentation.mentions.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.OnLightOnBackground87
import com.chocolate.presentation.theme.OnPrimary
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space56
import com.chocolate.presentation.theme.Typography

@Composable
fun SecondaryAppBar(
    textAppBar: String
) {
    Row(
        modifier = Modifier.fillMaxWidth().height(Space56).background(OnPrimary),
        horizontalArrangement = Arrangement.spacedBy(Space16),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_back),
            contentDescription = stringResource(R.string.arrow_back),
        )
        Text(
            textAppBar,
            style = Typography.titleMedium,
            color = OnLightOnBackground87
        )
    }
}