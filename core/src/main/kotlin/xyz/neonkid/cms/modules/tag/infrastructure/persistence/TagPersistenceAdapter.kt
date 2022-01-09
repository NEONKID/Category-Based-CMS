package xyz.neonkid.cms.modules.tag.infrastructure.persistence

import org.springframework.stereotype.Service
import xyz.neonkid.cms.common.interfaces.PersistenceAdapter
import xyz.neonkid.cms.modules.tag.domain.aggregate.Tag
import xyz.neonkid.cms.modules.tag.useCases.exceptions.TagNotFoundException
import xyz.neonkid.cms.persistence.tag.TagRepository

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Service
class TagPersistenceAdapter(private val tagRepository: TagRepository) : PersistenceAdapter<Tag, String> {
    override fun findById(id: String) =
        TagMapper.mapToDomainEntity(tagRepository.findById(id).orElseThrow { TagNotFoundException(id) })

    override fun insert(domain: Tag) =
        TagMapper.mapToDomainEntity(tagRepository.insert(TagMapper.mapToJdbcEntity(domain)))

    override fun update(domain: Tag) =
        TagMapper.mapToDomainEntity(tagRepository.update(TagMapper.mapToJdbcEntity(domain)))

    fun save(domain: Tag) = TagMapper.mapToDomainEntity(tagRepository.save(TagMapper.mapToJdbcEntity(domain)))

    override fun deleteById(id: String) {
        tagRepository.deleteById(id)
    }
}