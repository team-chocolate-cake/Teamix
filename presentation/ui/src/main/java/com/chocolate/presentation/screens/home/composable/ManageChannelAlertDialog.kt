package com.chocolate.presentation.screens.home.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.CustomColorsPalette
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXXLarge
import com.chocolate.presentation.theme.SpacingXMedium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageChannelAlertDialog(
    title: String,
    subTitle: String,
    colors: CustomColorsPalette,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
    ) {
        Card(
            modifier = modifier.fillMaxWidth().wrapContentHeight(),
            colors = CardDefaults.cardColors(colors.card),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = SpacingXXLarge, top = SpacingXXLarge, bottom = SpacingXLarge),
                text = "Do you want to $title channel",
                style = MaterialTheme.typography.titleMedium,
                color = colors.onBackground87,
            )
            Text(
                modifier = Modifier.fillMaxWidth().padding(start = SpacingXXLarge),
                text = subTitle,
                style = MaterialTheme.typography.bodySmall,
                color = colors.onBackground60,
            )
            Row(
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
                    .padding(vertical = 34.dp).padding(end = SpacingXXLarge),
                horizontalArrangement = Arrangement.spacedBy(SpacingXXLarge, alignment = Alignment.End)
            ) {
                Text(
                    text = stringResource(R.string.dismiss),
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.onBackground87,
                    modifier = Modifier.clickable { onDismiss() })
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.red60,
                    modifier = Modifier.padding(end = SpacingXMedium).clickable { onDismiss() })
            }
        }
    }
}