package com.chocolate.presentation.saveLater.tap


import com.chocolate.presentation.saveLater.saveLaterScreenContent


val tabs = listOf(
    TabItem(
        title = "In Progress",
        screen = { saveLaterScreenContent() }
    ),
    TabItem(
        title = "Archived",
        screen = { /* display nothing */ }
    ),
    TabItem(
        title = "Completed",
        screen = { /* display nothing */ }
    )
)
