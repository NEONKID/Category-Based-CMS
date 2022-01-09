package xyz.neonkid.cms.persistence.tag

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Table("tag")
data class TagEntity(
    @Id val name: String,
    @MappedCollection(idColumn = "tag_name") val postIds: Set<PostRef> = hashSetOf()
) {
    @LastModifiedDate @Column("updated_at") private lateinit var updatedAt: LocalDateTime
}

@Table("tag_post")
data class PostRef(val postId: Long)