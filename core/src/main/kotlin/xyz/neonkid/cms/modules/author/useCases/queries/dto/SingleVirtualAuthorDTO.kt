package xyz.neonkid.cms.modules.author.useCases.queries.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Neon K.I.D on 1/4/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class SingleVirtualAuthorDTO @JsonCreator constructor(
    @JsonProperty("id") val id: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("profile_image") val profileImage: String?,
    @JsonProperty("post_count") val postCount: Long
)