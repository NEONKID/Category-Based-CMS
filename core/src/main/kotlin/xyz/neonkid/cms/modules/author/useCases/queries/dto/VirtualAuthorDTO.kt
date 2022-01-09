package xyz.neonkid.cms.modules.author.useCases.queries.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

/**
 * Created by Neon K.I.D on 1/4/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class VirtualAuthorDTO(
    @JsonProperty("id") val id: UUID,
    @JsonProperty("name") val name: String,
    @JsonProperty("description") val description: String?,
    @JsonProperty("profile_image") val profileImage: String?,
    @JsonProperty("post_count") val postCount: Int
)