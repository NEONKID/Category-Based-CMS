package xyz.neonkid.cms.modules.author.infrastructure.persistence

import xyz.neonkid.cms.common.interfaces.ModelMapper
import xyz.neonkid.cms.modules.author.domain.aggregate.VirtualAuthor
import xyz.neonkid.cms.modules.author.domain.aggregate.VirtualAuthorId
import xyz.neonkid.cms.modules.author.domain.valueObjects.Description
import xyz.neonkid.cms.modules.author.domain.valueObjects.Name
import xyz.neonkid.cms.modules.author.domain.valueObjects.ProfileImage
import xyz.neonkid.cms.modules.post.domain.aggregate.PostId
import xyz.neonkid.cms.persistence.author.PostRef
import xyz.neonkid.cms.persistence.author.VirtualAuthorEntity
import java.util.stream.Collectors

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
object VirtualAuthorMapper : ModelMapper<VirtualAuthor, VirtualAuthorEntity> {
    override fun mapToDomainEntity(model: VirtualAuthorEntity) =
        VirtualAuthor(
            VirtualAuthorId(model.id),
            Name(model.name),
            model.description?.let { Description(it) },
            model.profileImage?.let { ProfileImage(it) },
            model.posts.let { model.posts.stream().map { PostId(it.postId) }.collect(Collectors.toSet()) }
        )

    override fun mapToJdbcEntity(model: VirtualAuthor) =
        VirtualAuthorEntity(
            model.name.value,
            model.description?.value,
            model.profileImage?.value,
            model.postIds.let { model.postIds.stream().map { PostRef(it.value) }.collect(Collectors.toSet()) }
        )
}