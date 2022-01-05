package xyz.neonkid.cms.persistence.post

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

/**
 * Created by Neon K.I.D on 1/2/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Table("post")
data class PostEntity (
    @Id val id: Long,
    val title: String,
    val body: String?,
    val thumbnail: String?,
    val isPrivate: Boolean = true,
    val description: String?,
    @Column("published_at") val publishedAt: LocalDateTime?,
    @MappedCollection(idColumn = "post_id") val categoryId: CategoryRef?,
    @MappedCollection(idColumn = "post_id") val virtualAuthorId: VirtualAuthorRef?
)

@Table("post_category")
data class CategoryRef(val categoryId: Long)

@Table("post_virtual_author")
data class VirtualAuthorRef(val virtualAuthorId: UUID)