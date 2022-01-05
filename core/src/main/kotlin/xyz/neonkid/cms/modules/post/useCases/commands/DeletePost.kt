package xyz.neonkid.cms.modules.post.useCases.commands

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.modules.post.domain.aggregate.PostId
import xyz.neonkid.cms.modules.post.domain.events.CategoryAddedToPostDomainEvent
import xyz.neonkid.cms.modules.post.domain.events.CategoryRemovedToPostDomainEvent
import xyz.neonkid.cms.modules.post.infrastructure.persistence.PostPersistenceAdapter
import xyz.neonkid.cms.modules.post.useCases.exceptions.PostNotFoundException
import xyz.neonkid.cms.persistence.post.PostRepository

/**
 * Created by Neon K.I.D on 12/31/21
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Service
@Transactional
class DeletePostUseCase(
    private val postPersistenceAdapter: PostPersistenceAdapter,
    private val publisher: ApplicationEventPublisher
) : UseCase<DeletePostCommand, Unit> {
    override fun invoke(command: DeletePostCommand) {
        val entity = postPersistenceAdapter.findById(command.postId)
        if (entity.categoryId != null)
            this.publisher.publishEvent(
                CategoryRemovedToPostDomainEvent(entity.id.value, entity.categoryId!!.value)
            )

        postPersistenceAdapter.deleteById(command.postId)
    }
}

data class DeletePostCommand(val postId: PostId)