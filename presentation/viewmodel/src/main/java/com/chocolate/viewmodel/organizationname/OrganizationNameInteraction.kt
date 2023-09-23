package com.chocolate.viewmodel.organizationname

interface OrganizationNameInteraction {
    fun onOrganizationNameChange(organizationName: String)
    fun onClickCreateOrganization()
    fun onEnterButtonClick(organizationName: String)
}