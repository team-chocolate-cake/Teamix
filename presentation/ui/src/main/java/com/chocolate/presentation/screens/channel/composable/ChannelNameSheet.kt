package com.chocolate.presentation.screens.channel.composable

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
import androidx.compose.ui.res.stringResource
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.BottomSheet
import com.chocolate.presentation.composable.TeamixOutLinedTextField
import com.chocolate.presentation.theme.CustomColorsPalette
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space8


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChannelNameSheet(onClick:()->Unit,color: CustomColorsPalette){
    var selectedItem by remember {
        mutableStateOf(0)
    }
    BottomSheet(colors = color, onDismissBottomSheet = { onClick() }) {
        Column(modifier = Modifier.padding(start = Space16, bottom = Space16)) {
            Text(
                text = stringResource(id = R.string.channel_name), style = MaterialTheme.typography.labelMedium,
                modifier = Modifier,
                color = color.onBackground87
            )

            TeamixOutLinedTextField(
                text =stringResource(id = R.string.channel_name),
                onValueChange = {},
                colorFocused = color.card,
                colorUnFocused = color.card,
                colorIcon = color.card
            )
            Text(
                text =stringResource(id = R.string.channel_status) , style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(top = Space16),
                color = color.onBackground87
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                InputChip(
                    modifier = Modifier.padding(end =Space8),
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0 },
                    label = { Text(text = stringResource(R.string.private_text)) },
                    colors = InputChipDefaults.inputChipColors(
                        selectedContainerColor = color.primary,
                        disabledContainerColor = color.card,
                        selectedLabelColor = color.white

                    )
                )
                InputChip(selected = selectedItem == 1,
                    onClick = { selectedItem = 1 }, label = { Text(text = stringResource(R.string.public_text)) },
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