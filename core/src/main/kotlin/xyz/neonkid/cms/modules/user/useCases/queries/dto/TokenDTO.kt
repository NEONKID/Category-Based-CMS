package xyz.neonkid.cms.modules.user.useCases.queries.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class TokenDTO @JsonCreator constructor(
    @JsonProperty("access_token") val accessToken: String
)