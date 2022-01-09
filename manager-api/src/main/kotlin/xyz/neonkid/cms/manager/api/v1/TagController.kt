package xyz.neonkid.cms.manager.api.v1

import org.springframework.web.bind.annotation.*
import xyz.neonkid.cms.core.utils.success
import xyz.neonkid.cms.modules.tag.infrastructure.persistence.TagPersistenceAdapter
import xyz.neonkid.cms.modules.tag.useCases.queries.TagQueryRepository

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@RestController
@RequestMapping("/v1/tags")
class TagController(
    private val tagPersistenceAdapter: TagPersistenceAdapter,
    private val tagQueryRepository: TagQueryRepository
) {
    @GetMapping
    fun getAllTags() = success(tagQueryRepository.fetchAll())

    @GetMapping("/{name}")
    fun getTagByName(@PathVariable name: String) = success(tagQueryRepository.fetchById(name))

    @DeleteMapping("/{name}")
    fun deleteTagByName(@PathVariable name: String) {
        tagPersistenceAdapter.deleteById(name)
    }
}