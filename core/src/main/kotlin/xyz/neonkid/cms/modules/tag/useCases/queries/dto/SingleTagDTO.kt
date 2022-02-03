package xyz.neonkid.cms.modules.tag.useCases.queries.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class SingleTagDTO @JsonCreator constructor (
    @JsonProperty("name") val name: String,
    @JsonProperty("public_post_count") val publicPostCount: Int
)