package com.chocolate.presentation.on_boarding

import androidx.annotation.DrawableRes
import com.chocolate.presentation.R

sealed class OnBoardingPage(
    @DrawableRes val imageResource: Int,
    val title: String,
    val description: String
) {
    object First: OnBoardingPage(
        R.drawable.img_on_boarding_one,
        "Welcome to Our Dynamic Workspace",
        "Welcome to our app! Join a dynamic workspace where teams collaborate effortlessly. Discover real-time messaging, video calls, and screen sharing, empowering seamless communication"
    )

    object Second: OnBoardingPage(
        R.drawable.img_on_boarding_two,
        "Organize, Collaborate and Boost Productivity",
        "Create channels to organize discussions around projects and topics. Engage in group chats and stay up-to-date with notifications. Boost productivity with file attachments and integrations"
    )

    object Third: OnBoardingPage(
        R.drawable.img_on_boarding_three,
        "Organize, Collaborate and Boost Productivity",
        "Personalize your profile, add a professional touch with avatars. Connect with colleagues and build strong relationships. Embrace a new era of work and learning in this unified platform"
    )

}
