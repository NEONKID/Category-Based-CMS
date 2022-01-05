package xyz.neonkid.cms.modules.post.useCases.queries.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import java.time.LocalDateTime

/**
 * Created by Neon K.I.D on 1/3/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class SinglePostDTO (
    @JsonProperty("id") val id: Long,
    @JsonProperty("title") val title: String,
    @JsonProperty("body") val body: String,
    @JsonProperty("thumbnail") val thumbnail: String?,
    @JsonProperty("isPrivate") val isPrivate: Boolean,
    @JsonProperty("description") val description: String?,
    @JsonProperty("published_at")
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    val publishedAt: LocalDateTime?
)