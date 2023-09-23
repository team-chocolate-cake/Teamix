package com.chocolate.viewmodel.organizationName

interface OrganizationNameInteraction {
    fun onOrganizationNameChange(organizationName: String)
    fun onClickCreateOrganization()
    fun onEnterButtonClick(organizationName: String,snakeBarr:Boolean)
}