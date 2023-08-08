package com.chocolate.presentation.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.profile.component.ChannelNameSheet
import com.chocolate.presentation.profile.component.MultiChoiceDialog
import com.chocolate.presentation.profile.component.OrganizationImageSheet
import com.chocolate.presentation.profile.component.OrganizationNameSheet
import com.chocolate.presentation.profile.component.SettingCard
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors

@Composable
fun OwnerPowerScreen() {
    OwnerPowerContent()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnerPowerContent() {
    TeamixTheme {
        val color = MaterialTheme.customColors()
        var showDialog by remember {
            mutableStateOf(false)
        }
        var organizationImageSheet by remember {
            mutableStateOf(false)
        }

        var organizationNameSheet by remember {
            mutableStateOf(false)
        }
        var channelName by remember {
            mutableStateOf(false)
        }


        if (showDialog) {
            MultiChoiceDialog(onClick = { showDialog = false }, list = listOf("Guest", "Member", "Administrator", "Owner"))
        }

        if (organizationNameSheet) {

            OrganizationNameSheet(onClick = { organizationNameSheet = false}, color =color )
        }

        if (channelName) {

            ChannelNameSheet(onClick = { channelName = false }, color = color)
        }


        if (organizationImageSheet) {

           OrganizationImageSheet(onClick = { organizationImageSheet = false }, color =color )
        }

        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(text="Owner Powers", style = MaterialTheme.typography.titleMedium
                    ,color=color.onBackground87)
                },navigationIcon={ Image(
                    painter = painterResource(id = R.drawable.alt_arrow_left),
                    contentDescription = null
                )},colors= TopAppBarDefaults.topAppBarColors(color.onPrimary))
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                item {
                    Text(
                        text = "Organization", style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 80.dp)
                    )
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .wrapContentHeight()
                            .background(color.card)
                    ) {
                        Column {
                            SettingCard(
                                click = { /*TODO*/ },
                                text = " Invites User",
                                icon = painterResource(id = R.drawable.invitesuser)
                            )
                            Divider(color = color.background, thickness = 2.dp)
                            SettingCard(
                                click = { organizationNameSheet = true },
                                text = "Edit Organization Name",
                                icon = painterResource(id = R.drawable.editorganizationname)
                            )
                            Divider(color = color.background, thickness = 2.dp)
                            SettingCard(
                                click = { organizationImageSheet = true },
                                text = "Edit Organization Image",
                                icon = painterResource(id = R.drawable.organizationimage)
                            )
                        }

                    }

                }

                item {
                    Text(
                        text = "Permissions & Roles", style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .wrapContentHeight()
                            .background(color.card)
                    ) {
                        Column {
                            SettingCard(
                                click = { showDialog = true },
                                text = "Change member Role",
                                icon = painterResource(id = R.drawable.ownerpowers)
                            )
                            Divider(color = color.background, thickness = 2.dp)
                            SettingCard(
                                click = { /*TODO*/ },
                                text = "Who invites users",
                                icon = painterResource(id = R.drawable.whoinviteusers)
                            )
                            Divider(color = color.background, thickness = 2.dp)
                            SettingCard(
                                click = { /*TODO*/ },
                                text = "Who can add custom emoji",
                                icon = painterResource(id = R.drawable.userheart)
                            )
                            Divider(color = color.background, thickness = 2.dp)
                            SettingCard(
                                click = {},
                                text = "Who can use @all/@everyone\n mentions",
                                icon = painterResource(id = R.drawable.mentions)
                            )
                        }

                    }

                }

                item {
                    Text(
                        text = "Channel", style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .wrapContentHeight()
                            .background(color.card)
                    ) {
                        Column {
                            SettingCard(
                                click = { channelName = true },
                                text = "Create Channel",
                                icon = painterResource(id = R.drawable.channel)
                            )
                            Divider(color = color.background, thickness = 2.dp)
                            SettingCard(
                                click = { },
                                text = "Delete Channel",
                                icon = painterResource(id = R.drawable.deletechannel),
                                textColor = color.red
                            )
                        }

                    }

                }


            }


        }


    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OwnerPowerScreenPreview() {
    OwnerPowerContent()
}



