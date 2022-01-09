package xyz.neonkid.cms.persistence.author

import org.springframework.data.annotation.*
import org.springframework.data.relational.core.conversion.MutableAggregateChange
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.event.BeforeSaveCallback
import java.time.LocalDateTime
import java.util.*

/**
 * Created by Neon K.I.D on 1/2/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Table("virtual_author")
data class VirtualAuthorEntity(
    val name: String,
    val description: String?,
    val profileImage: String?,
    @MappedCollection(idColumn = "virtual_author_id") val posts: Set<PostRef>
) {
    @Id lateinit var id: UUID

    @CreatedDate @Column("created_at") private lateinit var createdAt: LocalDateTime
    @LastModifiedDate @Column("updated_at") private lateinit var updatedAt: LocalDateTime
}

@Table("virtual_author_post")
data class PostRef(val postId: Long)

class BeforeSaveAuthorCallback : BeforeSaveCallback<VirtualAuthorEntity> {
    override fun onBeforeSave(
        aggregate: VirtualAuthorEntity, aggregateChange: MutableAggregateChange<VirtualAuthorEntity>
    ): VirtualAuthorEntity {
        aggregate.id = UUID.randomUUID()
        return aggregate
    }
}