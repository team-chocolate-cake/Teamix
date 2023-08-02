package com.chocolate.presentation.screens.home.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.screens.home.ChipsUIState
import com.chocolate.presentation.theme.CustomColorsPalette

@Composable
fun ItemChips(chipsUIState: ChipsUIState, colors: CustomColorsPalette) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(12.dp))
            .background(colors.card),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Card(
                modifier = Modifier.wrapContentSize().align(Alignment.End).padding(end = 4.dp, top = 4.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(colors.primary)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxSize().padding(vertical = 4.dp, horizontal = 8.dp),
                    text = chipsUIState.notificationNumber.toString(),
                    style = MaterialTheme.typography.labelMedium,
                    color = colors.onPrimary,
                    textAlign = TextAlign.End
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_mention),
                contentDescription = "icons",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp).align(Alignment.CenterHorizontally),
                tint = colors.onBackground60,
            )
            Text(
                text = chipsUIState.title,
                color = colors.onBackground60,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 24.dp).padding(horizontal = 26.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}