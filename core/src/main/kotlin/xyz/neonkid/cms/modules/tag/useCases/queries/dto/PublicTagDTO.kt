package xyz.neonkid.cms.modules.tag.useCases.queries.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class PublicTagDTO @JsonCreator constructor (
    @JsonProperty("name") val name: String,
    @JsonProperty("public_post_count") val publicPostCount: Int
)