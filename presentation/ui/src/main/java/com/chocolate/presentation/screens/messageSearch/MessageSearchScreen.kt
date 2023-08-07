package com.chocolate.presentation.screens.messageSearch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chocolate.presentation.screens.combosables.PersonCardWithDetails
import com.chocolate.presentation.screens.combosables.SearchBox
import com.chocolate.presentation.screens.searchMessage
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors

@Composable
fun MessageSearchScreen(
    //navController: NavController,
) {
    MessageSearchContent(searchMessage)
}

@Composable
private fun MessageSearchContent(
    state: MessageSearchUiState
) {
    val colors = MaterialTheme.customColors()

    TeamixTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colors.background)
                .padding(Space16),
        ) {
            SearchBox()
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(Space8),
                contentPadding = PaddingValues(vertical = Space16)
            ) {
                items(state.messages.count()) { index ->
                    PersonCardWithDetails(
                        personImageUrl = state.messages[index].imageUrl,
                        title = state.messages[index].name,
                        subTitle = state.messages[index].messageContent,
                        date = state.messages[index].date,
                        subTitleMaxLine = 3
                    )
                }
            }
        }
    }
}