package com.chocolate.presentation.profile.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.theme.CustomColorsPalette


@Composable
fun OrganizationNameSheet(onClick:()->Unit,color: CustomColorsPalette){
    ProfileBottomSheet(
        colors = color,
        onDismissBottomSheet = { onClick()}) {
        Column(modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)) {
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