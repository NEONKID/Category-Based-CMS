package xyz.neonkid.cms.modules.post.useCases.commands

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.modules.post.domain.aggregate.PostId
import xyz.neonkid.cms.modules.post.domain.events.CategoryRemovedToPostDomainEvent
import xyz.neonkid.cms.modules.post.infrastructure.persistence.PostPersistenceAdapter

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Service
@Transactional
class RemoveCategoryUseCase(
    private val postPersistenceAdapter: PostPersistenceAdapter,
    private val publisher: ApplicationEventPublisher
): UseCase<RemoveCategoryCommand, Unit> {
    override fun invoke(command: RemoveCategoryCommand) {
        val entity = postPersistenceAdapter.findById(command.postId)
        if (entity.categoryId != null) {
            this.publisher.publishEvent(
                CategoryRemovedToPostDomainEvent(entity.id.value, entity.categoryId!!.value)
            )
            entity.categoryId = null
        }

        this.postPersistenceAdapter.update(entity)
    }
}

data class RemoveCategoryCommand(val postId: PostId)