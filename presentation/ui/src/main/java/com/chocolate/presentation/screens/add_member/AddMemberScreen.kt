package com.chocolate.presentation.screens.add_member

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.PersonCardWithDetails
import com.chocolate.presentation.composable.TeamixTextField
import com.chocolate.presentation.composable.CancelableRectangularProfileItem
import com.chocolate.presentation.theme.Border1
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.addMember.AddMemberUiState

@Composable
fun AddMemberScreen(
    //navController: NavController,
) {
    AddMemberContent(
        state = AddMemberUiState()
    )
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun AddMemberContent(
    state: AddMemberUiState
) {
    val colors = MaterialTheme.customColors()
    val typography = MaterialTheme.typography

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colors.background)
                .padding(Space16),
        ) {
            TeamixTextField(
                value = state.searchInput,
                hint = stringResource(id = R.string.search),
                painter = painterResource(id = R.drawable.ic_search),
                onValueChange = {})

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(Space8),
                contentPadding = PaddingValues(end = Space16, top = Space16, bottom = Space16)
            ) {
                items(state.selectedMembers) { member ->
                    CancelableRectangularProfileItem(
                        personImageUrl = member.imageUrl,
                        personName = member.name,
                        onCancelButtonClicked = {}
                    )
                }
            }

            Divider(thickness = Border1, color = colors.border)

            Text(
                modifier = Modifier.padding(top = Space16, bottom = Space8),
                text = stringResource(id = R.string.suggested),
                style = typography.bodyLarge,
                color = colors.onBackground87
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(Space8),
                contentPadding = PaddingValues(bottom = Space16)
            ) {
                items(state.suggestedMembers) { member ->
                    PersonCardWithDetails(
                        personImageUrl = member.imageUrl,
                        title = member.name,
                        subTitle = member.jobTitle,
                        isSelected = member.isSelected,
                        painter = painterResource(id = R.drawable.ic_check),
                        contentDescription = ""
                    )
                }
            }

        }
}

@Preview
@Composable
fun AddMemberPreview() {
    TeamixTheme {
        AddMemberContent(AddMemberUiState())
    }
}