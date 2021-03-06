package xyz.neonkid.cms.modules.category.useCases.queries

import xyz.neonkid.cms.modules.category.useCases.queries.dto.CategoryDTO
import xyz.neonkid.cms.modules.category.useCases.queries.dto.PublicCategoryDTO
import xyz.neonkid.cms.modules.category.useCases.queries.dto.PublicSingleCategoryDTO
import xyz.neonkid.cms.modules.category.useCases.queries.dto.SingleCategoryDTO

/**
 * Created by Neon K.I.D on 1/3/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
interface CategoryQueryRepository {
    fun fetchById(id: Long): CategoryDTO
    fun fetchPublicById(id: Long): PublicCategoryDTO
    fun fetchAll(): MutableList<SingleCategoryDTO>
    fun fetchPublicAll(): MutableList<PublicSingleCategoryDTO>
}