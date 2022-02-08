package xyz.neonkid.cms.persistence.post

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.conversion.MutableAggregateChange
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.relational.core.mapping.event.BeforeSaveCallback
import xyz.neonkid.cms.snowflake.IdGenerator
import java.time.LocalDateTime
import java.util.*

/**
 * Created by Neon K.I.D on 1/2/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Table("post")
data class PostEntity (
    @Id var id: Long,
    val title: String,
    val body: String?,
    val thumbnail: String?,
    val isPrivate: Boolean = true,
    val description: String?,
    @CreatedDate @Column("created_at") var createdAt: LocalDateTime?,
    @Column("published_at") val publishedAt: LocalDateTime?,
    @MappedCollection(idColumn = "post_id") val categoryId: CategoryRef?,
    @MappedCollection(idColumn = "post_id") val virtualAuthorId: VirtualAuthorRef?,
    @MappedCollection(idColumn = "post_id") val tags: Set<TagRef> = hashSetOf()
) {
    @LastModifiedDate @Column("updated_at") var updatedAt: LocalDateTime = LocalDateTime.MIN
    private set
}

@Table("post_category")
data class CategoryRef(val categoryId: Long)

@Table("post_virtual_author")
data class VirtualAuthorRef(val virtualAuthorId: UUID)

@Table("post_tag")
data class TagRef(val tagName: String)

class BeforeSavePostCallback: BeforeSaveCallback<PostEntity> {
    override fun onBeforeSave(aggregate: PostEntity, aggregateChange: MutableAggregateChange<PostEntity>): PostEntity {
        if (aggregate.id == 0L)
            aggregate.id = IdGenerator.nextId()

        return aggregate
    }
}