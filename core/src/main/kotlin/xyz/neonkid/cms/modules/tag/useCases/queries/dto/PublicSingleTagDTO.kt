package xyz.neonkid.cms.modules.tag.useCases.queries.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class PublicSingleTagDTO @JsonCreator constructor (
    @JsonProperty("name") val name: String,
    @JsonProperty("recently_published") val recentlyPublished: Boolean,
    @JsonProperty("public_post_count") val publicPostCount: Int
)