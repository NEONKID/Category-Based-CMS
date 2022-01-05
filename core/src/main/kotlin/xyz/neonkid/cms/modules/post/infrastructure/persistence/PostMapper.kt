package xyz.neonkid.cms.modules.post.infrastructure.persistence

import xyz.neonkid.cms.common.interfaces.ModelMapper
import xyz.neonkid.cms.modules.author.domain.aggregate.VirtualAuthorId
import xyz.neonkid.cms.modules.category.domain.aggregate.CategoryId
import xyz.neonkid.cms.modules.post.domain.aggregate.Post
import xyz.neonkid.cms.modules.post.domain.aggregate.PostId
import xyz.neonkid.cms.modules.post.domain.valueObjects.*
import xyz.neonkid.cms.persistence.post.CategoryRef
import xyz.neonkid.cms.persistence.post.PostEntity
import xyz.neonkid.cms.persistence.post.VirtualAuthorRef

/**
 * Created by Neon K.I.D on 12/31/21
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
object PostMapper : ModelMapper<Post, PostEntity> {
    override fun mapToDomainEntity(model: PostEntity) =
        Post(
            PostId(model.id),
            Title(model.title),
            model.body?.let { Body(it) },
            model.thumbnail?.let { Thumbnail(it) },
            IsPrivate(model.isPrivate),
            model.description?.let { Description(it) },
            model.publishedAt?.let { ContentDateTime(it) },
            model.categoryId?.let { CategoryId(it.categoryId) },
            model.virtualAuthorId?.let { VirtualAuthorId(it.virtualAuthorId) }
        )

    override fun mapToJdbcEntity(model: Post) =
        PostEntity(
            model.id.value, model.title.value, model.body?.value,
            model.thumbnail?.value, model.isPrivate.value,
            model.description?.value, model.publishedAt?.value,
            model.categoryId?.let { CategoryRef(it.value) },
            model.virtualAuthorId?.let { VirtualAuthorRef(it.value) }
        )
}