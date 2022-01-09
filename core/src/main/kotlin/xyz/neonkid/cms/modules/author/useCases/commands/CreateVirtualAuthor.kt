package xyz.neonkid.cms.modules.author.useCases.commands

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.modules.author.domain.aggregate.VirtualAuthor
import xyz.neonkid.cms.modules.author.domain.aggregate.VirtualAuthorId
import xyz.neonkid.cms.modules.author.domain.valueObjects.Description
import xyz.neonkid.cms.modules.author.domain.valueObjects.Name
import xyz.neonkid.cms.modules.author.domain.valueObjects.ProfileImage
import xyz.neonkid.cms.modules.author.infrastructure.persistence.VirtualAuthorPersistenceAdapter
import xyz.neonkid.cms.modules.author.useCases.queries.VirtualAuthorQueryRepository
import xyz.neonkid.cms.modules.author.useCases.queries.dto.VirtualAuthorDTO
import xyz.neonkid.cms.persistence.author.VirtualAuthorRepository

/**
 * Created by Neon K.I.D on 12/31/21
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Service
@Transactional
class CreateVirtualAuthorUseCase(
    private val virtualAuthorPersistenceAdapter: VirtualAuthorPersistenceAdapter,
    private val virtualAuthorQueryRepository: VirtualAuthorQueryRepository
): UseCase<CreateVirtualAuthorCommand, VirtualAuthorDTO> {
    override fun invoke(command: CreateVirtualAuthorCommand): VirtualAuthorDTO {
        val entity = virtualAuthorPersistenceAdapter.insert(VirtualAuthor.newVirtualAuthor(command))
        return virtualAuthorQueryRepository.fetchById(entity.id!!.value)
    }
}

data class CreateVirtualAuthorCommand(
    val id: VirtualAuthorId,
    val name: Name,
    val description: Description?,
    val profileImage: ProfileImage?
)

data class CreateVirtualAuthorRequest(
    val name: String,
    val description: String?,
    val profileImage: String?
)