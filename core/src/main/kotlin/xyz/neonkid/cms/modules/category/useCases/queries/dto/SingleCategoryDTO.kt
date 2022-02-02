package xyz.neonkid.cms.modules.category.useCases.queries.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import xyz.neonkid.cms.modules.post.useCases.queries.dto.PostDTO

/**
 * Created by Neon K.I.D on 1/3/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class SingleCategoryDTO @JsonCreator constructor(
    @JsonProperty("id") val id: Long,
    @JsonProperty("name") val name: String,
    @JsonProperty("posts") val posts: List<PostDTO>,
    @JsonProperty("post_count") val postCount: Long,
    @JsonProperty("public_post_count") val publicPostCount: Long
)