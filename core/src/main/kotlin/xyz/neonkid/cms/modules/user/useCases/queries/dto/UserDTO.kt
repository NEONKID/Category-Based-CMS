package xyz.neonkid.cms.modules.user.useCases.queries.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Neon K.I.D on 2/20/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class UserDTO @JsonCreator constructor(
    @JsonProperty("id") val id: Long,
    @JsonProperty("email") val email: String,
    @JsonProperty("nickname") val nickname: String
)