package xyz.neonkid.cms.modules.author.useCases.events

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.modules.author.domain.aggregate.VirtualAuthorId
import xyz.neonkid.cms.modules.author.infrastructure.persistence.VirtualAuthorPersistenceAdapter
import xyz.neonkid.cms.modules.post.domain.aggregate.PostId
import xyz.neonkid.cms.modules.post.domain.events.VirtualAuthorAddedToPostDomainEvent
import java.util.*

/**
 * Created by Neon K.I.D on 1/2/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Component
class AddPostToVirtualAuthorEventHandler(private val addPostToVirtualAuthorUseCase: AddPostToVirtualAuthorUseCase) {
    @EventListener
    fun handleEvent(event: VirtualAuthorAddedToPostDomainEvent) {
        val command = AddPostToVirtualAuthorCommand(
            PostId(event.postId),
            VirtualAuthorId(event.virtualAuthorId)
        )
        this.addPostToVirtualAuthorUseCase.invoke(command)
    }
}

@Service
@Transactional
class AddPostToVirtualAuthorUseCase(
    private val virtualAuthorPersistenceAdapter: VirtualAuthorPersistenceAdapter
): UseCase<AddPostToVirtualAuthorCommand, Unit> {
    override fun invoke(command: AddPostToVirtualAuthorCommand) {
        val author = virtualAuthorPersistenceAdapter.findById(command.virtualAuthorId)
        author.addPost(command)

        virtualAuthorPersistenceAdapter.update(author)
    }
}

data class AddPostToVirtualAuthorCommand(val postId: PostId, val virtualAuthorId: VirtualAuthorId)