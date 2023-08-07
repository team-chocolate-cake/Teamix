package com.chocolate.presentation.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors

@Composable
fun ProfileScreen() {
    ProfileContent()
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent() {
    TeamixTheme {
        var textState= remember{ mutableStateOf("") }


        val color = MaterialTheme.customColors()
        var number by remember {
            mutableStateOf(0)
        }
        val pageState = rememberPagerState(initialPage = 1)
        val selectButton by remember {
            mutableStateOf(0)
        }
        val selectColor by remember {
            mutableStateOf(color.primary)
        }
        var showDialog by remember {
            mutableStateOf(false)
        }

        var themeDialog by remember {
            mutableStateOf(false)
        }
        var logout by remember {
            mutableStateOf(false)
        }
        var clearHistory by remember {
            mutableStateOf(false)
        }


        var text by remember { mutableStateOf("test") }

        LaunchedEffect(number) {
            if (number == 0) {
                pageState.scrollToPage(0)
            } else {
                pageState.scrollToPage(1)

            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color.background), horizontalAlignment = Alignment.CenterHorizontally
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
                        painter = painterResource(id = R.drawable.deb),
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
                "Abdoood", modifier = Modifier.padding(top = 16.dp),
                style = MaterialTheme.typography.titleMedium,
                color = color.onBackground87
            )
            Text(
                "Android developer", modifier = Modifier,
                style = MaterialTheme.typography.titleMedium,
                color = color.onBackground60
            )

            if (showDialog) {
                AlertDialog(
                    modifier = Modifier,
                    onDismissRequest = { showDialog = false },
                  //  title = { Text(text = "Select Language") },
                    confirmButton = {
                       // Text(text = "Select Language")
                    },
                    dismissButton = {

                    },
                    text = {
                        ProfileLanguageDialog()
                    }, containerColor =color.background
                )
            }
            if (themeDialog) {
                AlertDialog(
                    modifier = Modifier,
                    onDismissRequest = { themeDialog = false },
                    confirmButton = {
                    },
                    dismissButton = {
                    },
                    text = {
                        ChangeThemeDialog()
                    }, containerColor =color.background
                )
            }
            if(clearHistory) {
                AlertDialog(
                    modifier = Modifier,
                    onDismissRequest = { clearHistory = false },
                    title = { Text(text = "Are you sure to delete your history?") },
                    text = { Text(text = "This action will permanently remove all your browsing data," +
                            " including search history, and cached files") },
                    confirmButton = {
                        TextButton(onClick = { }) {
                            Text(text = "confirm")

                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { clearHistory = false }) {
                            Text(text = "dismiss")

                        }
                    },
                    containerColor = color.background
                )
            }
            if(logout) {
                AlertDialog(
                    modifier = Modifier,
                    onDismissRequest = { logout = false },
                    title = { Text(text = "test title") },
                    text = { Text(text = "test content") },
                    confirmButton = {
                        TextButton(onClick = { }) {
                            Text(text = "confirm")

                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { logout = false }) {
                            Text(text = "dismiss")

                        }
                    },
                    containerColor = color.background
                )
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
                            onClick = { number = 0 }, modifier = Modifier
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
                            onClick = { number = 1 }, modifier = Modifier
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
                                        OutlinedTextField(modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 16.dp)
                                                ,value = textState.value,
                                                onValueChange ={
                                                textState.value=it
                                            }, placeholder = {Text("name",color=color.onBackground60.copy(alpha=0.6f))}, shape = RoundedCornerShape(12.dp),
                                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                                 containerColor = color.card,
                                                    focusedBorderColor =  color.primary,
                                                unfocusedBorderColor = color.background
                                            ), trailingIcon = {
                                                Image(
                                                    painter = painterResource(id = R.drawable.pen),
                                                    contentDescription = null
                                                )
                                            }

                                        )
                                }

                            }

                        } else {

                            LazyColumn {
                                item {
                                    SettingCard(click = {showDialog=true},text = "Owner Powers", icon = painterResource(id = R.drawable.ownerpowers))
                                    Divider(color = color.background, thickness = 2.dp)
                                }
                                item {
                                    SettingCard(click = {showDialog=true},text = "Language", icon = painterResource(id = R.drawable.language))
                                    Divider(color = color.background, thickness = 2.dp)
                                }
                                item {
                                    SettingCard(click = {themeDialog=true},text = "Change Theme", icon = painterResource(id = R.drawable.changetheme))
                                    Divider(color = color.background, thickness = 2.dp)
                                }
                                item {
                                    SettingCard(click = {clearHistory=true},text = "Clear History", icon = painterResource(id = R.drawable.clearhistory))
                                    Divider(color = color.background, thickness = 2.dp)
                                }
                                item {
                                    SettingCard(click = {logout=true},text = "Log out", icon = painterResource(id = R.drawable.logout))
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
