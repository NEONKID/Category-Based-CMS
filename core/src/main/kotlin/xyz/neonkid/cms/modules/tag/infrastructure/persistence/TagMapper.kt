package xyz.neonkid.cms.modules.tag.infrastructure.persistence

import xyz.neonkid.cms.common.interfaces.ModelMapper
import xyz.neonkid.cms.modules.post.domain.aggregate.PostId
import xyz.neonkid.cms.modules.tag.domain.aggregate.Tag
import xyz.neonkid.cms.modules.tag.domain.aggregate.TagId
import xyz.neonkid.cms.persistence.tag.PostRef
import xyz.neonkid.cms.persistence.tag.TagEntity

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
object TagMapper : ModelMapper<Tag, TagEntity> {
    override fun mapToDomainEntity(model: TagEntity) = Tag(TagId(model.name), model.postIds.map { PostId(it.postId) }.toSet())
    override fun mapToJdbcEntity(model: Tag) = TagEntity(model.name.value, model.postIds.map { PostRef(it.value) }.toSet())
}