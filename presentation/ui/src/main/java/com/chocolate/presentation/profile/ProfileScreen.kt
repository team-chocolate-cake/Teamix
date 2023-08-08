package com.chocolate.presentation.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.chocolate.presentation.R
import com.chocolate.presentation.profile.component.ChangeThemeDialog
import com.chocolate.presentation.profile.component.MultiChoiceDialog
import com.chocolate.presentation.profile.component.ProfileDialog
import com.chocolate.presentation.profile.component.ProfileTextField
import com.chocolate.presentation.profile.component.SettingCard
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.profile.ProfileUiState
import com.chocolate.viewmodel.profile.ProfileViewModel

@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel= hiltViewModel()) {
    val state = profileViewModel.state.collectAsState().value
    ProfileContent(state = state)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileContent(state:ProfileUiState) {
    TeamixTheme {
        val color = MaterialTheme.customColors()
        var pageNumber by remember { mutableStateOf(0) }
        val pageState = rememberPagerState(initialPage = 0)
        var showLanguageDialog by remember { mutableStateOf(false) }
        var showThemeDialog by remember { mutableStateOf(false) }
        var showLogoutDialog by remember { mutableStateOf(false) }
        var shpwClearHistoryDialog by remember { mutableStateOf(false) }

        LaunchedEffect(pageNumber) {
            if (pageNumber == 0) {
                pageState.scrollToPage(0)
            } else {
                pageState.scrollToPage(1)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color.background)
                .padding(top = 26.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(Modifier.height(158.dp)) {
                Box(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .size(130.dp)
                        .clip(CircleShape)
                        .border(2.dp, color.primary, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .size(110.dp)
                            .clip(CircleShape),
                        painter = rememberAsyncImagePainter(state.image),
                        contentDescription = null
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.BottomEnd)
                        .clip(CircleShape),
                    colors = IconButtonDefaults.iconButtonColors(color.primary)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.camera),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .padding(bottom = 1.dp),
                        tint = color.onPrimary
                    )
                }
            }

            Text(
                state.name, modifier = Modifier.padding(top = 16.dp),
                style = MaterialTheme.typography.titleMedium,
                color = color.onBackground87
            )
            Text(
                state.job, modifier = Modifier,
                style = MaterialTheme.typography.titleMedium,
                color = color.onBackground60
            )

            if (showLanguageDialog) {
                MultiChoiceDialog(
                    { showLanguageDialog = false },
                    listOf("english", "arabic", "french", "spanish")
                )
            }
            if (showThemeDialog) {
                AlertDialog(
                    modifier = Modifier,
                    onDismissRequest = { showThemeDialog = false },
                    confirmButton = {
                    },
                    dismissButton = {
                    },
                    text = {
                        ChangeThemeDialog()
                    }, containerColor = color.background
                )
            }
            if (shpwClearHistoryDialog) {
                ProfileDialog(title = "Are you sure to delete your history?",
                    text =
                    "This action will permanently remove all your browsing data," +
                            " including search history, and cached files",
                    onClick = { shpwClearHistoryDialog = false })

            }
            if (showLogoutDialog) {
                ProfileDialog(title = "Are you sure to logout?", text =
                "", onClick = { showLogoutDialog = false })
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .height(400.dp)
                    .background(color.card)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .width(250.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(color.background),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { pageNumber = 0 }, modifier = Modifier
                                .padding(start = 16.dp)
                                .width(110.dp),
                            colors = ButtonDefaults.buttonColors(
                                if (pageState.currentPage == 0) color.primary.copy(alpha = 1f) else
                                    color.background
                            )
                        ) {
                            Text(
                                text = "Profile",
                                color = if (pageState.currentPage == 0) color.onPrimary else
                                    color.onBackground60
                            )
                        }
                        Button(
                            onClick = { pageNumber = 1 }, modifier = Modifier
                                .padding(end = 16.dp)
                                .width(110.dp),
                            colors = ButtonDefaults.buttonColors(
                                if (pageState.currentPage == 1) color.primary.copy(alpha = 1f) else
                                    color.background
                            )
                        ) {
                            Text(
                                text = "Settings",
                                color = if (pageState.currentPage == 1) color.onPrimary else
                                    color.onBackground60,
                                maxLines = 1
                            )
                        }
                    }

                    HorizontalPager(state = pageState, pageCount = 2) {
                        if (pageState.currentPage == 0) {
                            LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                                item {
                                    ProfileTextField(
                                        text = "name",
                                        color.primary,
                                        color.background,
                                        colorIcon = color.primary
                                    )
                                }
                                item {
                                    ProfileTextField(
                                        text = state.job,
                                        color.primary,
                                        color.background,
                                        colorIcon = color.primary
                                    )
                                }
                                item {
                                    ProfileTextField(
                                        text = state.number,
                                        color.primary,
                                        color.background,
                                        colorIcon = color.primary
                                    )
                                }
                                item {
                                    ProfileTextField(
                                        text = state.team,
                                        color.primary,
                                        color.background,
                                        colorIcon = color.primary
                                    )
                                }
                                item {
                                    ProfileTextField(
                                        text =state.status,
                                        color.primary,
                                        color.background,
                                        colorIcon = color.primary
                                    )
                                }

                            }

                        } else {

                            LazyColumn {
                                item {
                                    SettingCard(
                                        click = {  },
                                        text = "Owner Powers",
                                        icon = painterResource(id = R.drawable.ownerpowers)
                                    )
                                    Divider(color = color.background, thickness = 2.dp)
                                }
                                item {
                                    SettingCard(
                                        click = { showLanguageDialog = true },
                                        text = "Language",
                                        icon = painterResource(id = R.drawable.language)
                                    )
                                    Divider(color = color.background, thickness = 2.dp)
                                }
                                item {
                                    SettingCard(
                                        click = { showThemeDialog = true },
                                        text = "Change Theme",
                                        icon = painterResource(id = R.drawable.changetheme)
                                    )
                                    Divider(color = color.background, thickness = 2.dp)
                                }
                                item {
                                    SettingCard(
                                        click = { shpwClearHistoryDialog = true },
                                        text = "Clear History",
                                        icon = painterResource(id = R.drawable.clearhistory)
                                    )
                                    Divider(color = color.background, thickness = 2.dp)
                                }
                                item {
                                    SettingCard(
                                        click = { showLogoutDialog = true },
                                        text = "Log out",
                                        icon = painterResource(id = R.drawable.logout)
                                    )
                                    Divider(color = color.background, thickness = 2.dp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}

