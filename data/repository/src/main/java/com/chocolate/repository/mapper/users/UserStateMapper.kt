package com.chocolate.repository.mapper.users

import com.chocolate.entities.user.respons.Aggregated
import com.chocolate.entities.user.respons.Presence
import com.chocolate.entities.user.respons.UserState
import com.chocolate.entities.user.respons.Website
import com.chocolate.repository.dto.users.response.AggregatedDto
import com.chocolate.repository.dto.users.response.PresenceDto
import com.chocolate.repository.dto.users.response.UserStateDto
import com.chocolate.repository.dto.users.response.WebsiteDto

fun UserStateDto.toUserState(): UserState {

    return UserState(
        presence =presenceDto?.toPresence()
    )


}

fun  PresenceDto.toPresence(): Presence{
    return Presence(
        aggregated = aggregatedDto?.toAggregated(),
        website = websiteDto?.toWebsite()
    )
}


fun AggregatedDto.toAggregated(): Aggregated {
    return Aggregated(
        status, timestamp
    )
}

fun WebsiteDto.toWebsite():Website{
    return Website(status, timestamp)
}