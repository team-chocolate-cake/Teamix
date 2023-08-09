package com.chocolate.presentation.profile.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.theme.CustomColorsPalette
import com.chocolate.presentation.theme.Space16


@Composable
fun OrganizationNameSheet(onClick:()->Unit,color: CustomColorsPalette){
    ProfileBottomSheet(
        colors = color,
        onDismissBottomSheet = { onClick()}) {
        Column(modifier = Modifier.padding(start =Space16, bottom = Space16)) {
            Text(
                text = "Select Organization Image",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier,
                color = color.onBackground87
            )

            ProfileTextField(
                text = "name",
                colorFocused = color.card,
                colorUnFocused = color.card,
                colorIcon = color.card
            )

        }
    }
}