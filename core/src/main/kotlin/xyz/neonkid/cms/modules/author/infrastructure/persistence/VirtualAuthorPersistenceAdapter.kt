package xyz.neonkid.cms.modules.author.infrastructure.persistence

import org.springframework.stereotype.Service
import xyz.neonkid.cms.common.interfaces.PersistenceAdapter
import xyz.neonkid.cms.modules.author.domain.aggregate.VirtualAuthor
import xyz.neonkid.cms.modules.author.domain.aggregate.VirtualAuthorId
import xyz.neonkid.cms.modules.author.useCases.exceptions.VirtualAuthorNotFoundException
import xyz.neonkid.cms.persistence.author.VirtualAuthorRepository

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Service
class VirtualAuthorPersistenceAdapter (
    private val virtualAuthorRepository: VirtualAuthorRepository
): PersistenceAdapter<VirtualAuthor, VirtualAuthorId> {
    override fun findById(id: VirtualAuthorId) =
        VirtualAuthorMapper.mapToDomainEntity(virtualAuthorRepository.findById(id.value).orElseThrow { VirtualAuthorNotFoundException(id.value) })

    override fun insert(domain: VirtualAuthor) =
        VirtualAuthorMapper.mapToDomainEntity(virtualAuthorRepository.insert(VirtualAuthorMapper.mapToJdbcEntity(domain)))

    override fun update(domain: VirtualAuthor) =
        VirtualAuthorMapper.mapToDomainEntity(virtualAuthorRepository.update(VirtualAuthorMapper.mapToJdbcEntity(domain)))

    override fun deleteById(id: VirtualAuthorId) {
        virtualAuthorRepository.deleteById(id.value)
    }
}