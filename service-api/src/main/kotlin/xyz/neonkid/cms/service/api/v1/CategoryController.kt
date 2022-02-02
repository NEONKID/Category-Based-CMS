package xyz.neonkid.cms.service.api.v1

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.neonkid.cms.modules.category.useCases.queries.CategoryQueryRepository

/**
 * Created by Neon K.I.D on 1/16/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@RestController
@RequestMapping("/v1/categories")
class CategoryController(
    private val categoryQueryRepository: CategoryQueryRepository
) {
    @GetMapping
    fun getCategories() = categoryQueryRepository.fetchPublicAll()

    @GetMapping( "/{id}")
    fun getCategory(@PathVariable id: Long) = categoryQueryRepository.fetchPublicById(id)
}