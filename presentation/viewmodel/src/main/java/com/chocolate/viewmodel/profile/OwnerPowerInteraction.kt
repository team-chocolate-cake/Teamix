package com.chocolate.viewmodel.profile

interface OwnerPowerInteraction {
    fun updateOrganizationNameSheetState(showSheet:Boolean)
    fun updateOrganizationImageState(showSheet:Boolean)
    fun updateChangeMemberRoleDialogState(showSheet:Boolean)
    fun updateCreateChannelSheetState(showSheet:Boolean)
}