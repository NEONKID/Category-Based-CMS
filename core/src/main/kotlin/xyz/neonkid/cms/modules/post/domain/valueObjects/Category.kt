package xyz.neonkid.cms.modules.post.domain.valueObjects

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import xyz.neonkid.cms.modules.category.domain.aggregate.CategoryId

/**
 * Created by Neon K.I.D on 12/31/21
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class Category @JsonCreator constructor(
    @JsonProperty("id") val id: Long,
    @JsonProperty("name") val name: String,
    @JsonProperty("parents") val parents: Category?
)