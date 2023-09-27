package com.chocolate.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.chocolate.presentation.screens.channel.channelRoute
import com.chocolate.presentation.screens.choosemember.chooseMemberRoute
import com.chocolate.presentation.screens.createchannel.createChannelRoute
import com.chocolate.presentation.screens.createmember.createMemberRoute
import com.chocolate.presentation.screens.createorganization.createOrganizationRoute
import com.chocolate.presentation.screens.createtopic.createTopicRoute
import com.chocolate.presentation.screens.directmessage.directMessageRoute
import com.chocolate.presentation.screens.directmessagechat.dmChatRoute
import com.chocolate.presentation.screens.directmessagechoosemember.directMessageChooseMemberRoute
import com.chocolate.presentation.screens.drafts.draftsRoute
import com.chocolate.presentation.screens.home.homeRoute
import com.chocolate.presentation.screens.login.loginRoute
import com.chocolate.presentation.screens.onboarding.onboardingRoute
import com.chocolate.presentation.screens.organization.organizationNameRoute
import com.chocolate.presentation.screens.profile.profileRoute
import com.chocolate.presentation.screens.savedlater.saveLaterRoute
import com.chocolate.presentation.screens.search.searchRoute
import com.chocolate.presentation.screens.taskorganization.taskOrganizationRoute
import com.chocolate.presentation.screens.topicmessages.topicRoute
import com.chocolate.presentation.screens.welcome.welcomeRoute

@Composable
fun TeamixNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        welcomeRoute()
        onboardingRoute()
        homeRoute()
        organizationNameRoute()
        createOrganizationRoute()
        loginRoute()
        searchRoute()
        profileRoute()
        searchRoute()
        directMessageRoute()
        taskOrganizationRoute()
        topicRoute()
        chooseMemberRoute()
        createChannelRoute()
        saveLaterRoute()
        draftsRoute()
        channelRoute()
        createMemberRoute()
        directMessageChooseMemberRoute()
        dmChatRoute()
        createTopicRoute()
    }
}