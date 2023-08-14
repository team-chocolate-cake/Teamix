package com.chocolate.viewmodel.organization_name

interface OrganizationNameInteraction {
    fun onOrganizationNameChange(organizationName: String)
    fun onClickCreateOrganization()
    fun onClickActionButton(organizationName: String)

}