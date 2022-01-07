package xyz.neonkid.cms.modules.post.useCases.commands

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.modules.post.domain.aggregate.PostId
import xyz.neonkid.cms.modules.post.domain.valueObjects.*
import xyz.neonkid.cms.modules.post.infrastructure.persistence.PostPersistenceAdapter
import xyz.neonkid.cms.modules.post.useCases.queries.PostQueryRepository
import xyz.neonkid.cms.modules.post.useCases.queries.dto.PostDTO
import java.time.LocalDateTime

/**
 * Created by Neon K.I.D on 1/7/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Service
@Transactional
class SavePostUseCase(
    private val postPersistenceAdapter: PostPersistenceAdapter,
    private val postQueryRepository: PostQueryRepository
) : UseCase<SavePostCommand, PostDTO> {
    override fun invoke(command: SavePostCommand): PostDTO {
        val post = postPersistenceAdapter.findById(command.id)
        post.updatePost(command)
        postPersistenceAdapter.update(post)

        return postQueryRepository.fetchById(command.id.value)
    }
}

data class SavePostCommand(
    val id: PostId,
    val title: Title?,
    val body: Body?,
    val description: Description?,
    val thumbnail: Thumbnail?,
    val publishedAt: ContentDateTime?
)

data class SavePostRequest(
    val title: String?,
    val body: String?,
    val description: String?,
    val thumbnail: String?,
    val publishedAt: LocalDateTime?,
)