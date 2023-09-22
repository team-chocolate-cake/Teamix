package com.chocolate.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.chocolate.presentation.screens.directMessageChat.dmChatRoute
import com.chocolate.presentation.screens.channel.channelRoute
import com.chocolate.presentation.screens.chooseMember.chooseMemberRoute
import com.chocolate.presentation.screens.createMember.createMemberRoute
import com.chocolate.presentation.screens.createTopic.createTopicRoute
import com.chocolate.presentation.screens.create_account.createAccountWebViewRoute
import com.chocolate.presentation.screens.createChannel.createChannelRoute
import com.chocolate.presentation.screens.create_organization.createOrganizationRoute
import com.chocolate.presentation.screens.directMessage.directMessageRoute
import com.chocolate.presentation.screens.directMessageChooseMember.directMessageChooseMemberRoute
import com.chocolate.presentation.screens.drafts.draftsRoute
import com.chocolate.presentation.screens.forget_password.forgetPasswordWebViewRoute
import com.chocolate.presentation.screens.home.homeRoute
import com.chocolate.presentation.screens.login.loginRoute
import com.chocolate.presentation.screens.on_boarding.onboardingRoute
import com.chocolate.presentation.screens.organiztion.organizationNameRoute
import com.chocolate.presentation.screens.profile.profileRoute
import com.chocolate.presentation.screens.saveLater.saveLaterRoute
import com.chocolate.presentation.screens.search.searchRoute
import com.chocolate.presentation.screens.taskOrganiztion.taskOrganizationRoute
import com.chocolate.presentation.screens.topicMessages.topicRoute
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
        forgetPasswordWebViewRoute()
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
        createAccountWebViewRoute()
        createMemberRoute()
        directMessageChooseMemberRoute()
        dmChatRoute()
        createTopicRoute()
    }
}