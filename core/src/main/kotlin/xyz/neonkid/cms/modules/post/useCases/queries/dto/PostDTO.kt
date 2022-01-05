package xyz.neonkid.cms.modules.post.useCases.queries.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import xyz.neonkid.cms.modules.post.domain.valueObjects.Category
import xyz.neonkid.cms.modules.post.domain.valueObjects.VirtualAuthor
import java.time.LocalDateTime

/**
 * Created by Neon K.I.D on 12/31/21
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class PostDTO (
    @JsonProperty("id") val id: Long,
    @JsonProperty("title") val title: String,
    @JsonProperty("body") val body: String?,
    @JsonProperty("thumbnail") val thumbnail: String?,
    @JsonProperty("isPrivate") val isPrivate: Boolean,
    @JsonProperty("description") val description: String?,

    @JsonProperty("published_at")
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    val publishedAt: LocalDateTime?,

    @JsonProperty("virtual_author") val virtualAuthor: VirtualAuthor?,
    @JsonProperty("category") val category: Category?,
)