package xyz.neonkid.cms.modules.author.useCases.events

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.modules.author.domain.aggregate.VirtualAuthorId
import xyz.neonkid.cms.modules.author.infrastructure.persistence.VirtualAuthorPersistenceAdapter
import xyz.neonkid.cms.modules.post.domain.aggregate.PostId
import xyz.neonkid.cms.modules.post.domain.events.VirtualAuthorRemovedToPostDomainEvent
import java.util.*

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Component
class RemovePostToVirtualAuthorEventHandler(private val removePostToVirtualAuthorUseCase: RemovePostToVirtualAuthorUseCase) {
    @EventListener
    fun handleEvent(event: VirtualAuthorRemovedToPostDomainEvent) {
        val command = RemovePostToVirtualAuthorCommand(
            PostId(event.postId),
            VirtualAuthorId(event.virtualAuthorId)
        )
        this.removePostToVirtualAuthorUseCase.invoke(command)
    }
}

@Service
@Transactional
class RemovePostToVirtualAuthorUseCase(
    private val virtualAuthorPersistenceAdapter: VirtualAuthorPersistenceAdapter
): UseCase<RemovePostToVirtualAuthorCommand, Unit> {
    override fun invoke(command: RemovePostToVirtualAuthorCommand) {
        val author = virtualAuthorPersistenceAdapter.findById(command.virtualAuthorId)
        author.removePost(command)

        virtualAuthorPersistenceAdapter.update(author)
    }
}

data class RemovePostToVirtualAuthorCommand(val postId: PostId, val virtualAuthorId: VirtualAuthorId)