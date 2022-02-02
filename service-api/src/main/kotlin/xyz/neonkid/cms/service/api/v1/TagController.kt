package xyz.neonkid.cms.service.api.v1

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.neonkid.cms.modules.tag.useCases.queries.TagQueryRepository

/**
 * Created by Neon K.I.D on 1/16/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@RestController
@RequestMapping("/v1/tags")
class TagController(
    private val tagQueryRepository: TagQueryRepository
) {
    @GetMapping
    fun getTags() = tagQueryRepository.fetchPublicAll()

    @GetMapping("/{name}")
    fun getTag(@PathVariable name: String) = tagQueryRepository.fetchPublicByName(name)
}