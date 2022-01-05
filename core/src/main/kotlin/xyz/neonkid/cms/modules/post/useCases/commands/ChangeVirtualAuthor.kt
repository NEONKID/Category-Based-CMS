package xyz.neonkid.cms.modules.post.useCases.commands

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.modules.author.domain.aggregate.VirtualAuthorId
import xyz.neonkid.cms.modules.post.domain.aggregate.PostId
import xyz.neonkid.cms.modules.post.domain.events.VirtualAuthorAddedToPostDomainEvent
import xyz.neonkid.cms.modules.post.domain.events.VirtualAuthorRemovedToPostDomainEvent
import xyz.neonkid.cms.modules.post.infrastructure.persistence.PostPersistenceAdapter

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Service
@Transactional
class ChangeVirtualAuthorUseCase(
    private val postPersistenceAdapter: PostPersistenceAdapter,
    private val publisher: ApplicationEventPublisher
): UseCase<ChangeVirtualAuthorCommand, Unit> {
    override fun invoke(command: ChangeVirtualAuthorCommand) {
        val entity = postPersistenceAdapter.findById(command.postId)
        if (entity.virtualAuthorId != null)
            this.publisher.publishEvent(
                VirtualAuthorRemovedToPostDomainEvent(entity.id.value, entity.virtualAuthorId!!.value)
            )

        entity.virtualAuthorId = command.virtualAuthorId
        this.publisher.publishEvent(
            VirtualAuthorAddedToPostDomainEvent(entity.id.value, command.virtualAuthorId.value)
        )

        postPersistenceAdapter.update(entity)
    }
}

data class ChangeVirtualAuthorCommand(val postId: PostId, val virtualAuthorId: VirtualAuthorId)