package com.chocolate.presentation.screens.profile.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.CustomColorsPalette
import com.chocolate.presentation.theme.Space16


@Composable
fun OrganizationNameSheet(onClick:()->Unit,color: CustomColorsPalette){
    ProfileBottomSheet(
        colors = color,
        onDismissBottomSheet = { onClick()}) {
        Column(modifier = Modifier.padding(start =Space16, bottom = Space16)) {
            Text(
                text = stringResource(R.string.select_organization_name),
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier,
                color = color.onBackground87
            )
            ProfileTextField(
                text = stringResource(R.string.name),
                onValueChange = {},
                colorFocused = color.card,
                colorUnFocused = color.card,
                colorIcon = color.card
            )
        }
    }
}