package com.chocolate.presentation.profile.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.theme.CustomColorsPalette


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChannelNameSheet(onClick:()->Unit,color: CustomColorsPalette){
    var selectedItem by remember {
        mutableStateOf(0)
    }
    ProfileBottomSheet(colors = color, onDismissBottomSheet = { onClick() }) {
        Column(modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)) {
            Text(
                text = "Channel Name", style = MaterialTheme.typography.labelMedium,
                modifier = Modifier,
                color = color.onBackground87
            )

            ProfileTextField(
                text = "chocolate-cake",
                colorFocused = color.card,
                colorUnFocused = color.card,
                colorIcon = color.card
            )
            Text(
                text = "Channel Name", style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(top = 16.dp),
                color = color.onBackground87
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                InputChip(
                    modifier = Modifier.padding(end = 8.dp),
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0 },
                    label = { Text(text = "private") },
                    colors = InputChipDefaults.inputChipColors(
                        selectedContainerColor = color.primary,
                        disabledContainerColor = color.card,
                        selectedLabelColor = color.white

                    )
                )
                InputChip(selected = selectedItem == 1,
                    onClick = { selectedItem = 1 }, label = { Text(text = "Public") },
                    colors = InputChipDefaults.inputChipColors(
                        selectedContainerColor = color.primary,
                        disabledContainerColor = color.card,
                        selectedLabelColor = color.white

                    )
                )
            }
        }
    }
}