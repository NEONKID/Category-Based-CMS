package xyz.neonkid.cms.persistence.category

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table
import xyz.neonkid.cms.modules.post.domain.aggregate.PostId

/**
 * Created by Neon K.I.D on 1/2/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Table("category")
data class CategoryEntity(
    @Id val id: Long,
    val name: String,
    @MappedCollection(idColumn = "category_id") val postIds: Set<PostRef> = hashSetOf()
)

@Table("category_post")
data class PostRef(val postId: Long)