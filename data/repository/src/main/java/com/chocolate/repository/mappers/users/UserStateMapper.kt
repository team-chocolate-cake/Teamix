package com.chocolate.repository.mappers.users

import com.chocolate.entities.user.Aggregated
import com.chocolate.entities.user.Presence
import com.chocolate.entities.user.UserState
import com.chocolate.entities.user.Website
import com.chocolate.repository.model.dto.users.response.AggregatedDto
import com.chocolate.repository.model.dto.users.response.PresenceDto
import com.chocolate.repository.model.dto.users.response.UserStateDto
import com.chocolate.repository.model.dto.users.response.WebsiteDto


fun UserStateDto.toUserState(): UserState {

    return UserState(
        presence = presenceDto?.toPresence() ?: Presence(Aggregated("",0),
            Website("",0)  )
    )


}

fun PresenceDto.toPresence(): Presence {
    return Presence(
        aggregated = aggregatedDto?.toAggregated() ?: Aggregated("",0),
        website = websiteDto?.toWebsite() ?: Website("",0))
}


fun AggregatedDto.toAggregated(): Aggregated {
    return Aggregated(
        status?:"", timestamp?:0
    )
}

fun WebsiteDto.toWebsite(): Website {
    return Website(status?:"", timestamp?:0)
}