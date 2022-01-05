package xyz.neonkid.cms.modules.post.useCases.commands

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.modules.author.domain.aggregate.VirtualAuthorId
import xyz.neonkid.cms.modules.category.domain.aggregate.CategoryId
import xyz.neonkid.cms.modules.post.domain.aggregate.Post
import xyz.neonkid.cms.modules.post.domain.events.CategoryAddedToPostDomainEvent
import xyz.neonkid.cms.modules.post.domain.valueObjects.*
import xyz.neonkid.cms.modules.post.infrastructure.persistence.PostPersistenceAdapter
import xyz.neonkid.cms.modules.post.useCases.queries.PostQueryRepository
import xyz.neonkid.cms.modules.post.useCases.queries.dto.PostDTO
import java.time.LocalDateTime
import java.util.*

/**
 * Created by Neon K.I.D on 12/31/21
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Service
@Transactional
class CreatePostUseCase(
    private val postPersistenceAdapter: PostPersistenceAdapter,
    private val postQueryRepository: PostQueryRepository,
    private val publisher: ApplicationEventPublisher
) : UseCase<CreatePostCommand, PostDTO> {
    override fun invoke(command: CreatePostCommand): PostDTO {
        val entity = postPersistenceAdapter.insert(Post.newPost(command))
        if (entity.categoryId != null)
            this.publisher.publishEvent(
                CategoryAddedToPostDomainEvent(entity.id.value, entity.categoryId!!.value)
            )

        return postQueryRepository.fetchById(entity.id.value)
    }
}

data class CreatePostCommand (
    val title: Title,
    val body: Body?,
    val description: Description?,
    val thumbnail: Thumbnail?,
    val isPrivate: IsPrivate,
    val publishedAt: ContentDateTime?,
    val categoryId: CategoryId?,
    val virtualAuthorId: VirtualAuthorId?
)

data class CreatePostRequest (
    val title: String,
    val body: String?,
    val description: String?,
    val thumbnail: String?,
    val isPrivate: Boolean = true,
    val publishedAt: LocalDateTime?,
    val categoryId: Long?,
    val virtualAuthorId: UUID?
)